/*
 * Author : J Hencil Peter
 * Program : simple kafka producer (Asynchronous approach)
 * */
package producers.simple;
import org.apache.kafka.clients.producer.*;

import java.util.Properties;


public class producer_async_approach {


    public static void main(String args[]) {
        Properties kafkaProperties = new Properties();
        kafkaProperties.put("bootstrap.servers", "localhost:9092,localhost:9093");
        kafkaProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(kafkaProperties);

        try {
            for (int count = 1; count <= 10; count++) {
                final ProducerRecord<String, String> producerRecord = new ProducerRecord<>("kafka-topic-one-partition", "emp-key", "John Mathew" + count);
                    producer.send(producerRecord, new ProducerCallback());

                System.out.println("Producer sent asynchronous message :  " + count );
            }
            producer.close();
        }
        catch(Exception exception){
            System.out.println("Exception raised while sending the message.");
            System.out.print("Exception : " + exception);
        }
    }

}

class ProducerCallback implements Callback{
    private  void printMetaData(final RecordMetadata metadata){
        System.out.println("Message written Topic : "+ metadata.topic());
        System.out.println("Partition : "+ metadata.partition());
        System.out.println("Offset : "+ metadata.offset());
    }
    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception exception){
        if (exception!= null) {
            System.out.println("Asynchronous producer failed  with exception.");
            System.out.println("Exception : " + exception);
        }
        else{
            System.out.print("Asynchronous producer succeeded!!!");
            printMetaData(recordMetadata);
        }
    }
}
