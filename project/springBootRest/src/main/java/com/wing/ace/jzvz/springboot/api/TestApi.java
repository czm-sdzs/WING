package com.wing.ace.jzvz.springboot.api;

import com.wing.ace.jzvz.springboot.domain.CityPOJO;
import com.wing.ace.jzvz.springboot.services.TestServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2017/11/21.
 */
@RestController
@RequestMapping("/test")
public class TestApi {

    @Autowired
    private TestServices testServices;

    @RequestMapping(value = {"/",""})
    public String test(){
        return "hello test";
    }

    @RequestMapping(value = {"/get"})
    public List<CityPOJO> getCity(int id){
        List<CityPOJO> list = testServices.getCity(id);
        return list;
    }
}