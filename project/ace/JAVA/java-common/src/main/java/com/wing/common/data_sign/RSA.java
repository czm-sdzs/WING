package com.wing.common.data_sign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * Create By: CuiBo
 * Date: 2018/3/7 9:23
 * Description: 使用MD5withRSA 方式获取公钥和私钥
 */
public class RSA {
    private static Logger LOGGER = LoggerFactory.getLogger(RSA.class);
    private KeyPair keyPair;
    private RSA rsa=null;


    /**
     * 构造RSA对象，初始化keyPair
     * @throws Exception
     */
    private RSA() throws Exception{
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        keyPair = keyPairGenerator.generateKeyPair();
    }

    /**
     * @Author: CuiBo
     * @Description: 获取生成公钥和私钥的对象RSA
     * @Params:
     * @Return:
     * @Date : 2018/3/7 16:21
     */
    public static RSA getRSA(){
        RSA rsa = null;
        try {
            rsa = new RSA();
        } catch (Exception e) {
            LOGGER.error("初始化出错.error:{}",e);
        }
        return rsa;
    }

   /**
    * @Author: CuiBo
    * @Description: 获取公钥对象
    * @Params:
    * @Return:
    * @Date : 2018/3/7 16:23
    */

    private PublicKey getPublicKey() {
        return keyPair.getPublic();
    }

    /**
     * @Author: CuiBo
     * @Description: 获取私钥对象
     * @Params:
     * @Return:
     * @Date : 2018/3/7 16:24
     */

    private PrivateKey getPrivateKey() {
        return keyPair.getPrivate();
    }


/**
 * @Author: CuiBo
 * @Description: 获取BASE64编译后的公钥和私钥
 * @Params:
 * @Return:
 * @Date : 2018/3/7 16:38
 */

    public static Map<String,String> getMD5withRSAKey(){
        //生成公钥私钥，用于生成数字签名
        Map<String,String> map = new HashMap<>();//存放生成的公钥和私钥字符串
        RSA rsa = RSA.getRSA();
        PublicKey publicKey = rsa.getPublicKey();
        PrivateKey privateKey = rsa.getPrivateKey();
        String publicKeyStr = RSACommon.encryptBASE64(publicKey.getEncoded());
        String privateKeyStr = RSACommon.encryptBASE64(privateKey.getEncoded());
        map.put("publicKey",publicKeyStr);
        map.put("privateKey",privateKeyStr);
        return map;
    }

}
