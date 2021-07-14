/*
 * Author : J Hencil Peter
 * Program : simple kafka producer to send keywords to kafka(synchronous approach)
 * This producer program has been created to read the keywords list and send to kafka topic.
  * */
package studytest;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class producer_keyword_sync_approach {

    private static void printMetaData(final RecordMetadata metadata) {
        System.out.println("Message written Topic : " + metadata.topic());
        System.out.println("Partition : " + metadata.partition());
        System.out.println("Offset : " + metadata.offset());
    }

    public static void main(String args[]) {
        System.out.println("Producer started....");
        Properties kafkaProperties = new Properties();
        kafkaProperties.put("bootstrap.servers", "localhost:9092,localhost:9093");
        kafkaProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(kafkaProperties);

        List<String> topicNames =  Arrays.asList("kafka-topic-one-partition"); // send message to one topic
        //List<String> topicNames = List.of("kafka-topic-one-partition", "kafka-topic-two-partitions"); // send message to two topics
        //List<String> topicNames = List.of("kafka-topic-one-partition", "kafka-topic-two-partitions", "kafka-topic-three-partitions"); // send message to three topics

        System.out.println("Producer is about to send the keywords....");
        long currentLineNumber = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("..//data//keywords_connect.csv"))) {
            String currentKeyword = br.readLine();

            while (currentKeyword != null) {

                //send messages to all the topics one by one
                for (String topic:topicNames){
                    final ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, "", currentKeyword);

                    RecordMetadata metadata = producer.send(producerRecord).get();
                }
                currentLineNumber++;
                System.out.println(MessageFormat.format("keyword Number {0}  Name {1}", currentLineNumber, currentKeyword));
                currentKeyword = br.readLine();
            }

        } catch (Exception exception) {
            System.out.println("Exception while processing keyword file. Exception : " + exception);
        } finally {
            System.out.println(MessageFormat.format("Producer has sent {0} keywords successfully.", currentLineNumber));
            producer.close();
        }
    }
}