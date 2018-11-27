package com.wing.pi.tools.datasource.hbase.impl;


import com.wing.pi.tools.datasource.hbase.client.HbaseClient;
import com.wing.pi.tools.datasource.hbase.service.HbaseWrite;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * Create By: CuiBo
 * Date: 2018/4/8 17:41
 * Description:
 */
public class HbaseWriteImpl extends HbaseClient implements HbaseWrite {
    private static final Logger LOGGER = LoggerFactory.getLogger(HbaseWriteImpl.class);

    @Override
    public void write(String tableName,List<Put> listPut,int commitNum){
        HTable htable = null;
        HConnection connection = null;
        int listSize = listPut.size();
        //批量提交
        try{
            if(null!=listPut && listPut.size()>0){
                TableName name = TableName.valueOf(tableName);
                connection = hbaseClient.getHbaseConn();
                htable = (HTable) connection.getTable(name);
                htable.setAutoFlush(false);
                //判断是否分批次
                if(listSize > commitNum){
                    int part = listSize/commitNum;
                    System.out.println("part:"+part);
                    for(int i=0;i<part;i++){
                        List<Put> listPage = listPut.subList(0,commitNum);
                        //执行插入hbase
                        htable.put(listPage);
                        htable.flushCommits();
                        //剔除已经插入的数据
                        listPut.subList(0,commitNum).clear();
                        LOGGER.info("hbase batch page write:"+i);

                    }
                    //插入最后剩下的数据
                    if(listPut.size()>0){
                        htable.put(listPut);
                        htable.flushCommits();
                        LOGGER.info("hbase batch last write.");
                    }
                }else{
                    //直接一次性插入
                    System.out.println("3");
                    htable.put(listPut);
                    htable.flushCommits();
                    LOGGER.info("hbase batch write:"+listSize);
                }
            }else{
                LOGGER.info("没有数据！");
            }
        }catch (IOException e){
            LOGGER.error("hbase write error:{}",e);
        }finally {
            hbaseClient.close(htable,connection);
        }
    }

    @Override
    public void write(String tableName, List<Put> listPut) throws Exception {
        this.write(tableName,listPut,1000);
    }
}
