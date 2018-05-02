//package com.wing.ace.kafka;
//
//
//import kafka.producer.KeyedMessage;
//import kafka.producer.ProducerConfig;
//
//import java.util.Properties;
//
///**
//* @author leicui bourne_cui@163.com
//*/
//public class KafkaProducer extends Thread
//{
////    private final kafka.javaapi.producer.Producer<Integer, String> producer;
//	private final kafka.javaapi.producer.Producer<String, String> producer;
//    private final String topic;
//    private final Properties props = new Properties();
//
//    public KafkaProducer(String topic)
//    {
//        props.put("serializer.class", "kafka.serializer.StringEncoder");
//        props.put("zk.connect", "10.200.0.93:2182,10.200.0.93:2183,10.200.0.93:2184");
//        props.put("metadata.broker.list", "10.200.0.93:9093,10.200.0.93:9094,10.200.0.93:9095");
//        props.put("num.partitions", "3");
//
//        //异步Produce
//        //props.put("producer.type", "async");
//
//        producer = new kafka.javaapi.producer.Producer<String, String>(new ProducerConfig(props));
////        producer = new kafka.javaapi.producer.Producer<Integer, String>(new ProducerConfig(props));
//
//        this.topic = topic;
//    }
//
//    @Override
//    public void run() {
//        int messageNo = 1;
//        while (true)
//        {
//            String messageStr = new String("Message_" + messageNo);
//            System.out.println("Send:" + messageStr);
////            producer.send(new KeyedMessage<Integer, String>(topic, messageStr));
//            producer.send(new KeyedMessage<String, String>(topic, messageStr));
//            messageNo++;
//            try {
//                sleep(3000);
//            } catch (InterruptedException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//    }
//}
