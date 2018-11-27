package com.wing.common;

import com.alibaba.fastjson.JSON;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Create By: CuiBo
 * Date: 2018/4/4 9:17
 * Description: map
 */
public class MapHandle {

    public void  TestMap(){
        Map<String,String> mapTest = new HashMap<>();

        Set<Map.Entry<String,String>> entrySet=mapTest.entrySet();
        for(Map.Entry<String, String> entry:entrySet){
            String key=entry.getKey();
            String value=entry.getValue();
        }
    }


    public static void main(String[] args) {
        str2map();
    }


    public static void str2map(){
        String str="{\"auth\":{\"appId\":\"DC-DS\",\"appKey\":\"123456\"},\"data\":{\"AES\":[\"123\",\"abc\"],\"MD5\":[\"13176564356\",\"13476587699\"]}}";
        Map map = com.alibaba.fastjson.JSONObject.parseObject(str,Map.class);
        System.out.println(map);
    }


}
