package com.wing.pi

import redis.clients.jedis.{JedisCluster, ScanParams, ScanResult}

import scala.collection.JavaConversions.mapAsScalaMap
import java.util

object ReadUtil {

  private def main(args: Array[String]): Unit = {
    //var channelSourceMap = getHash("dataPlatform.bam.channelSource")

  }

  //  def likeSearch(env:String,key:String): Unit ={
  //    val jedis:JedisCluster = EnvUtil.jedisCluster(env)
  //    jedis.
  //
  //  }


  def getHm(env: String, key: String): Unit = {
    val hm = getHash(env, key)
    val keyList = hm.map(_._1)
    keyList.foreach((x: String) => {
      print(x + ":")
      println(hm.get(x).get)
    })
  }


  def getS(env: String, key: String) {
    val jedis: JedisCluster = EnvUtil.jedisCluster(env)
    val result = jedis.get(key)
    println("type:"+result.getClass.getSimpleName)
    println(result)
  }



  import scala.collection.JavaConversions.mapAsScalaMap

  private def getHash(env: String, key: String): scala.collection.mutable.Map[String, String] = {
    val jedis: JedisCluster = EnvUtil.jedisCluster(env)
    val jMap = jedis.hgetAll(key)
    println("type:"+jMap.getClass.getSimpleName)
    val map: scala.collection.mutable.Map[String, String] = new util.HashMap[String, String](jMap)
    map
  }


}
