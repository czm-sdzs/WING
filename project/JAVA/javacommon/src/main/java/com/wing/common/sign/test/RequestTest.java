package com.wing.common.sign.test;//package com.wing.common.sign.test;
//
//import com.wing.http.HttpRequest;
//import net.sf.json.JSONObject;
//
//import java.util.Map;
//
///**
// * Create By: CuiBo
// * Date: 2018/4/8 15:00
// * Description:
// */
//public class RequestTest {
//
//
//    public static void main(String[] args) {
//        try {
//            testPost();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    private static void testPost() throws Exception {
////        String url = "http://localhost:8000/Businessloans/merchant/merchantInfo";
//        String url = "http://58.250.161.51:9994/Businessloans/merchant/merchantInfo";
//        Map sendData = DataSignTest.initDataTest();
//        JSONObject json = JSONObject.fromObject(sendData);
//        String  papams = json.toString();
//
//        HttpRequest.post(url,papams);
//
//    }
//
//}
