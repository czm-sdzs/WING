package com.wing.pi



import com.alibaba.druid.pool.DruidDataSource
import com.alibaba.fastjson.JSONArray
import redis.clients.jedis.{HostAndPort, JedisCluster}
import java.util

object EnvUtil {


  private var mysqlDataSource: DruidDataSource = _

  def mysqlDa(env:String):DruidDataSource = {
    var url:String=""
    var username:String=""
    var passwd:String=""
    if("test".equals(env)){
      url="jdbc:mysql://192.168.0.216:3306/bam"
      username="root"
      passwd="123456"
    }else if("prod".equals()){
      url="jdbc:mysql://192.168.0.216:3306/bam"
      username="root"
      passwd="123456"
    }

    if(mysqlDataSource ==null){
      synchronized {
        if (mysqlDataSource == null) {
          mysqlDataSource = new DruidDataSource
          mysqlDataSource.setDriverClassName("com.mysql.jdbc.Driver")
          mysqlDataSource.setUrl(url)
          mysqlDataSource.setUsername(username)
          mysqlDataSource.setPassword(passwd)
          mysqlDataSource.setInitialSize(5)
          mysqlDataSource.setMaxActive(10)
          //最多等待连接3秒钟
          mysqlDataSource.setMaxWait(3000)
          mysqlDataSource.setPoolPreparedStatements(true)
          mysqlDataSource.setMaxOpenPreparedStatements(10)
        }
      }
    }
    mysqlDataSource
  }

  private var cluster: JedisCluster = _
  /**
    * 获取redis的信息
    *
    * @param env 环境信息
    * @return
    */
  def jedisCluster(env: String): JedisCluster = {
    var nodis:Array[String] = Array()
    if("test".equals(env)){
      nodis= Array("192.168.0.192:6380","192.168.0.192:6381","192.168.0.192:6382","192.168.0.192:6383","192.168.0.192:6384","192.168.0.192:6385")
    }else if("prod".equals(env)){
      nodis = Array("10.0.8.170:6800","10.0.8.170:6801","10.0.8.171:6800","10.0.8.171:6801","10.0.8.172:6800","10.0.8.172:6801")
    }
    if (cluster == null) {
      synchronized {
        if (cluster == null) {
          val jedisClusterNodes: util.HashSet[HostAndPort] = new util.HashSet[HostAndPort](nodis.length)
          for(elem <- nodis){
            val nodiss = elem.split(":")
            val hostAndPort: HostAndPort = new HostAndPort(nodiss(0), Integer.parseInt(nodiss(1)))
            jedisClusterNodes.add(hostAndPort)
          }
          cluster = new JedisCluster(jedisClusterNodes)
        }
      }
    }
    cluster
  }

}
