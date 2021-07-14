/*
 * Author : J Hencil Peter
 * Program : single threaded kafka producer (Asynchronous approach)
 * */
package kafkaproducer.singlethread_SyncAsync;
import org.apache.kafka.clients.producer.*;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Timestamp;
import java.util.Properties;


public class producer_single_thread_async_approach {


    public static void main(String args[]) {
        //String kafkaTopic = "kafka-topic-single-partition";
        String kafkaTopic = "kafka-topic-two-partitions";
        Properties kafkaProperties = new Properties();
        kafkaProperties.put("bootstrap.servers", "localhost:9092,localhost:9093");
        kafkaProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(kafkaProperties);


        try{
            System.out.println(new File("").getAbsolutePath());
            ObjectMapper objectMapper = new ObjectMapper();
            try(BufferedReader br = new BufferedReader(new FileReader(".//data//products.csv"))) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                System.out.println("Start time : " + timestamp);
                String line = br.readLine();

                long currentLineNumber = 1;
                while (line != null){
                    String[] arrString = line.split(",");
                    //construct objet
                    CatalogDetail2 catalogDetail = new CatalogDetail2(arrString[0],arrString[1],arrString[2],arrString[3],
                            arrString[4],arrString[5],arrString[6],arrString[7],arrString[8],arrString[9],
                            arrString[10], arrString[11],arrString[12]);
                    String jsonString = objectMapper.writeValueAsString(catalogDetail);

                    //send the json message to the producer
                    final ProducerRecord<String, String> producerRecord = new ProducerRecord<>(kafkaTopic, "key-temp", jsonString);

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
            System.out.println("Excelption raised while sending the message.");
            System.out.print("Exception : " + exception);
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