/*
 * Author : J Hencil Peter
 * Program : simple kafka producer (synchronous approach)
 * */
package kafkaproducer.simple;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

public class producer_sync_approach {

    private static void printMetaData(final RecordMetadata metadata){
        System.out.println("Message written Topic : "+ metadata.topic());
        System.out.println("Partition : "+ metadata.partition());
        System.out.println("Offset : "+ metadata.offset());
    }

    public static void main(String args[]){
        Properties kafkaProperties = new Properties();
        kafkaProperties.put("bootstrap.servers", "localhost:9092,localhost:9093");
        kafkaProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(kafkaProperties);

        try{
            for(int count=1;count <= 10; count++){
                final ProducerRecord<String, String> producerRecord = new ProducerRecord<>("kafka-topic-one-partition", "emp-key","John Mathew" + count);
                RecordMetadata metadata = producer.send(producerRecord).get();

                System.out.print("producer sent message " + count + " and received ACK.");
                printMetaData(metadata);
            }

            producer.close();
        }
        catch(Exception exception){
            System.out.println("Excelption raised while sending the message.");
            System.out.print("Exception : " + exception);
        }

    }

}
