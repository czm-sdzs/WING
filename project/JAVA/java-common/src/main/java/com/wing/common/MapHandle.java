package com.wing.common;

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
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis());
    }
}
