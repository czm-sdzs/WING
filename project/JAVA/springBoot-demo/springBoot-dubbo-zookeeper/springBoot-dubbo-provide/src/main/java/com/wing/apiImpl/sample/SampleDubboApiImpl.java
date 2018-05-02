package com.wing.apiImpl.sample;


import com.alibaba.dubbo.config.annotation.Service;
import com.wing.api.sample.SampleDubboApi;

/**
 * Create By: CuiBo
 * Date: 2018/3/27 14:31
 * Description:
 */
@Service(version = "1.0.0")
public class SampleDubboApiImpl implements SampleDubboApi {
    @Override
    public String testDubboServiceCall() {
        System.out.println("---dubbo 发布服务----");
        return "success!";
    }
}
