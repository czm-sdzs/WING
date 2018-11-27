package com.wing.pi

import redis.clients.jedis.JedisCluster

object DeleteUtil {

  def deleteKeyRedis(env:String,key: String): Unit ={
    val jedisCluster:JedisCluster = EnvUtil.jedisCluster(env)
    jedisCluster.del(key)
  }

}
