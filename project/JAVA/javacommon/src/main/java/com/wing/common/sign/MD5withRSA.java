package com.wing.common.sign;//package com.wing.common.sign;
//
//import com.wing.common.Base64Cast;
//
//import javax.crypto.Cipher;
//import java.security.MessageDigest;
//import java.security.PrivateKey;
//import java.security.PublicKey;
//
///**
// * Create By: CuiBo
// * Date: 2018/3/12 10:07
// * Description:
// */
//public class MD5withRSA {
//    /**
//     * @Author: CuiBo
//     * @Description: 使用base64编译数字签名
//     * @Params: contentBytes
//     * @Return:
//     * @Date : 2018/3/8 13:52
//     */
//
//    public static String getSign(String contentBytes, String privateKeyStr) throws Exception {
//        byte[] contentSignBytes = sign(contentBytes.getBytes(), privateKeyStr);
//        String contentSignStr = Base64Cast.byteEncryptBASE64Str(contentSignBytes);
//        return contentSignStr;
//    }
//
//
//    /**
//     * @Author: CuiBo
//     * @Description: 生成数字签名
//     * @Params:
//     * @Return:
//     * @Date : 2018/3/7 17:00
//     */
//
//    private static byte[] sign(byte[] conentBytes, String privateKeyStr) throws Exception {
//        PrivateKey privateKey = RSACommon.getPrivateKey(privateKeyStr);
//        //生成数字摘要
//        MessageDigest md = MessageDigest.getInstance("MD5");
//        byte[] md5Bytes = md.digest(conentBytes);
//
//        //对摘要进行私钥加密
//        Cipher cipher = Cipher.getInstance("RSA");
//        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
//        byte[] encrytBytes = cipher.doFinal(md5Bytes);
//        //这就是最后生成的签名
//        return encrytBytes;
//    }
//
//    /**
//     * @Author: CuiBo
//     * @Description:
//     * @Params:
//     * @Return:
//     * @Date : 2018/3/13 14:08
//     */
//
//    public static boolean getVerify(String content, String signData, String publicKeyStr) throws Exception {
//        //将传输的原摘要处理为byte[]
//        byte[] contentBytes = content.getBytes();
//        //数字签名使用base64转为byte[]
//        byte[] signDataBytes = Base64Cast.base64String2Byte(signData);
//        return verify(contentBytes, signDataBytes, publicKeyStr);
//    }
//
//
//    /**
//     * @Author: CuiBo
//     * @Description: 验证数字签名
//     * @Params:
//     * @Return:
//     * @Date : 2018/3/12 17:32
//     */
//    private static boolean verify(byte[] contentBytes, byte[] signData,
//                                  String publicKeyStr) throws Exception {
//        PublicKey publicKey = RSACommon.getPublicKey(publicKeyStr);
//        //首先对签名进行解密
//        Cipher cipher = Cipher.getInstance("RSA");
//        cipher.init(Cipher.DECRYPT_MODE, publicKey);
//        byte[] decrytBytes = cipher.doFinal(signData);    //这是解密开的数字摘要
//
//        //接下来对明文进行摘要
//        MessageDigest md = MessageDigest.getInstance("MD5");
//        byte[] srcBytes = md.digest(contentBytes);  //这是原数字摘要
//
//        //比较两个摘要是否相同
//        //将字节数据编码成Base64再进行字符串比较
//        //原文摘要
//        String decrytStr = RSACommon.encryptBASE64(decrytBytes);
//        String srcStr = RSACommon.encryptBASE64(srcBytes);
//        //数字摘要
//        if (decrytStr.equals(srcStr)) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//}
