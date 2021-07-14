/*
 * Author : J Hencil Peter
 * Program : multi threaded  kafka producer with timer (synchronous approach)
 * */
package kafkaproducer.timertask;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

public class producer_multi_thread_sync_approach {

    private static void printMetaData(final RecordMetadata metadata) {
        System.out.println("Message written Topic : " + metadata.topic());
        System.out.println("Partition : " + metadata.partition());
        System.out.println("Offset : " + metadata.offset());
    }

    public static void main(String args[]) {
        //String kafkaTopic = "kafka-topic-one-partition";
        String kafkaTopic = "kafka-topic-two-partitions";
        Properties kafkaProperties = new Properties();
        kafkaProperties.put("bootstrap.servers", "localhost:9092,localhost:9093");
        kafkaProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(kafkaProperties);

        String[] fileList = new String[2];
        fileList[0] = "..//data//products-1.csv";
        fileList[1] = "..//data//products-2.csv";

        //dispatcher objects for each thread
        Thread[] threadDispatchers = new Thread[fileList.length];
        for (int count = 0; count < fileList.length; count++) {
            threadDispatchers[count] = new Thread(new DispatcherSync(producer, kafkaTopic, fileList[count]));
            threadDispatchers[count].start();
        }

        try {
            for (Thread thread : threadDispatchers) {
                thread.join();
            }

        } catch (Exception exception) {
            System.out.println("Exception raised from  main....");
            System.out.print("Exception : " + exception);
        } finally {
            producer.close();
        }

    }

}