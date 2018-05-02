package com.wing.common;/**
 * Created by Administrator on 2017/11/21.
 */

import com.google.gson.Gson;
import net.sf.json.JSONObject;


import java.io.*;
import java.util.*;

/**
 * description 类型转换
 * author stephen.cui
 * date 2017/11/21 23:47
 **/
public class TypeCast {

    private static final String SEP1 = " ";
    private static final String SEP2 = "|";
    private static final String SEP3 = "=";

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

    /**
     * @Author: CuiBo
     * @Description: base64编码的string转为byte[]
     * @Params:
     * @Return:
     * @Date : 2018/3/7 11:33
     */

    public static byte[] base64String2Byte(String str){
        byte[] keyBytes = new byte[0];
        try {
//            keyBytes = (new BASE64Decoder()).decodeBuffer(str);
            keyBytes = (Base64.getDecoder()).decode(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return keyBytes;
    }


    /**
     * @Author: CuiBo
     * @Description: bytes[]用base64编码返回String
     * @Params:
     * @Return:
     * @Date : 2018/3/7 11:39
     */

    public static String byteEncryptBASE64Str(byte[] bytes) {
//        BASE64Encoder encoder = new BASE64Encoder();
//        String encryptKey = encoder.encodeBuffer(bytes);

        Base64.Encoder encoder = Base64.getEncoder();
        String encryptKey = encoder.encodeToString(bytes);
        String str = encryptKey.replaceAll("[\\t\\n\\r]", "");
        return str;
    }


    /**
     * @Author: CuiBo
     * @Description: list<Map> 转为 byte
     * @Params:
     * @Return:
     * @Date : 2018/3/7 13:43
     */

    public static byte[] listMap2Byte(List<Map<Object,Object>> listMap)throws Exception{
        ByteArrayOutputStream bou = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bou);
        out.writeObject(listMap);
        out.flush();
        byte[] bytes = bou.toByteArray();
        out.close();
        bou.close();
        return bytes;

    }


    public static String listMap2String(List<Map> listMap)throws Exception{
        return null;
    }

    public static String listToString(List<?> list) {
        StringBuffer sb = new StringBuffer();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == null || list.get(i) == "") {
                    continue;
                }
                // 如果值是list类型则调用自己
                if (list.get(i) instanceof List) {
                    sb.append(listToString((List<?>) list.get(i)));
                    sb.append(SEP1);
                } else if (list.get(i) instanceof Map) {
                    sb.append(MapToString((Map<?, ?>) list.get(i)));
                    sb.append(SEP1);
                } else {
                    sb.append(list.get(i));
                    sb.append(SEP1);
                }
            }
        }
        return sb.toString();
    }


    /**
     * Map转换String
     *
     * @param map
     *            :需要转换的Map
     * @return String转换后的字符串
     */
    /**
     * Map转换String
     *
     * @param map
     *            :需要转换的Map
     * @return String转换后的字符串
     */
    public static String MapToString(Map<?, ?> map) {
        StringBuffer sb = new StringBuffer();
        // 遍历map
        for (Object obj : map.keySet()) {
            if (obj == null) {
                continue;
            }
            Object key = obj;
            Object value = map.get(key);
            if (value instanceof List<?>) {
                sb.append(key.toString() + SEP1 + listToString((List<?>) value));
                sb.append(SEP2);
            } else if (value instanceof Map<?, ?>) {
                sb.append(key.toString() + SEP1 + MapToString((Map<?, ?>) value));
                sb.append(SEP2);
            } else {
                sb.append(key.toString() + SEP3 + value.toString());
                sb.append(SEP2);
            }
        }
        return sb.toString();
    }
    /**
     * @Author: CuiBo
     * @Description: byte 转为 list<Map>
     * @Params:
     * @Return:
     * @Date : 2018/3/7 13:43
     */

    public List<Map> byte2ListMap(byte[] bytes)throws Exception{
        ByteArrayInputStream bin2 = new ByteArrayInputStream(bytes);
        ObjectInputStream in_ = new ObjectInputStream(bin2);
        // 新ArrayList
        List<Map> listMap = (List<Map>)in_.readObject();
//        ArrayList<HashMap<String, String>> list2 = (ArrayList<HashMap<String, String>>) in_.readObject();
        in_.close();
        bin2.close();
        System.out.println(listMap);
        return listMap;
    }



    public Map json2(String str){
        JSONObject object = JSONObject.fromObject(str);
        Map map = (Map)object;
        return map;
    }




    /**
     * @Author: CuiBo
     * @Description:
     * @Params:
     * @Return:
     * @Date : 2018/3/7 13:53
     */

    public static void main(String[] args) throws Exception{
        TypeCast test = new TypeCast();
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        System.out.println(list.getClass().getSimpleName());

//        //测试base64数据类型转换
//        String base64Str = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDf7icFVA7WNT/e6JnJt0iV4YoP1IjQoHGCZ5BOdYz27LYzQB8ymg4Bg1Pdm8pchHj9+3fwtIzxtaqLlGKONXnyM4YaNYPcskv0cKoyi2SGRqBz48df55AQcvmIzS0QQk0a8566tk4I7hNuS6Lz6Hs1uTN/4k+5z4uV/mcXk1DcgwIDAQAB";
//        System.out.println("转换前的base64字符串： "+base64Str);
//        byte[] bytes = test.base64String2Byte(base64Str);
//        String str = test.byteEncryptBASE64Str(bytes);
//        System.out.println("转换后的字符串: "+str);

        //测试byte和listMap互转
        //初始化
//        List<Map<Object,Object>> listMap = new ArrayList<>();
//        for(int i =0;i<3;i++){
//            Map map = new HashMap();
//            map.put(i,"value_"+i);
//            listMap.add(map);
//        }
//        byte[] bytes = test.listMap2Byte(listMap);
//        test.byte2ListMap(bytes);



//        //测试json转map
//        String jsonStr = "{\"data\":[{\"orgNo\":\"1\",\"calltime\":\"2018-03-07 12:12:12\",\"merchantNo\":\"No1\",\"consistent\":\"_1\"},{\"orgNo\":\"2\",\"calltime\":\"2018-03-07 12:12:13\",\"merchantNo\":\"No2\",\"consistent\":\"_2\"}],\"signData\":\"1111\",\"flag\":\"N\"}";
//        Map map = test.json2(jsonStr);
//        List<Map> data = (List<Map>)map.get("data");
//
//
//
//        System.out.println(data);
//        System.out.println(map);
    }

}
