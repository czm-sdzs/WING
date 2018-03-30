package com.wing;

/**
 * Create By: CuiBo
 * Date: 2018/3/29 13:38
 * Description: 字符串处理
 */
public class StringHandle {

    public static void main(String[] args) {
        splitStr();
    }


    public static void splitStr(){
        String str = "s1 s11 2018-04-01,s2 s22 2018-04-01,s3 s33 2018-04-01";
        String[] strs = str.split(",");
        System.out.println(strs);
    }
}
