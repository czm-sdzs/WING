package com.wing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Create By: CuiBo
 * Date: 2018/3/23 17:54
 * Description: 服务入口
 */
@SpringBootApplication
@ServletComponentScan
public class ClientApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
        LOGGER.info("consumer Application start success! ......");
    }
}
