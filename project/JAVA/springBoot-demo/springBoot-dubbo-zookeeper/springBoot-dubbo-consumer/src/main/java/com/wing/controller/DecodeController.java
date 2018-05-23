package com.wing.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.wing.service.cipher.DecodeService;
import com.wing.utils.ResponseUtil;

/**
 * Create By: CuiBo
 * Date: 2018/3/29 15:24
 * Description:
 */
@RestController
@RequestMapping("/")
public class DecodeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DecodeController.class);

    @Autowired
    DecodeService decodeService;

    /**
     * @Author: CuiBo
     * @Description: 获取密码
     * @Params:
     * @Return:
     * @Date : 2018/3/26 10:52
     */
    @RequestMapping(path = {"decode"}, method = {RequestMethod.POST})
    public String decodeStr(@RequestBody String str){
        LOGGER.info("decode str:"+str);
        String result = decodeService.decode(str);
        return ResponseUtil.succeed(result);

    }
}
