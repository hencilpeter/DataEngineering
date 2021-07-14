/*
* Author : J Hencil Peter
* Description : Simple Kafka Standalone consumer (Commit AsyncSync Approach)
* */
package kafkaconsumer.simple;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class consumer_commit_async_sync {
    public static Properties getKafkaProperties(){
        Properties properties = new Properties();
        properties.put("group.id", "kafka-group");
        properties.put("bootstrap.servers","localhost:9092, localhost:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        return properties;
    }

    public static void main(String args[]) throws Exception {
        String topicName = "kafka-topic-one-partition";
        Properties properties = getKafkaProperties();
        Consumer<String, String> consumer = new KafkaConsumer<>(properties);
        List<TopicPartition> partitionList = new ArrayList<>();

        if (partitionList != null) {
            //extract partition list for the given topic
            for (PartitionInfo partitionInfo : consumer.partitionsFor(topicName))
                partitionList.add(new TopicPartition(topicName, partitionInfo.partition()));

            //assign partition list to the consumer
            consumer.assign(partitionList);
        }
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(1000);
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("\ntopic name : %s, partition = %s, offset = %d, record key = %s, record value = %s",
                            record.topic(), record.partition(), record.offset(), record.key(), record.value());
                }

                consumer.commitAsync(new consumer_callback());
            }

        } catch (Exception exception) {
            System.out.println("Exception raised : "+ exception);
        }
        finally {
            try
            {
                consumer.commitSync();
            }
            catch (Exception exception){
                System.out.println("Exception raised during sync commit..." + exception);
            }
            finally {
                consumer.close();
            }
        }
    }
}
