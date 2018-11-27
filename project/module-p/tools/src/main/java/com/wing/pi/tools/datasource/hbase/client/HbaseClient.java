package com.wing.pi.tools.datasource.hbase.client;

import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Create By: CuiBo
 * Date: 2018/4/8 17:20
 * Description: 初始化hbase客户端
 */
public class HbaseClient extends HbaseAbstract{

    private static final Logger LOGGER = LoggerFactory.getLogger(HbaseClient.class);

    public static HbaseClient hbaseClient;

    private  HConnection connection = null;

    //本地测试
    private static String ips = "192.168.20.53";

    /**
     * @Author: CuiBo
     * @Description:
     * @Params:
     * @Return:
     * @Date : 2018/4/8 17:27
     */
    public static HbaseClient getHbaseClient(){
        if(null == hbaseClient){
            synchronized (HbaseClient.class){
                if(null == hbaseClient){
                    hbaseClient = new HbaseClient();
                    return hbaseClient;
                }
            }
        }
        return hbaseClient;
    }

    //初始化hbase配置
    public static void initHbaseConf(String zkAddrs, String user){
        conf.set("hbase.zookeeper.quorum", zkAddrs);
        try {
            InetAddress ia=null;
            ia=ia.getLocalHost();
            String localname=ia.getHostName();
            String localip=ia.getHostAddress();
            LOGGER.info("HbaseClient 1 本机名称是："+ localname);
            LOGGER.info("HbaseClient 1 本机的ip是 ："+localip);
            if(localip.indexOf(ips) > -1){
                LOGGER.info("HbaseClient SET System："+ user);
                System.setProperty("HADOOP_USER_NAME", "hadoop");
            }
        } catch (Exception e) {
            LOGGER.error("Hbase conf init ERROR:{}",e);
        }
        getHbaseClient();
    }

    //获取hbase连接
    public HConnection getHbaseConn(){
        try {
            if(null ==connection || connection.isClosed()){
                connection = HConnectionManager.createConnection(conf);
            }
        } catch (IOException e) {
            LOGGER.error("get hbase connection ERROR:{}", e);
        }
        LOGGER.info("get hbase connection success!");
        return connection;
    }



    /**
     * @Author: CuiBo
     * @Description: 关闭资源
     * @Params:
     * @Return:
     * @Date : 2018/3/28 9:23
     */

    public void close(HTableInterface htable, HConnection connection) {
        if ( null != htable) {
            try {
                htable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (null != connection) {
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
