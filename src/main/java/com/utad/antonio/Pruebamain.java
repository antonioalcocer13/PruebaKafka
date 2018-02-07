package com.utad.antonio;

import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.Properties;
import java.util.UUID;


public class Pruebamain {
    static String serverKafka = "localhost:9092";
    static String KafakSerializerClass = "kafka.serializer.StringEncoder";
    static String KafakValueSerializer = "org.apache.kafka.common.serialization.StringSerializer";
    static String KafakKeySerializer = "org.apache.kafka.common.serialization.StringSerializer";
    static String KafakValueDeserializer = "org.apache.kafka.common.serialization.StringDeserializer";
    static String KafakKeyDeserializer = "org.apache.kafka.common.serialization.StringDeserializer";
    static String consumerSesionTimeout = "30000";
    static String consumerAutoCommitInterval = "1000";
    static String consumerAutoCommitEnable = "true";
    static String consumerGruopId = "myconsumer";


    public static void main(String[] args) {
        long events = Long.parseLong(args[0]);
        long blocks1 = Long.parseLong(args[1]);
        Properties propsProcuct = new Properties();
        propsProcuct.put("bootstrap.servers", serverKafka);
        propsProcuct.put("serializer.class", KafakSerializerClass);
        propsProcuct.put("value.serializer", KafakValueSerializer);
        propsProcuct.put("key.serializer", KafakKeySerializer);
        Properties propsConsumer = new Properties();
        propsConsumer.put("bootstrap.servers", serverKafka);
        propsConsumer.put("group.id", consumerGruopId);
        propsConsumer.put("enable.auto.commit", consumerAutoCommitEnable);
        propsConsumer.put("auto.commit.interval.ms", consumerAutoCommitInterval);
        propsConsumer.put("session.timeout.ms", consumerSesionTimeout);
        propsConsumer.put("key.deserializer", KafakKeyDeserializer);
        propsConsumer.put("value.deserializer", KafakValueDeserializer);
        propsConsumer.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        propsConsumer.put(ConsumerConfig.GROUP_ID_CONFIG, UUID.randomUUID().toString());
        propsConsumer.put(ConsumerConfig.CLIENT_ID_CONFIG, "your_client_id");


        System.out.println("Empieza el programa");
        ProducerExample miproducer = new ProducerExample(events,blocks1,propsProcuct);
        miproducer.generate();
        System.out.println("ahora a consumir");
        consumerExample miconsumidor=new consumerExample(propsConsumer);
        miconsumidor.consuming();
        System.out.println("Acaba el programa");

    }
}
