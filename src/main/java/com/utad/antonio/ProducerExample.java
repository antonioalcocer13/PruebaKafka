package com.utad.antonio;


import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

//import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.tools.ConsoleProducer;

/**
 * Created by aalcocer on 11/01/16.
 */
public class ProducerExample {

    long events ;
    long blocks1 ;


    Properties props ;


    Producer<String, String> producer;

    public void generate()
    { Random rnd = new Random();
        producer = new KafkaProducer(props);

        for (long nBlocks = 0; nBlocks < blocks1; nBlocks++) {
            for (long nEvents = 0; nEvents < events; nEvents++) {

                long runtime = new Date().getTime();
                String msg = runtime + "," + (50 + nBlocks) + "," + nEvents + "," + rnd.nextInt(1000);
                int key = rnd.nextInt(100);

                ProducerRecord<String, String> data = new ProducerRecord<String, String>("test2",
                        Integer.toString(key), msg);
                producer.send(data);
            }
        }
        producer.close();
    }
    public ProducerExample(long events, long blocks1, Properties props) {
        this.events = events;
        this.blocks1 = blocks1;
        this.props = props;
    }


}

