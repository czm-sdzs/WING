package com.wing.operation.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Create By: CuiBo
 * Date: 2018/3/27 16:35
 * Description: Hbase 公用类
 */
@Component
public class HbaseOperation {

    private static final Logger LOGGER = LoggerFactory.getLogger(HbaseOperation.class);

    @Autowired
    private Configuration conf;

    @Value("${hbase.quorum}")
    private String QUORUM;

    @Value("${hbase.user}")
    private String HADOOPUSER;

   /* static {
        conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", QUORUM);
        System.setProperty("HADOOP_USER_NAME", "hadoop");
    }*/

    /**
     * @Author: CuiBo
     * @Description: 初始化config
     * @Params:
     * @Return:
     * @Date : 2018/3/28 12:56
     */

    private void initConfig() {
        if (null == conf) {
            conf = HBaseConfiguration.create();
            conf.set("hbase.zookeeper.quorum", QUORUM);
            System.setProperty("HADOOP_USER_NAME", "hadoop");
        }
    }

    /**
     * @Author: CuiBo
     * @Description: 获取connection
     * @Params:
     * @Return:
     * @Date : 2018/3/27 18:11
     */
    private HConnection getConnection() {
        HConnection connection = null;
//        initConfig();
        try {
            connection = HConnectionManager.createConnection(conf);

        } catch (IOException e) {
            LOGGER.error("HbaseOperation getConnection ERROR:{}", e);
        }
        return connection;
    }

    /**
     * @Author: CuiBo
     * @Description: 根据rowkey, 一行中的某一列簇查询一条数据
     * @Params:
     * @Return:
     * @Date : 2018/3/28 9:17
     */

    public Result getResultByRowKeyColumn(String tableName, String rowKey, String family, String qualifier) {
        Result result = null;
        HConnection connection = null;
        HTableInterface htable = null;
        try {
            connection = getConnection();
            htable = connection.getTable(tableName);
            Get get = new Get(rowKey.getBytes());
            get.addFamily(family.getBytes());
            get.addColumn(family.getBytes(), qualifier.getBytes());
            result = htable.get(get);
            LOGGER.info("query result size:{}", result.size());

        } catch (IOException e) {
            LOGGER.error("HbaseOperation getResult ERROR:{}", e);
        } finally {
            close(htable, connection);
        }
        return result;
    }


    /**
     * @Author: CuiBo
     * @Description: 关闭资源
     * @Params:
     * @Return:
     * @Date : 2018/3/28 9:23
     */

    public void close(HTableInterface htable, HConnection connection) {
        if (htable != connection) {
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
