package kafkastream.invoiceproducer;
import com.google.gson.Gson;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.Properties;
/*
* Author: J Hencil Peter
* Description: Producer application send the simple invoice JSON message to kafka.
* */

public class InvoiceProducer {

    public static void main(String[] args) throws InterruptedException
    {
        Gson gson = new Gson();
        Properties properties = new Properties();
        properties.put("bootstrap.servers","localhost:9092,localhost:9093");
        properties.put("acks","1");
        properties.put("retries", 0);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<String, String>(properties);

        try{

            Invoice invoice = new Invoice(101, "James Thompson", 2500, "NGL");
            //String jsonString = gson.toJson(invoice);
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(invoice);
            ProducerRecord<String, String> record = new ProducerRecord<>("kafka-topic-invoice", invoice.getCityCode(), jsonString);
            producer.send(record);
        }
        catch(Exception exception){
            exception.printStackTrace();
        }
        producer.close();

        System.out.println("Invoice JSON message has been published....");

    }
}
