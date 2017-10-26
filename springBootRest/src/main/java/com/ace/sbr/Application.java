package com.ace.sbr;/**
 * Created by Administrator on 2017/3/29.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

/**
 * description 启动类
 * author stephen.cui
 * date 2017/3/29 23:29
 **/
@SpringBootApplication
//@ComponentScan
//@RestController
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
