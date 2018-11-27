package com.wing.pi.tools.datasource.hbase.impl;


import com.wing.pi.tools.datasource.hbase.client.HbaseClient;
import com.wing.pi.tools.datasource.hbase.service.HbaseQuery;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Create By: CuiBo
 * Date: 2018/4/8 17:41
 * Description:
 */
public class HbaseQueryImpl extends HbaseClient implements HbaseQuery {
    private static final Logger LOGGER = LoggerFactory.getLogger(HbaseQueryImpl.class);

    /**
     * @Author: CuiBo
     * @Description: 根据rowkey,family,qualifier 查询hbase
     * @Params:
     * @Return:
     * @Date : 2018/3/28 9:17
     */
    @Override
    public Result getResultByRowKey(String tableName, String rowKey, String family, String qualifier) {
        LOGGER.info("Hbase Query getResultByRowKey ...");
        Result result = null;
        HTableInterface htable = null;
        HConnection connection = null;
        try {
            connection = hbaseClient.getHbaseConn();
            htable =connection.getTable(tableName);
            Get get = new Get(rowKey.getBytes());
            get.addFamily(family.getBytes());
            get.addColumn(family.getBytes(), qualifier.getBytes());
            result = htable.get(get);
            LOGGER.info("HbaseQueryImpl getResultByRowKey result size:{}", result.size());
        } catch (IOException e) {
            LOGGER.error("HbaseQueryImpl getResultByRowKey ERROR:{}", e);
        } finally {
            hbaseClient.close(htable, connection);
        }
        return result;
    }

    /**
     * @Author: CuiBo
     * @Description: 根据rowkey 查询hbase
     * @Params:
     * @Return:
     * @Date : 2018/3/28 9:17
     */
    @Override
    public Result getListByRowKey(String tableName, String rowKey) {
        LOGGER.info("Hbase Query  getListByRowKey ...");
        Result result = null;
        HTableInterface htable = null;
        HConnection connection = null;
        try {
            connection = hbaseClient.getHbaseConn();
            htable = connection.getTable(tableName);
            Get get = new Get(rowKey.getBytes());
            result = htable.get(get);
            LOGGER.info("HbaseQueryImpl getLByRowKey result size:{}", result.size());
        } catch (IOException e) {
            LOGGER.error("HbaseQueryImpl getResultByRowKey ERROR:{}", e);
        } finally {
            hbaseClient.close(htable, null);
        }
        return result;
    }



    /**
     * @Author: CuiBo
     * @Description: 根据rowkey批量查询hbase
     * @Params:
     * @Return:
     * @Date : 2018/4/3 15:44
     */
    @Override
    public Result[] getResultsByRowKeys(String tableName, List<String> rowkeyList){
        LOGGER.info("Hbase Query getResultsByRowKeys ...");
        Result[] results= null;
        HTableInterface htable = null;
        HConnection connection = null;
        List<Get> getList = new ArrayList();
        try{
            connection = hbaseClient.getHbaseConn();
            htable = connection.getTable(tableName);
            for (String rowkey : rowkeyList){
                Get get = new Get(Bytes.toBytes(rowkey));
                getList.add(get);
            }
            results = htable.get(getList);//重点在这，直接查getList<Get>
            LOGGER.info("HbaseQueryImpl getResultsByRowKeys result size:{}", results.length);
        }catch (IOException e){
            LOGGER.error("HbaseQueryImpl getResultsByRowKeys ERROR:{}", e);
        }finally {
            hbaseClient.close(htable,null);
        }
        return results;
    }



    /**
     * @Author: CuiBo
     * @Description: 根据rowkey批量查询hbase
     * @Params:
     * @Return:
     * @Date : 2018/4/3 15:44
     */
    public Result[] getResultsByRowKeys(String tableName, List<String> rowkeyList,String family, String qualifier){
        LOGGER.info("Hbase Query getResultsByRowKeys ...");
        Result[] results= null;
        HTableInterface htable = null;
        HConnection connection = null;
        List<Get> getList = new ArrayList();
        try{
            connection = hbaseClient.getHbaseConn();
            htable = connection.getTable(tableName);
            for (String rowkey : rowkeyList){
                Get get = new Get(Bytes.toBytes(rowkey));
                getList.add(get);
                get.addFamily(family.getBytes());
                get.addColumn(family.getBytes(), qualifier.getBytes());
            }
            results = htable.get(getList);//重点在这，直接查getList<Get>
            LOGGER.info("HbaseQueryImpl getResultsByRowKeys result size:{}", results.length);
        }catch (IOException e){
            LOGGER.error("HbaseQueryImpl getResultsByRowKeys ERROR:{}", e);
        }finally {
            hbaseClient.close(htable,null);
        }
        return results;
    }

}
