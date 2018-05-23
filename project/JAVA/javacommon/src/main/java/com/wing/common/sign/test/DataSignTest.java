package com.wing.common.sign.test;//package com.wing.common.sign.test;
//
//import com.wing.common.sign.MD5withRSA;
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * Create By: CuiBo
// * Date: 2018/3/14 10:28
// * Description:
// */
//public class DataSignTest {
//
//    private static String PUBLICKEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCT6sjugHtyNS3wqkFF9GYU0l6VRy79hXyVT7QIO4CTE4XrNl80PFdmX3VNPvSSLHe0QJQA5znnWSVdtSQ0RNk6zFg8dQjvW3bQ+l1gwK4NeZND5s7mg5Ohf9L5fLoapQ7d3b5gDzSACuWuNTGW7hgZm17Wgh1jIZFnzyTRvKXF9wIDAQAB";
//    private static String PRIVATEKEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJPqyO6Ae3I1LfCqQUX0ZhTSXpVHLv2FfJVPtAg7gJMThes2XzQ8V2ZfdU0+9JIsd7RAlADnOedZJV21JDRE2TrMWDx1CO9bdtD6XWDArg15k0PmzuaDk6F/0vl8uhqlDt3dvmAPNIAK5a41MZbuGBmbXtaCHWMhkWfPJNG8pcX3AgMBAAECgYBSI5nq34wtkpxtPY47s4kQXPXK/Zl5jSvumjEGNUyj376JLEtX7X43SiEQgTo9BdZ/UTEfA0gjUVyvmcx9/hGGfXg8ji4jnCULzhrOEV0QbNZXDDqI7EeNy2HUOwyPJxV3bq3zAQF2EAkyLAVKFdquxWU7FSps+m+LJl4PozSlCQJBANJCrQRNcv4SpSFE/K1HR9g6/EqG6T4ShbFEwX4FSZBDmjSn0LsroPSuj3hJZVUCuFAuOfYLoLo/BybQrPpBArUCQQC0GDuxoEkajbkuNpoj8C6P2Fjsyrw0kcIiQKWgLG8U41w5K16feXZXnlx/arGWH6q3puzniHZn4YAxaXqIsjV7AkBifMziWQzmKgGjZdcdq2fhxy9qEWuroa1x7Yzc5MkihCf1Ri4lnjgUpLebVnXarh17oUuRDr1IwEEvU0vTJSSJAkEApQIbF5mjwXee9w/CVTFIiDWS7IqovIXcWOPgnQx5TqsuoZbyIK36VzdaysfoJIOpOJHyOAXZbz625Q8e3UZjJwJAWpQ6e9Lbhvbx68FAoTgr8zKecfAeTTchJGjJoainKSbxgZ21dKDf/XR37rkcZExWQq4aueg3TZbmjcxt1vw3iA==";
//
//    /**
//     * @Author: CuiBo
//     * @Description:
//     * @Params:
//     * @Return:
//     * @Date : 2018/3/14 15:37
//     */
//
//    public static Map initDataTest()throws Exception{
//        //要传输的明文
//        List<Map<Object,Object>> dataList = new ArrayList<>();
//        Map<String,Object> dataMap = new HashMap<>();
//        Map<String,String> authMap = new HashMap<>();
//        authMap.put("appId","sqb");
//        authMap.put("appKey","sqb_8ikl");
//        for(int i=0;i<2;i++){
//            HashMap<Object, Object> signMap = new HashMap<Object, Object>();
//            signMap.put("orgNo","sqb");
//            signMap.put("calltime","20180404170709");
//            signMap.put("trans_type","realTime");
//            signMap.put("merchantNum",-1);
//            signMap.put("merchantNo","025ec148-a1ed-4360-8d2a-729c6f1eed32");
//            signMap.put("merchantName","无色");
//            signMap.put("contactName","张");
//            dataList.add(signMap);
//        }
//        //摘要
//        JSONArray json = JSONArray.fromObject(dataList);
//        String contentStr = json.toString();//把json转换为String
//        System.out.println("需进行数字签名的原摘要："+contentStr);
//        //数字签名
//        String signData= MD5withRSA.getSign(contentStr,PRIVATEKEY);
//        System.out.println("数字签名："+signData);
//        dataMap.put("data",contentStr);//数据
//        dataMap.put("signData",signData);//数字签名
//        dataMap.put("flag","N");//是否回传标记
//        dataMap.put("auth",authMap);
//
//
//        return dataMap;
//    }
//
//    private static String send(){
//        String data = "";
//        try{
//            Map sendData = initDataTest();
//            JSONObject json = JSONObject.fromObject(sendData);
//            data = json.toString();
//            System.out.println("发送的数据为："+data);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return data;
//    }
//
//
//    /**
//     * @Author: CuiBo
//     * @Description: 验证发送的签名数据
//     * @Params:
//     * @Return:
//     * @Date : 2018/3/14 15:36
//     */
//
//    private static void receive(){
//        try{
//            String data = send();
//            Map<String,String> map = JSONObject.fromObject(data);
//            String content = map.get("data");
//            String signData = map.get("signData");
//            System.out.println("接收后明文数据"+content);
//            System.out.println("接收后数字签名"+signData);
//            boolean flag = MD5withRSA.getVerify(content,signData,PUBLICKEY);
//            System.out.println("结果："+flag);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }
//
//
//    /**
//     * @Author: CuiBo
//     * @Description: 简单测试签名和验签
//     * @Params:
//     * @Return:
//     * @Date : 2018/3/15 9:34
//     */
//
//    private static void simpleTest()throws Exception{
//        MD5withRSA md5withRSA = new MD5withRSA();
//        String src = "你好，我是原文！";
//        System.out.println("发送前明文数据:"+src);
//        String contentSignStr = md5withRSA.getSign(src,PRIVATEKEY);
//        System.out.println("签名后的数据："+contentSignStr);
//        //验证数字签名
//        boolean flag = md5withRSA.getVerify(src,contentSignStr,PUBLICKEY);
//        System.out.println("结果："+flag);
//    }
//
//
//
//
//    public static void main(String[] args) throws Exception{
//        //简单数据结构验证
////        simpleTest();
//
//        //复杂数据结构验证
//        receive();
//
//    }
//}