package com.wing.pi

import java.util

import redis.clients.jedis.JedisCluster
import sun.awt.SunHints.Key
import java.util.Map

object WreteUtil {

  def main(args: Array[String]): Unit = {

  }

  def writeHash2Redis(env:String,key: String,_value:String): Unit ={
    var channelSourceMap:util.HashMap[String,String] =new util.HashMap[String,String]
    println("value : "+_value)
    val kv = _value.split(",")
    for (s <- kv) channelSourceMap.put(s.split(":")(0), s.split(":")(1))
    writeKey2Redis(env,key,channelSourceMap)
  }

  def writeRedisStr(env:String,key: String,_value:String): Unit ={
    println("value : "+_value)
    val jedisCluster:JedisCluster = EnvUtil.jedisCluster(env)
    jedisCluster.set(key,_value)
  }


  private def writeKey2Redis(env:String,key: String,value:Map[String,String]): Unit ={
    val jedisCluster:JedisCluster = EnvUtil.jedisCluster(env)
    jedisCluster.hmset(key,value)
  }




}
