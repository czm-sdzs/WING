package com.wing.pi.tools.datasource.hbase.service;

import org.apache.hadoop.hbase.client.Result;

import java.util.List;

public interface HbaseQuery {

    /**
     * @Author: CuiBo
     * @Description: 根据rowkey,family,qualifier 查询hbase
     * @Params: 
     * @Return: 
     * @Date : 2018/4/8 17:48
     */
     
    Result getResultByRowKey(String tableName, String rowKey, String family, String qualifier);


    Result getListByRowKey(String tableName, String rowKey);
    /**
     * @Author: CuiBo
     * @Description: 根据rowkey批量查询hbase
     * @Params:
     * @Return:
     * @Date : 2018/4/3 15:44
     */
    Result[] getResultsByRowKeys(String tableName, List<String> rowkeyList);





}
