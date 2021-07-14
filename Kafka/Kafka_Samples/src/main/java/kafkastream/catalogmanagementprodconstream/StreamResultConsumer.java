package kafkastream.catalogmanagementprodconstream;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class StreamResultConsumer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers","localhost:9092, localhost:9093");
        properties.put("group.id", "grp-1");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.LongDeserializer");

        KafkaConsumer<String, Long> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Arrays.asList("kafka-topic-result"));
        //consumer.subscribe(Arrays.asList("kafka-topic-result-two-partitions"));

        try{
            long incomingMessageCount = 0;
            while(true){

                ConsumerRecords<String, Long> records = consumer.poll(1000);
                for(ConsumerRecord<String, Long> record:records){
                    //enable below line to print all messages
                    incomingMessageCount += record.value();
                    System.out.println("Key = " + record.key() + "value = " + record.value() + " total records : "+ incomingMessageCount);

//                    if (incomingMessageCount ==0 || (incomingMessageCount >= 10000  && incomingMessageCount < 11000 )||
//                            ( incomingMessageCount >=100000  && incomingMessageCount  < 10100)
//                            || (incomingMessageCount >= 500000 && incomingMessageCount < 501000) || incomingMessageCount >= 1000000 ){
//                        incomingMessageCount += record.value();
//                        System.out.println("Message Count #: " + incomingMessageCount + " timestamp : " + record.key());
//
//                    }
//                    else {
//                        incomingMessageCount += record.value();
//                    }
                }
            }
        }
        finally {
            consumer.close();
        }

    }
}
