package com.wing.common.data_sign;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;

/**
 * Create By: CuiBo
 * Date: 2018/3/7 15:33
 * Description: RSA 处理公钥和私钥公用方法
 */
public class RSACommon {

    /**
     * @Author: CuiBo
     * @Description: 将公钥字符串转为PublicKey 对象
     * @Params: key 公钥字符串
     * @Return: PublicKey 公钥对象
     * @Date : 2018/3/7 11:25
     */
    public static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    /**
     * @Author: CuiBo
     * @Description: 将私钥字符串转为PrivateKey对象
     * @Params: key 私钥字符串
     * @Return: PrivateKey 私钥对象
     * @Date : 2018/3/7 11:26
     */

    public static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }


    /**
     * @Author: CuiBo
     * @Description: 用base64编码byte数组
     * @Params:
     * @Return:
     * @Date : 2018/3/7 11:28
     */

    public static String encryptBASE64(byte[] bytes) {
        BASE64Encoder encoder = new BASE64Encoder();
        String encryptKey = encoder.encodeBuffer(bytes);
        return encryptKey.replaceAll("[\\t\\n\\r]", "");
    }

    /**
     * @Author: CuiBo
     * @Description: 用base64编码string
     * @Params:
     * @Return:
     * @Date : 2018/3/7 11:27
     */

    public static byte[] dencryptBASE64( String str) {
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes = new byte[0];
        try {
            bytes = decoder.decodeBuffer(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }


    public static void main(String[] args) {
        //生成公钥私钥
        Map map = RSA.getMD5withRSAKey();
        System.out.println(map);
    }

}