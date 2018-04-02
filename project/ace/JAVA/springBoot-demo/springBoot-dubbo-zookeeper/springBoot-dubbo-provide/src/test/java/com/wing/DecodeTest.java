package com.wing;

import com.wing.apiImpl.cipher.DecodeServiceImpl;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Create By: CuiBo
 * Date: 2018/3/29 15:42
 * Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DecodeTest {

    @Resource
    private DecodeServiceImpl decodeService;


    @org.junit.Test
    public void test(){
        String htable = "dwd:md5_aes";
        String rowKey = "dr_ds_cry";
        String FAMILY = "fam";
        String QUALIFIER = "cry";

        String str = "{\n" +
                "\t\"data\":\"+XxHH2NLSluPLY2KLbYwMg==\",\n" +
                "\t\"user\":\"s1\",\n" +
                "\t\"code\":\"s11\"\n" +
                "}";
        String result  =  decodeService.decode(str);
        System.out.println(result);
    }
}
