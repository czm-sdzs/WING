package com.wing.services;/**
 * Created by Administrator on 2017/4/8.
 */

import com.wing.dao.TestDao;
import com.wing.domain.CityPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description
 * author stephen.cui
 * date 2017/4/8 16:49
 **/
@Service
public class TestServices {

    @Autowired
    private TestDao testDao;

    public String test(){
        return "hello world!";
    }

    public List<CityPOJO> getCity(int id) {
        return testDao.getCity(id);
    }

}
