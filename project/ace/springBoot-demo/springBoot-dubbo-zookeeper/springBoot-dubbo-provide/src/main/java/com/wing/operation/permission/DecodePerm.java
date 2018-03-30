package com.wing.operation.permission;

import com.wing.operation.hbase.HbaseOperation;
import com.wing.utils.Constant;
import com.wing.utils.TimeUtil;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


/**
 * Create By: CuiBo
 * Date: 2018/3/27 17:10
 * Description:
 */
@Component
public class DecodePerm {
    private static final Logger LOGGER = LoggerFactory.getLogger(DecodePerm.class);
    @Autowired
    HbaseOperation hbaseOperation;

    private static final String htableName = "dwd:md5_aes";
    private static final String ROW_KEY="dr_ds_cry";
    private static final String FAMILY = "fam";
    private static final String QUALIFIER = "cry";

  /**
   * @Author: CuiBo
   * @Description: 查询数据  判断数据是否存在
   * @Params:
   * @Return:
   * @Date : 2018/3/28 14:37
   */

    public boolean valid(Map map) {
        String user = (String)map.get(Constant.DECODE_USER);
        String code = (String)map.get(Constant.DECODE_CODE);
        String key = user+"_"+code;
        boolean resultFlag = false;
        Result result = hbaseOperation.getResultByRowKeyColumn(htableName,ROW_KEY,FAMILY,QUALIFIER);
        Map<String,String> resultMap = new HashMap<>();
        if (result.size() > 0) {
            for (KeyValue keyValue : result.list()) {
                String value = Bytes.toString(keyValue.getValue());
                LOGGER.info("query result value:{}", value);//列对应的值
                String[] cryResult = value.split(",");
                for (int i = 0; i < cryResult.length; i++) {
                    String[] cryStr = cryResult[i].split(" ");
                    resultMap.put(cryStr[0]+"_"+cryStr[1], cryStr[2]);
                }
                LOGGER.info("resutMap:{}", resultMap);
            }
        }
        if(resultMap.containsKey(key) && TimeUtil.getDistanceDays(resultMap.get(key))){
            resultFlag = true;
        }
        return resultFlag;
    }


}
