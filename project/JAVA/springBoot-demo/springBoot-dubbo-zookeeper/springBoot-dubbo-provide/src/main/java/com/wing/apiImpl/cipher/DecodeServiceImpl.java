package com.wing.apiImpl.cipher;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.wing.api.cipher.DecodeApi;
import com.wing.operation.hbase.DecodeQuery;
import com.wing.operation.permission.DecodePerm;
import com.wing.utils.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Create By: CuiBo
 * Date: 2018/3/29 15:03
 * Description:
 */
@Service(version = "1.0.0")
public class DecodeServiceImpl implements DecodeApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(DecodeServiceImpl.class);

    @Autowired
    DecodePerm decodePerm;

    @Autowired
    DecodeQuery decodeQuery;

    @Override
    public String decode(String str) {
        String result = "";
        /*
        身份验证通过则进行解密
         */
        Map map =  JSONObject.parseObject(str);
        if(decodePerm.valid(map)){
            String rowKey = map.get(Constant.DATA).toString();
            String value = decodeQuery.getDecodeStr(rowKey);
            result = "解密结果！";
        }
        return result;
    }
}
