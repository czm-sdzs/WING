package com.wing.ace.common;/**
 * Created by Administrator on 2017/11/21.
 */

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * description 类型转换
 * author stephen.cui
 * date 2017/11/21 23:47
 **/
public class TypeCast {

    /**
     * String的类型转换为map
     * @param str
     * @return
     */
    public Map<String,Object> string2Map1(String str){
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap<String,Object>();
        //字符串中“：”会报错
//        map = gson.fromJson(str,new TypeToken<Map<String,Object>>(){}.getType());
        map = gson.fromJson(str,map.getClass());
        return map;
    }
    /**
     * String的类型转换为map
     * @param str
     * @return
     */
    public Map<String,Object> string2Map2(String str){
        JSONObject jb = JSONObject.fromObject(str);
        Map map = (Map)jb;
        return map;
    }

    /**
     * 特殊处理
     * String的类型转换为map
     * @param str
     * @return
     */
    public Map<String,Object> string2Map3(String str){
        Map<String,Object> map = new HashMap<String,Object>();
        String[] strs = str.split(",");
        for(String item: strs){
            if(item.indexOf("=") !=-1){
                String[] pros = item.split("=");
                map.put(pros[0],pros[1]);
            }
        }
        return map;
    }
}
