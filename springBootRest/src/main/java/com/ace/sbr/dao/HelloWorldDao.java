package com.ace.sbr.dao;

import com.ace.sbr.domain.CityPOJO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2017/4/8.
 */
@Mapper
public interface HelloWorldDao {

    public List<CityPOJO> getCity(int id);
}
