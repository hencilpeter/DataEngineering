/*
 * Author : J Hencil Peter
 * Program : Standalone consumer with Asynchronous commit.
 * */

package kafkaconsumer.order_catalog;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class consumer_commit_async {
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

        if (partitionList != null){
            //extract partition list for the given topic
            for(PartitionInfo partitionInfo:consumer.partitionsFor(topicName))
                partitionList.add(new TopicPartition(topicName, partitionInfo.partition()));

            //assign partition list to the consumer
            consumer.assign(partitionList);
        }
        int recordCount = 0;
        while(true){
            ConsumerRecords<String, String> records = consumer.poll(1000);
            for(ConsumerRecord<String, String> record: records){
                System.out.printf("\ntopic name : %s, partition = %s, offset = %d, record key = %s, record value = %s",
                        record.topic(), record.partition(), record.offset(), record.key(), record.value());

                recordCount++;
            }

            consumer.commitAsync(new consumer_callback());
        }

    }
}
