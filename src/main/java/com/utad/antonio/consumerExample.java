package com.utad.antonio;


import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;



/**
 * Created by aalcocer on 11/01/16.
 */
public class consumerExample {



    boolean loop = true;
    Properties props;
    public void consuming() {

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Arrays.asList("test1"));
        while (loop) {

            ConsumerRecords<String, String> records = consumer.poll(400);
            for (ConsumerRecord<String, String> record : records)
                System.out.println("offset = " +record.offset() + ", key = " + record.key() + ", value = " +
                    record.value());

        }
        consumer.close();
    }
    public consumerExample(Properties props) {
        this.props = props;
    }
}

