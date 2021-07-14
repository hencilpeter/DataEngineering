/*
 * Author : J Hencil Peter
 * Program : multi threaded kafka producer (Asynchronous approach)
 * */
package kafkaproducer.multithread_SyncAsync;

import org.apache.kafka.clients.producer.*;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.Scanner;


public class producer_multi_thread_async_approach {


    public static void main(String args[]) {
        String kafkaTopic = "kafka-topic-single-partition";
        //String kafkaTopic = "kafka-topic-two-partitions";
        Properties kafkaProperties = new Properties();
        kafkaProperties.put("bootstrap.servers", "localhost:9092,localhost:9093");
        kafkaProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(kafkaProperties);

        String[] fileList = new String[2];
        fileList[0] = ".//data//products-1.csv";
        fileList[1] = ".//data//products-2.csv";

        //dispatcher objects for each thread
        Thread[] threadDispatchers = new Thread[fileList.length];
        for (int count = 0; count < fileList.length; count++) {
            threadDispatchers[count] = new Thread(new Dispatcher2(producer, kafkaTopic, fileList[count]));
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

class ProducerCallback implements Callback{
    private  void printMetaData(final RecordMetadata metadata){
        //System.out.println("Message written Topic : "+ metadata.topic());
        //System.out.println("Partition : "+ metadata.partition());
        //System.out.println("Offset : "+ metadata.offset());
    }
    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception exception){
        if (exception!= null) {
            System.out.println("Asynchronous producer failed  with exception.");
            System.out.println("Exception : " + exception);
        }
        else{
            //System.out.print("Asynchronous producer succeeded!!!");
            printMetaData(recordMetadata);
        }
    }
}

class Dispatcher2 implements Runnable {
    private final Producer<String, String> producer;
    private final String topicName;
    private final String fileName;

    Dispatcher2(Producer<String, String> _producer, String _topicName,
               String _fileName) {
        producer = _producer;
        topicName = _topicName;
        fileName = _fileName;
    }

    @Override
    public void run() {
        System.out.println("Producer Starting...");
        File fileObject = new File(fileName);
        ObjectMapper objectMapper = new ObjectMapper();
        long currentLineNumber = 1;
        Timestamp timestamp;
        try (Scanner scanner = new Scanner(fileObject)) {
            timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println("Line #: " + currentLineNumber + "FileName : " + fileName + " timestamp : " + timestamp);


            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String[] arrString = line.split(",");
                //construct objet
                CatalogDetail catalogDetail = new CatalogDetail(arrString[0], arrString[1], arrString[2], arrString[3],
                        arrString[4], arrString[5], arrString[6], arrString[7], arrString[8], arrString[9],
                        arrString[10], arrString[11], arrString[12]);
                String jsonString = objectMapper.writeValueAsString(catalogDetail);

                //send the json message to the producer
                final ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topicName, "key-temp", jsonString);
                producer.send(producerRecord, new ProducerCallback());

                if (currentLineNumber == 10000 || currentLineNumber == 100000
                        || currentLineNumber == 500000 || currentLineNumber == 1000000) {
                    timestamp = new Timestamp(System.currentTimeMillis());
                    System.out.println("Line #: " + currentLineNumber + "FileName : " + fileName + " timestamp : " + timestamp);

                }
                currentLineNumber++;

            }
        } catch (Exception exception) {
            System.out.println("Exception in thread. exception : " + exception);
        }
    }
}

class CatalogDetail2{
    CatalogDetail2(String _PogId, String _Supc, String _Band, String _Description,
                  String _Size,String _Category,String _SubCategory,String _Price,String _Quantity,
                  String _Country,String _SellerCode,String _CreationCode, String _Stock){
        PogId = _PogId;
        Supc = _Supc;
        Band = _Band;
        Description = _Description;
        Size = _Size;
        Category = _Category;
        SubCetagory = _SubCategory;
        Price = _Price;
        Quantity = _Quantity;
        Country = _Country;
        SellerCode = _SellerCode;
        CreationCode = _CreationCode;
        Stock = _Stock;
    }
    public String PogId;
    public String Supc;
    public String Band;
    public String Description;
    public String Size;
    public String Category;
    public String SubCetagory;
    public String Price;
    public String Quantity;
    public String Country;
    public String SellerCode;
    public String CreationCode;
    public String Stock;
}