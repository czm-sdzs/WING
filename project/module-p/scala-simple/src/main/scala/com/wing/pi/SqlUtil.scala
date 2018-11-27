package com.wing.pi

import java.sql.{PreparedStatement, ResultSet}

import com.alibaba.druid.pool.{DruidDataSource, DruidPooledConnection}

object SqlUtil {

  def main(args: Array[String]): Unit = {
    var flag = false


    while (true){
      println("flag:"+flag)
      flag = doo(flag,"test")

    }

  }


  def doo(flag:Boolean,env:String): Boolean ={
    if(flag){
      val channelSource = getApplyChannel(env)
      val keyList = channelSource.map(_._1)
      keyList.foreach((x:String) => {
        val value = channelSource.get(x).get
        println(value)

      })
    }else{

    }
    false
  }


  def getApplyChannel(env:String): Map[String, String] = {
    var sourceMap:Map[String,String] = Map();
    //查询mysql获取数据
    val mysqlDS: DruidDataSource = EnvUtil.mysqlDa(env)
    val conn: DruidPooledConnection = mysqlDS.getConnection
    var ps:PreparedStatement =null
    val channelSql = "select code,value from bam_dimension where type='channelsource'"
    ps = conn.prepareStatement(channelSql)
    val queryResult: ResultSet = ps.executeQuery()
    while(queryResult.next()){
      sourceMap += (queryResult.getString(1) -> queryResult.getString(1))
    }
  sourceMap
  }
}
