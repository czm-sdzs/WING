package com.wing.common;

import com.alibaba.fastjson.JSON;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create By: CuiBo
 * Date: 2018/3/29 13:38
 * Description: 字符串处理
 */
public class StringHandle {

    public static void main(String[] args) {
        json();
    }


    public static void splitStr(){
        String str = "s1 s11 2018-04-01,s2 s22 2018-04-01,s3 s33 2018-04-01";
        String[] strs = str.split(",");
        System.out.println(strs);
    }


    public static void json(){
        List<Double> list = new ArrayList<>();
        double a = 15000000;
        list.add(a);
        String str= JSON.toJSONString(list);
        System.out.println(str);
        Map<String,String> map = new HashMap<>();
        map.put("sdsd",str);
        JSONObject json = JSONObject.fromObject(map);
        System.out.println(json);

    }
}
