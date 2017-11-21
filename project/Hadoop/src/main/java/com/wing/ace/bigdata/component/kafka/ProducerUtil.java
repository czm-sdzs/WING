package com.wing.ace.bigdata.component.kafka;

import kafka.admin.AdminUtils;
import kafka.admin.RackAwareMode;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.utils.ZkUtils;
import org.apache.kafka.common.security.JaasUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Created by Administrator on 2017/4/10.
 */
public class ProducerUtil extends Thread {
    private Logger log = LoggerFactory.getLogger(ProducerUtil.class);
    private kafka.javaapi.producer.Producer<String, String> producer = null;
    private static  Properties props = new Properties();
    private String topic="t15";

    static{
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("zk.connect", "localhost:4180,localhost:4181,localhost:4182");
        props.put("metadata.broker.list", "localhost:9090,localhost:9091,localhost:9092");
        props.put("num.partitions", "3");
        props.put("zookeeper.session.timeout.ms", "800000");
        //异步Produce
        //props.put("producer.type", "async");
    }

    public ProducerUtil(){
        synchronized(this){
            if(producer == null){
                producer = new kafka.javaapi.producer.Producer<String, String>(new ProducerConfig(props));
            }
            createTopic(topic);

        }

    }

    @Override
    public void run(){
        int messageNo = 1;
        while (true)
        {
            String messageStr = new String("Message_" + messageNo);
            System.out.println("Send:" + messageStr);
            producer.send(new KeyedMessage<String, String>(topic, messageStr));
            log.info("send ok!");
            messageNo++;
            try {
                sleep(3000);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
    }

    public synchronized void createTopic(String topic){
        ZkUtils zkUtils = ZkUtils.apply("localhost:4180,localhost:4181,localhost:4182", 30000, 30000, JaasUtils.isZkSecurityEnabled());
        // 创建一个单分区单副本名为t1的topic
        try{
            AdminUtils.createTopic(zkUtils, topic, 3, 3, new Properties(), RackAwareMode.Enforced$.MODULE$);
        }catch(Exception es){
            String str=es.toString();
            if(str.indexOf("already exists")!=-1){
                log.info(es+"Topic "+topic+" is already exists!");
            }
            System.out.println("----------------------"+es);
        }
        zkUtils.close();
    }

}
