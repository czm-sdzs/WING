package com.ace.sbr.api;/**
 * Created by Administrator on 2017/4/6.
 */

import com.ace.sbr.domain.CityPOJO;
import com.ace.sbr.services.HelloWorldServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * description HelloWorldApi
 * author stephen.cui
 * date 2017/4/6 21:59
 **/
@RestController
@RequestMapping("/test")
public class HelloWorldApi {
    @Autowired
    private HelloWorldServices helloWorldServices;

    @RequestMapping(value = {"/"})
    public String test(){
        String str =  helloWorldServices.test();
        return str;
    }

    @RequestMapping(value = {"/get"})
    public List<CityPOJO> getCity(int id){
        List<CityPOJO> list =  helloWorldServices.getCity(id);
        return list;
    }
}
