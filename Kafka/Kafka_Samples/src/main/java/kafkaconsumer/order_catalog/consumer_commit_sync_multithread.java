/*
 * Author : J Hencil Peter
 * Program : Consumer class with Synchronous commit with multithreading capability.
 * Purpose : This program can be used as single thread as well as multi thread.
 * */

package kafkaconsumer.order_catalog;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class consumer_commit_sync_multithread {
    public static Properties getKafkaProperties(){
        Properties properties = new Properties();
        properties.put("group.id", "kafka-group");
        properties.put("bootstrap.servers","localhost:9092, localhost:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        return properties;
    }
    public static void main(String args[]) throws Exception {
        //String topicName = "kafka-topic-one-partition";
        //String topicName = "kafka-topic-two-partitions";
        //String topicName = "kafka-topic-three-partitions";
        //String topicName = "kafka-topic-four-partitions";
        String topicName = "kafka-topic-five-partitions";
        //String topicName = "kafka-topic-six-partitions";
        Properties properties = getKafkaProperties();
        Consumer<String, String> consumer = new KafkaConsumer<>(properties);
        List<TopicPartition> partitionList = new ArrayList<>();

        if (partitionList != null){
            //extract partition list for the given topic
            for(PartitionInfo partitionInfo:consumer.partitionsFor(topicName))
                partitionList.add(new TopicPartition(topicName, partitionInfo.partition()));

            //assign partition list to the consumer
            //consumer.assign(partitionList);
        }


        //dispatcher objects for each thread
        int countPartition = partitionList.size();
        Thread[] threadDispatchers = new Thread[countPartition];
        for (int count = 0; count < countPartition; count++) {

            //create mew consumer for the thread
            Consumer<String, String> threadConsumer = new KafkaConsumer<String, String>(properties);

            //assign the partition to the consumer
            threadConsumer.assign(Arrays.asList(partitionList.get(count)));

            threadDispatchers[count] = new Thread(new consumer_dispatcher_commit_sync(threadConsumer, partitionList.get(count)));

            //start the thread
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
            //clean-up code
        }

    }
}
