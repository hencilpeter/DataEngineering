/*
 * Author : J Hencil Peter
 * Program : single threaded kafka producer with timer (Asynchronous approach)
 * */
package kafkaproducer.timertask;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.Timer;


public class producer_single_thread_async_approach {


    public static void main(String args[]) {
        //String kafkaTopic = "kafka-topic-one-partition";
        String kafkaTopic = "kafka-topic-two-partitions";
        Properties kafkaProperties = new Properties();
        kafkaProperties.put("bootstrap.servers", "localhost:9092,localhost:9093");
        kafkaProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(kafkaProperties);

        CustomTimerTask customTimerTask = new CustomTimerTask();
        Timer timer = new Timer(true);
        System.out.println("Timer Started...");
        timer.scheduleAtFixedRate(customTimerTask, 0, 2 * 1000);

        long startTimeInMilliSeconds = System.currentTimeMillis();


        try{
            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println(new File("").getPath());
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
                    //convert the object to json format
                    String jsonString = objectMapper.writeValueAsString(catalogDetail);


                    //prepare the producer record
                    final ProducerRecord<String, String> producerRecord = new ProducerRecord<>(kafkaTopic, "key-temp", jsonString);
                    //update the line count
                    customTimerTask.updatePublishedItems(currentLineNumber);
                    //send the json message to the producer
                    producer.send(producerRecord, new ProducerCallback());

                    if (currentLineNumber == 10000 || currentLineNumber == 100000
                            || currentLineNumber == 500000 || currentLineNumber == 1000000 ){
                        timestamp = new Timestamp(System.currentTimeMillis());
                        System.out.println("Item Count : " + currentLineNumber + " timestamp : " + timestamp);

                    }

                    //increment the line numbers
                    currentLineNumber++;
                    line = br.readLine();
                }

                timestamp = new Timestamp(System.currentTimeMillis());
                System.out.println("Line #: " + currentLineNumber + " timestamp : " + timestamp);

                long timeSpentInMilliSeconds = System.currentTimeMillis() - startTimeInMilliSeconds;
                long seconds = timeSpentInMilliSeconds / 1000;
                System.out.println("Total Time spent : " + seconds + " seconds");
                System.out.println("Overall Transfer rate : " +  (double)currentLineNumber/(double)seconds + " records per second");

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




