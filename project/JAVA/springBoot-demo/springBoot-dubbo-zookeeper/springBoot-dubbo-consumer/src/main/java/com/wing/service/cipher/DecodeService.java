package com.wing.service.cipher;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wing.api.cipher.DecodeApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * Create By: CuiBo
 * Date: 2018/3/29 14:40
 * Description: 解密
 */
@Component
public class DecodeService implements DecodeApi{
    private static final Logger LOGGER = LoggerFactory.getLogger(DecodeService.class);

    @Reference(version = "1.0.0")
    DecodeApi decodeApi;

    @Override
    public String decode(String str) {
        return decodeApi.decode(str);
    }
}
