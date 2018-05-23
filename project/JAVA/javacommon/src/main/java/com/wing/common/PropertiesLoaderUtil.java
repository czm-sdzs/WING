package com.wing.common;//package com.wing.common;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.BufferedInputStream;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.*;
//
///**
// * Create By: CuiBo
// * Date: 2018/3/12 10:58
// * Description: 读取配置文件
// */
//public class PropertiesLoaderUtil {
//    private static final Logger LOGGER  = LoggerFactory.getLogger(PropertiesLoaderUtil.class);
//
//
//   /**
//    * @Author: CuiBo
//    * @Description:  实时加载配置文件，修改后立即生效，不必重启
//    * @Params:
//    * @Return:
//    * @Date : 2018/3/12 14:05
//    */
//
//    public  Map getPropertyDynamic() {
//        String propertyFile = "F:/project/company/data_rish_control/dap/dap-gateway/dap-gateway-businessloan/src/main/resources/config/test/sqb.properties";
//        Map map = new HashMap();
//        Properties prop = new Properties();
//        try {
//            //获取最新的
//            // 生成输入流
//            InputStream in = new BufferedInputStream(new FileInputStream(propertyFile));
//            // 生成properties对象
//            prop.load(in);     ///加载属性列表
//            Iterator<String> it=prop.stringPropertyNames().iterator();
//            while(it.hasNext()){
//                String key=it.next();
//                map.put(key.toString(), prop.getProperty(key));
//            }
//            in.close();
//        } catch (IOException e) {
//            LOGGER.error("get Property error:{}",e);
//        }
//        return map;
//    }
//
//
//    /**
//     * @Author: CuiBo
//     * @Description: 静态获取
//     * @Params:
//     * @Return:
//     * @Date : 2018/3/12 17:31
//     */
//    public Map getProtertyStatic(){
//        Map map = new HashMap();
//        Properties prop = new Properties();
//        try {
//            InputStream in = PropertiesLoaderUtil.class.getClassLoader().getResourceAsStream("application.properties");
//            // 生成properties对象
//            prop.load(in);     ///加载属性列表
//            Iterator<String> it=prop.stringPropertyNames().iterator();
//            while(it.hasNext()){
//                String key=it.next();
//                map.put(key.toString(), prop.getProperty(key));
//            }
//            in.close();
//        } catch (IOException e) {
//            LOGGER.error("get Property error:{}",e);
//        }
//        LOGGER.info("config.properties:{}",map.toString());
//        return map;
//    }
//
//    /**
//     * 读取c3p0
//     * @return
//     */
//    public Hashtable getC3p0Property(){
//        Hashtable hashtable = new Hashtable();
//        Properties p = new Properties();
//        try {
//            InputStream is=Thread.currentThread().getContextClassLoader().getSystemResourceAsStream("c3p0.properties");
//            p.load(is);
//            hashtable = (Hashtable)p;
//            LOGGER.info(p.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return hashtable;
//    }
//
//}
