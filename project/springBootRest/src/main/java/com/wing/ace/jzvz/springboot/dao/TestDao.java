package com.wing.ace.jzvz.springboot.dao;

import com.wing.ace.jzvz.springboot.domain.CityPOJO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2017/4/8.
 */
@Mapper
public interface TestDao {

    public List<CityPOJO> getCity(int id);
}
