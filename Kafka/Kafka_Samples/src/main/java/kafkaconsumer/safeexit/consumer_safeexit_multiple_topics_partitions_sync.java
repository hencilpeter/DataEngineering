/*
 * Author : J Hencil Peter
 * Program : Kafka consumer to consume keywords (synchronous approach)
 * This consumer program has been created to test the safe exist during the JVM shutdown.
 * */

package kafkaconsumer.safeexit;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.WakeupException;

import java.util.*;

public class consumer_safeexit_multiple_topics_partitions_sync {
    public static Properties getKafkaProperties() {
        Properties properties = new Properties();
        properties.put("group.id", "kafka-group");
        properties.put("bootstrap.servers", "localhost:9092, localhost:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("enable.auto.commit", "false");
        return properties;
    }

    public static void main(String args[]) throws Exception {

        //List<String> topicNames = List.of("kafka-topic-one-partition"); // receive message to one topic
        //List<String> topicNames = List.of("kafka-topic-one-partition", "kafka-topic-two-partitions"); // receive message to two topics
        List<String> topicNames = Arrays.asList("kafka-topic-one-partition", "kafka-topic-two-partitions", "kafka-topic-three-partitions"); // receive message to three topics

        Properties properties = getKafkaProperties();
        Consumer<String, String> consumer = new KafkaConsumer<>(properties);
        List<TopicPartition> partitionList = new ArrayList<>();

        //add all the topics partitions list to the consumer
        if (partitionList != null) {
            //process list of topics
            for (String topic : topicNames) {
                //process partition list for the current topic
                for (PartitionInfo partitionInfo : consumer.partitionsFor(topic))
                    partitionList.add(new TopicPartition(topic, partitionInfo.partition()));
            }
            //assign partition list to the consumer
            consumer.assign(partitionList);
        }

        //thread to handle shutdown
        Thread mainThead = Thread.currentThread();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("Exit message received. Consumer Wakeup is being called...");
                consumer.wakeup();

                try {
                    mainThead.join();
                } catch (InterruptedException exception) {
                    System.out.println("Interrupted Exception raised. exception ==> " + exception);
                }
            }
        });

        final Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<>();
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(1000);
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("\ntopic name : %s, partition = %s, offset = %d, record key = %s, record value = %s",
                            record.topic(), record.partition(), record.offset(), record.key(), record.value());
                    currentOffsets.put(new TopicPartition(record.topic(), record.partition()),
                            new OffsetAndMetadata(record.offset() + 1, "no metadata"));
                }

                if (!currentOffsets.isEmpty()) {
                    try {
                        consumer.commitSync(currentOffsets);
                        currentOffsets.clear();
                    } catch (Exception exception) {
                        System.out.println("Error in committing the offset. exception : " + exception);
                    }
                }
            }
        } catch (WakeupException exception) {

            System.out.println("SafeExit Exception triggered....");
            if (!currentOffsets.isEmpty()) {
                boolean isSafeExitCommitRequired = true;
                //call the external storage to double confirm the currentOffset is not processed
                //e.g isSafeExitCommitRequired = IsCurrentOffsetProcessed(currentOffsets);
                //if the above call returns true, safe exit commit performed
                if (isSafeExitCommitRequired) {
                    System.out.println("Safe Exit Commit Performed....");
                    consumer.commitSync(currentOffsets);
                    currentOffsets.clear();
                }

            } else {
                System.out.println("SafeExist doesn't require to perform commit.....");
            }
        } finally {
            consumer.close();
        }
    }
}
