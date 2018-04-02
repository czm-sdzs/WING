package com.wing;

import com.wing.operation.hbase.HbaseOperation;
import org.apache.hadoop.hbase.client.Result;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Create By: CuiBo
 * Date: 2018/3/28 11:11
 * Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class HbaseOperationTest {

    @Resource
    private HbaseOperation hbaseOperation;


    @org.junit.Test
    public void test(){
        String htable = "dwd:md5_aes";
        String rowKey = "dr_ds_cry";
        String FAMILY = "fam";
        String QUALIFIER = "cry";
        Result result  =  hbaseOperation.getResultByRowKeyColumn(htable,rowKey,FAMILY,QUALIFIER);
        System.out.println(result);
    }
}
