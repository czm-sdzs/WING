package com.wing.operation.hbase;

import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Create By: CuiBo
 * Date: 2018/3/29 15:10
 * Description: 查询 解密表
 */
@Component
public class DecodeQuery {
    private static final Logger LOGGER = LoggerFactory.getLogger(DecodeQuery.class);
    @Autowired
    HbaseOperation hbaseOperation;

    private static final String HTABLENAME = "dwd:md5_aes";
    private static final String FAMILY     = "fam";
    private static final String QUALIFIER  = "aes_id";


    /**
     * @Author: CuiBo
     * @Description: 查询密码
     * @Params:
     * @Return:
     * @Date : 2018/3/29 15:12
     */

    public String getDecodeStr(String rowKey) {
        String value = "";
        Result result = hbaseOperation.getResultByRowKeyColumn(HTABLENAME, rowKey, FAMILY, QUALIFIER);
        LOGGER.info("getDecodeStr result:{}",result);
        if (result.size() > 0) {
            for (KeyValue keyValue : result.list()) {
                value = Bytes.toString(keyValue.getValue());
                LOGGER.info("query result value:{}", value);//列对应的值
            }
        }
        return value;
    }
}
