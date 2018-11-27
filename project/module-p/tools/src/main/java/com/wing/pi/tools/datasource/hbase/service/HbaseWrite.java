package com.wing.pi.tools.datasource.hbase.service;

import org.apache.hadoop.hbase.client.Put;

import java.util.List;

public interface HbaseWrite {
    /**
     *
     * @param tableName 表名
     * @param listPut 数据list
     * @param commitNum 每批次提交数，默认1000，不满1000一次性提交
     * @throws Exception
     */
   void write(String tableName, List<Put> listPut, int commitNum) throws Exception;


    /**
     *
     * @param tableName 表名
     * @param listPut 数据list 每批次提交数，默认1000，不满1000一次性提交
     * @throws Exception
     */
    void write(String tableName, List<Put> listPut) throws Exception;



}
