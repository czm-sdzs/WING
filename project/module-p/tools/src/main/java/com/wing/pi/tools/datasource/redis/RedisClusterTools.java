package com.wing.pi.tools.datasource.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Create By: CuiBo
 * Date: 2018/8/21 16:58
 * Description:
 */
public class RedisClusterTools {
    static String evn = "prod";

    static JedisCluster jedisCluster = null;

    public static void initRedisClusterTools() {
        // redis节点信息   这里最好写入配置文件
        System.out.println("init redis");
        Set<HostAndPort> nodes = new HashSet<HostAndPort>();

        if("test".equals(evn)){
            nodes.add(new HostAndPort("192.168.0.192", 6380));
            nodes.add(new HostAndPort("192.168.0.192", 6381));
            nodes.add(new HostAndPort("192.168.0.192", 6382));
            nodes.add(new HostAndPort("192.168.0.192", 6383));
            nodes.add(new HostAndPort("192.168.0.192", 6384));
            nodes.add(new HostAndPort("192.168.0.192", 6385));
        }else{
            nodes.add(new HostAndPort("10.0.8.170", 6800));
            nodes.add(new HostAndPort("10.0.8.171", 6800));
            nodes.add(new HostAndPort("10.0.8.172", 6800));
            nodes.add(new HostAndPort("10.0.8.170", 6801));
            nodes.add(new HostAndPort("10.0.8.171", 6801));
            nodes.add(new HostAndPort("10.0.8.172", 6801));
        }

        // Jedis连接池配置
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最大空闲连接数, 默认8个
        jedisPoolConfig.setMaxIdle(100);
        // 最大连接数, 默认8个
        jedisPoolConfig.setMaxTotal(500);
        //最小空闲连接数, 默认0
        jedisPoolConfig.setMinIdle(0);
        // 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
        jedisPoolConfig.setMaxWaitMillis(2000); // 设置2秒
        //对拿到的connection进行validateObject校验
        jedisPoolConfig.setTestOnBorrow(true);
        jedisCluster = new JedisCluster(nodes, jedisPoolConfig);
    }

    private static void existKey(String key) {
            System.out.println("key:"+key);
            boolean tf = jedisCluster.exists(key);
            System.out.println(tf);
    }

    private static void findByKey(String key) {
//        try {//取字符串
//            String value = jedisCluster.get(key);
        Map value = jedisCluster.hgetAll(key);
            System.out.println(value);
//        } catch (Exception e) {
//            try {//取list
//                // 取数据，第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
//                List<String> values = jedisCluster.lrange(key, 0, -1);
//                System.out.println(values);
//            } catch (Exception e2) {
//                System.out.println("redis没有这个key");
//            }
//
//        }
    }

    public static void main(String[] args) {
        initRedisClusterTools();
        String key="dataPlatform.bamdata.%S.HighRiskArea.city";
        existKey(key);
        findByKey(key);
    }


}
