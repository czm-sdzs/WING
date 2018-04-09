package com.wing.common;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Create By: CuiBo
 * Date: 2018/4/8 14:59
 * Description:
 */
public class Base64Cast {


    /**
     * @Author: CuiBo
     * @Description: bytes[]用base64编码返回String
     * @Params:
     * @Return:
     * @Date : 2018/3/12 11:39
     */

    public static String byteEncryptBASE64Str(byte[] bytes) {
        BASE64Encoder encoder = new BASE64Encoder();
        String encryptKey = encoder.encodeBuffer(bytes);
        String str = encryptKey.replaceAll("[\\t\\n\\r]", "");
        return str;
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
            keyBytes = (new BASE64Decoder()).decodeBuffer(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return keyBytes;
    }


    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
}
