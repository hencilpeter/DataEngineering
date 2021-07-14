/*
* Author : J Hencil Peter
* Program : simple kafka producer (fire and forget approach)
* */
package kafkaproducer.simple;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class producer_fire_and_forget {
    public static void main(String args[]){

        Properties kafkaProperties = new Properties();
        kafkaProperties.put("bootstrap.servers", "localhost:9092,localhost:9092");
        kafkaProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(kafkaProperties);
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>("kafka-topic-one-partition", "emp-key","John Mathew" );
        try{
            producer.send(producerRecord);
            producer.close();
        }
        catch(Exception exception){
            System.out.println("Exception raised while sending the message.");
            System.out.print("Exception : " + exception);
        }
        finally {
            System.out.println("Producer Ends!!!!!");
        }

    }
}
