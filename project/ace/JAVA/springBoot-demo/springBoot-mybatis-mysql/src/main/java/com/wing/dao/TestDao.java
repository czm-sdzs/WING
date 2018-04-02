package com.wing.dao;

import com.wing.domain.CityPOJO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2017/4/8.
 */
@Mapper
public interface TestDao {

    public List<CityPOJO> getCity(int id);
}
