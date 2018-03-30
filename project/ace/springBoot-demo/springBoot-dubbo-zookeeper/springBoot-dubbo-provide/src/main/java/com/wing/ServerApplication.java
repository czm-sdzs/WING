package com.wing;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Create By: CuiBo
 * Date: 2018/3/26 11:12
 * Description: 服务启动入口
 */
@SpringBootApplication
@org.springframework.context.annotation.Configuration
public class ServerApplication {

    @Value("${hbase.quorum}")
    private String QUORUM;

    private static Logger LOGGER = LoggerFactory.getLogger(ServerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
        LOGGER.info("Provider Application start success! ......");
    }

    /**
     * @Author: CuiBo
     * @Description: 初始话就处理
     * @Params:
     * @Return:
     * @Date : 2018/3/30 18:02
     */

    @Bean
    public Configuration configuration() {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", QUORUM);
        return conf;
    }
}
