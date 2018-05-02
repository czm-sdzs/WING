package com.wing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.wing.service.sample.SampleDubboService;

/**
 * Create By: CuiBo
 * Date: 2018/3/26 10:49
 * Description:
 */
@RestController
@RequestMapping("/dubbo")
public class SampleController {

    @Autowired
    SampleDubboService sampleDubboService;

    /**
     * @Author: CuiBo
     * @Description: 测试 get请求
     * @Params:
     * @Return:
     * @Date : 2018/3/26 10:51
     */
    @RequestMapping(path = {"test"}, method = {RequestMethod.GET})
    public String testGet(){
        return sampleDubboService.testDubboServiceCall();
    }

    /**
     * @Author: CuiBo
     * @Description: 测试post请求
     * @Params:
     * @Return:
     * @Date : 2018/3/26 10:52
     */
    @RequestMapping(path = {"/post"}, method = {RequestMethod.POST})
    public String testPost(){
        return "success!";
    }
}
