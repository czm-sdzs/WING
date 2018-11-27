package com.wing.pi.md5aes

import java.io.{FileWriter, IOException}
import java.util
import java.util.Date

import java.io.{FileWriter, IOException}
import java.util
import java.util.Date

import com.lakala.data.DataNormal
import com.lkl.datacrypto.{AESCrypt, AESDecrypt}
import org.apache.hadoop.hbase.client._
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.mapreduce.TableInputFormat
import org.apache.hadoop.hbase.protobuf.ProtobufUtil
import org.apache.hadoop.hbase.util.Base64
import org.apache.hadoop.hbase.{HBaseConfiguration, TableName}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext, TaskContext}

import scala.io.Source

object HbaseRW {
  var source_table = ""
  var target_table = ""
  var hadoop_user_name = ""
  var zookeeperQuorum = ""
  var zookeeperPorn = ""
  var scanCaching: Int = 0
  var writeBatchSize = 0
  var writeBatchDays = 0

  def main(args: Array[String]): Unit = {
    init()
    readAndWriteHbase()
  }


  def readAndWriteHbase(): Unit = {
    var sc: SparkContext = null
    try {
      System.setProperty("HADOOP_USER_NAME", hadoop_user_name);
      var lastLine = ""
      val file = Source.fromFile("conf/transHistory.txt")
      for (line <- file.getLines) {
        if (line.trim.nonEmpty) {
          lastLine = line.trim
        }
      }
      file.close

      if (lastLine.isEmpty) {
        println("transHistory.txt文件不能为空..")
        return
        //        DateUtils.getDayBefore(new Date() ,1)
      }

      val arrDay = lastLine.split(",")
      val sparkConf = new SparkConf().setAppName("HBaseAes")
      sc = new SparkContext(sparkConf)
      sc.getConf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
      sc.getConf.set("spark.rdd.compress", "true")
      sc.getConf.set("spark.hadoop.mapred.output.compress", "true")
      sc.getConf.set("spark.hadoop.mapred.output.compression.codec", "true")
      sc.getConf.set("spark.hadoop.mapred.output.compression.codec", "org.apache.hadoop.io.compress.GzipCodec")
      sc.getConf.set("spark.hadoop.mapred.output.compression.type", "BLOCK")

      var num = 0
      val out = new FileWriter("conf/transHistory.txt", true)
      var strStartTime = arrDay(1)

      do { //按时间分批执行
        var beginWriteTime =  DateUtils.getDateNow()
        println("begin time:"+beginWriteTime)
        val scan = new Scan()

        val endTime = DateUtils.tranTimeToLong(DateUtils.getDaysLater(strStartTime, writeBatchDays))

        println(DateUtils.tranTimeToLong(strStartTime) + ":" + endTime)
        scan.setTimeRange(DateUtils.tranTimeToLong(strStartTime), endTime)
        // 每x天一个批次
        val rddNum = transMd5Aes(sc, scan)
        num = rddNum.collect().sum
        // 记录该批次的时间到文件
        println(s"${strStartTime},${DateUtils.tranTimeToString(endTime)},${num},${beginWriteTime}," + DateUtils.getDateNow()) //TODO: 打印耗时信息和记录数
        out.write(s"\n${strStartTime},${DateUtils.tranTimeToString(endTime)},${num},${beginWriteTime}," + DateUtils.getDateNow())
        out.flush()
        strStartTime = DateUtils.tranTimeToString(endTime)
      } while (strStartTime < DateUtils.getDateNow())
    } catch {
      case e2: Exception =>
        println("************error2:" + e2.printStackTrace())
    } finally {
      if (sc != null) sc.stop()
    }
  }

