package kafkastream.statefultransformation;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class ResultConsumer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers","localhost:9092, localhost:9093");
        properties.put("group.id", "grp-1");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.LongDeserializer");
        //properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, Long> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Arrays.asList("kafka-topic-result"));

        try{
            while(true){
                ConsumerRecords<String, Long> records = consumer.poll(1000);
                for(ConsumerRecord<String, Long> record:records){
                    System.out.println("Key = " + record.key() + "value = " + record.value());
                }
            }
        }
        finally {
            consumer.close();
        }

    }
}


