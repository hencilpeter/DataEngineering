/*
 * Author : J Hencil Peter
 * Program : single threaded kafka producer (Asynchronous approach)
 * Purpose : This program is created to test the consumer samples.
 * */
package kafkaconsumer.order_catalog;
import org.apache.kafka.clients.producer.*;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Timestamp;
import java.util.Properties;


public class producer_single_thread_async_approach {


    public static void main(String args[]) {
         //String kafkaTopic = "kafka-topic-one-partition";
        //String kafkaTopic = "kafka-topic-two-partitions";
        //String kafkaTopic = "kafka-topic-three-partitions";
        String kafkaTopic = "kafka-topic-four-partitions";
        //String kafkaTopic = "kafka-topic-five-partitions";
        //String kafkaTopic = "kafka-topic-six-partitions";

        Properties kafkaProperties = new Properties();
        kafkaProperties.put("bootstrap.servers", "localhost:9092,localhost:9093");
        kafkaProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(kafkaProperties);


        try{
            System.out.println(new File("").getAbsolutePath());
            ObjectMapper objectMapper = new ObjectMapper();
            try(BufferedReader br = new BufferedReader(new FileReader("..//data//products.csv"))) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                System.out.println("Start time : " + timestamp);
                String line = br.readLine();

                long currentLineNumber = 1;
                while (line != null){
                    String[] arrString = line.split(",");
                    //construct objet
                    CatalogDetail catalogDetail = new CatalogDetail(arrString[0],arrString[1],arrString[2],arrString[3],
                            arrString[4],arrString[5],arrString[6],arrString[7],arrString[8],arrString[9],
                            arrString[10], arrString[11],arrString[12]);
                    String jsonString = objectMapper.writeValueAsString(catalogDetail);

                    //send the json message to the producer
                    final ProducerRecord<String, String> producerRecord = new ProducerRecord<>(kafkaTopic, "", jsonString);

                    producer.send(producerRecord, new ProducerCallback());

                    if (currentLineNumber == 10000 || currentLineNumber == 100000
                            || currentLineNumber == 500000 || currentLineNumber == 1000000 ){
                        timestamp = new Timestamp(System.currentTimeMillis());
                        System.out.println("Line #: " + currentLineNumber + " timestamp : " + timestamp);

                    }
                    currentLineNumber++;
                    line = br.readLine();
                }

                timestamp = new Timestamp(System.currentTimeMillis());
                System.out.println("Line #: " + currentLineNumber + " timestamp : " + timestamp);
            }
            catch(Exception exception){
                System.out.println("Exception : " + exception);
            }


            producer.close();
        }
        catch(Exception exception){
            System.out.println("Exception raised while sending the message.");
            System.out.print("Exception : " + exception);
        }
    }

}