  private def transMd5Aes(sc: SparkContext, scan: Scan): RDD[Int] = {
    val conf_aes = HBaseConfiguration.create()
    //设置zooKeeper集群地址，也可以通过将hbase-site.xml导入classpath，但是建议在程序里这样设置
    conf_aes.set("hbase.zookeeper.quorum", zookeeperQuorum)
    //设置zookeeper连接端口，默认2181
    conf_aes.set("hbase.zookeeper.property.clientPort", zookeeperPorn)
    conf_aes.set(TableInputFormat.INPUT_TABLE, source_table) //原数据表
    println(scan.getClass().getResource("/").getPath())
    scan.setCaching(10000)
    scan.setCacheBlocks(false)
    conf_aes.set(TableInputFormat.SCAN, convertScanToString(scan))

    val bcZookeeperQuorum = sc.broadcast[String](zookeeperQuorum)
    val bcZookeeperPorn = sc.broadcast[String](zookeeperPorn)
    val bcTarget_table = sc.broadcast[String](target_table)
    val bcWriteBatchSize = sc.broadcast[Int](writeBatchSize)
    val hBasereadRDD = sc.newAPIHadoopRDD(conf_aes, classOf[TableInputFormat],
      classOf[ImmutableBytesWritable],
      classOf[Result])

    //写hbase
    val fam = "fam".getBytes
    val rdd = hBasereadRDD.mapPartitions(x => {
      val pid = TaskContext.getPartitionId()
      var i = 0
      val dest_conf = HBaseConfiguration.create()
      dest_conf.set("hbase.zookeeper.quorum", bcZookeeperQuorum.value)
      dest_conf.set("hbase.zookeeper.property.clientPort", bcZookeeperPorn.value)
      println(s"pid=${pid}, zookeeperQuorum=${bcZookeeperQuorum.value}，zookeeperPorn=${bcZookeeperPorn.value}")
      val conn = ConnectionFactory.createConnection(dest_conf)
      println(s"pid=${pid}, hbase已连接...")
      val dest_table = conn.getTable(TableName.valueOf(bcTarget_table.value))
      val listPut = new util.ArrayList[Put]()

      x.toArray.foreach(record => {
        val result = record._2
        /**
          * 1:解密aes_id,得到明文，根据类型清洗，判空
          * 2:aes:非空数据加密 得到新的aes
          * 3:rowKey:非空数据md5得到新的rowKey，
          */
        val aes_id_before = result.getValue(fam, "aes_id".getBytes)
        var type_id = ""
        if (result.getValue(fam, "type_id".getBytes) != null) {
          type_id = new String(result.getValue(fam, "type_id".getBytes))
        }

        //明文
        var plainText = ""
        if (aes_id_before != null) plainText = AESDecrypt.aesDeCrypty(new String(aes_id_before))
        if (plainText != null && plainText.nonEmpty) {
          //清洗后的数据
          var dataNormalValue = ""
          if ("1".equals(type_id)) {
            //姓名升维
            dataNormalValue = DataNormal.normalName(plainText)
          }
          if ("2".equals(type_id)) {
            dataNormalValue = DataNormal.normalPersonId(plainText)
          }
          if ("3".equals(type_id)) {
            dataNormalValue = DataNormal.normalBankCard(plainText)
          }
          if ("4".equals(type_id)) {
            dataNormalValue = DataNormal.normalMobileno(plainText)
          }
          if ("5".equals(type_id)) {
            dataNormalValue = DataNormal.normalAddress(plainText)
          }
          if ("6".equals(type_id)) {
            dataNormalValue = DataNormal.normalEmail(plainText)
          }
          if ("7".equals(type_id)) {
            dataNormalValue = DataNormal.normalPhoneNo(plainText)
          }
          if (dataNormalValue != null && dataNormalValue.nonEmpty) {
            val rowKey_after_md5 = AESCrypt.plainMd5(dataNormalValue)
            val aes_id_after = AESCrypt.aesCrypty(dataNormalValue)
            if (null != rowKey_after_md5 && !"".equals(rowKey_after_md5)) {
              val put = new Put(rowKey_after_md5.getBytes)
              put.addColumn(fam, "aes_id".getBytes, aes_id_after.getBytes)
              put.addColumn(fam, "sys_control_id".getBytes, result.getValue(fam, "sys_control_id".getBytes))
              put.addColumn(fam, "sys_last_program".getBytes, result.getValue(fam, "sys_last_program".getBytes))
              put.addColumn(fam, "sys_update_time".getBytes, result.getValue(fam, "sys_update_time".getBytes))
              put.addColumn(fam, "sys_update_user".getBytes, result.getValue(fam, "sys_update_user".getBytes))
              put.addColumn(fam, "type_id".getBytes, result.getValue(fam, "type_id".getBytes))
                listPut.add(put)
            }
            if (listPut.size >= bcWriteBatchSize.value) {
              i += bcWriteBatchSize.value
              println(new Date() + s": pid=${pid}, ${i}  :" + listPut.size())
              dest_table.put(listPut)
              listPut.clear()
            }
          }
        }
      })
      if (listPut.size >= 1) {
        i += listPut.size
        println(new Date() + s": pid=${pid}, count:" + listPut.size())
        dest_table.put(listPut)
        listPut.clear()
      }
      dest_table.close
      conn.close()
      println(s"pid=${pid}, hbase已关闭...")
      Array(i).toIterator
    })
    rdd
  }

  @throws[IOException]
  def convertScanToString(scan: Scan) = {
    val proto = ProtobufUtil.toScan(scan)
    Base64.encodeBytes(proto.toByteArray)
  }

  def init(): Unit = {
    ReadConf.initConf()
    val properties = ReadConf.getProperty()
    source_table = properties.getProperty("soruce_list")
    target_table = properties.getProperty("target_list")
    hadoop_user_name = properties.getProperty("hadoop.user_name")
    zookeeperQuorum = properties.getProperty("hbase.zookeeper.quorum")
    zookeeperPorn = properties.getProperty("hbase.zookeeper.porn")
    scanCaching = properties.getProperty("scanCaching").toInt
    writeBatchSize = properties.getProperty("writeBatchSize").toInt
    writeBatchDays = properties.getProperty("writeBatchDays").toInt
    println(s"source_table=${source_table}," +
      s"target_table=${target_table}," +
      s"hadoop_user_name=${hadoop_user_name}," +
      s"zookeeperQuorum=${zookeeperQuorum}," +
      s"zookeeperPorn=${zookeeperPorn}," +
      s"scanCaching=${scanCaching}," +
      s"writeBatchSize=${writeBatchSize}," +
      s"writeBatchDays=${writeBatchDays}")
  }

}
