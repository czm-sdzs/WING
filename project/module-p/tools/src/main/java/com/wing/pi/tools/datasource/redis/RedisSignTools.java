package com.wing.pi.tools.datasource.redis;

import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Create By: CuiBo
 * Date: 2018/8/21 16:34
 * Description:
 */
public class RedisSignTools {

    private String redisIp="192.168.0.192";
    private String redisPort = "6381";


    public static void main(String[] args) {
        init();
        System.out.println("测试jedis运行情况: "+jedis.ping());
        jedis.close();
        shardedJedis.close();
    }


    /** * 获取客户端连接 * */
    public static void init(){
        initialPool();
        initialShardedPool();
        jedis = jedisPool.getResource();
        shardedJedis = shardedJedisPool.getResource();
    }


    /**
     * Jedis就是封装了redis的一些命令操作的java客户端
     * */
    private static Jedis  jedis = null;
    /**
     * Jedis实例不是线程安全的，所以为了避免一些线程安全问题，Jedis提供了线程池JedisPool
     * */
    private static JedisPool jedisPool = null;
    /**
     * redis在版本2的时候不支持redis集群，Jedis只能支持单redis服务器操作，此时redis服务器只能通过多个相互独立的
     * 主从服务器进行横向扩展，使用SharedJedis老师先分布式缓存，ShardedJedis通过一致性哈希表来实现分布式缓存。
     * */
    private static ShardedJedis  shardedJedis = null;
    /**
     * ShardedJedis的线程池ShardedJedisPool
     * */
    private static ShardedJedisPool shardedJedisPool = null;

    private static final String ip = "127.0.0.1";//Redis服务器IP
    private static final Integer port = 6379;//Redis的端口号
    private static final Integer timeout = 1000;//连接redis的等待时间
    private static final Integer maxTotal = 1024;//可连接实例的最大数目，默认值为8
    private static final Integer maxIdle = 200;//控制一个pool最多有多少个状态为idle的jedis实例，默认值为8
    private static final Integer maxWait = 10000;//等待可用连接的最大时间
    private static final boolean testOnBorrow = true;//在borrow一个jedis实例时，是否提前进行validate操作，如果为ture，则得到的jedis实例均是可用的

    /**
     * 初始化非切片池
     * */
    private static void initialPool(){
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxIdle(maxIdle);
            config.setMaxTotal(maxTotal);
            config.setMaxWaitMillis(maxWait);
            config.setTestOnBorrow(testOnBorrow);
            jedisPool = new JedisPool(config,ip,port,timeout);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 初始化切片池
     * */
    private static void initialShardedPool(){
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxIdle(maxIdle);
            config.setMaxTotal(maxTotal);
            config.setMaxWaitMillis(maxWait);
            config.setTestOnBorrow(testOnBorrow);
            List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
            shards.add(new JedisShardInfo(ip,port));
            //构造池
            shardedJedisPool = new ShardedJedisPool(config,shards);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
