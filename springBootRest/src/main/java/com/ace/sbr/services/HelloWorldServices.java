package com.ace.sbr.services;/**
 * Created by Administrator on 2017/4/8.
 */

import com.ace.sbr.dao.HelloWorldDao;
import com.ace.sbr.domain.CityPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description
 * author stephen.cui
 * date 2017/4/8 16:49
 **/
@Service
public class HelloWorldServices {

    @Autowired
    private HelloWorldDao helloWorldDao;

    public String test(){
        return "hello world!";
    }

    public List<CityPOJO> getCity(int id) {
        return helloWorldDao.getCity(id);
    }

}
