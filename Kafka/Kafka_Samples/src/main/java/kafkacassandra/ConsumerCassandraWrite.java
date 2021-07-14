/*
*Author : J Hencil Peter
* Purpose : Integrating Kafka with Cassandra
*  */
package kafkacassandra;

import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.Arrays;
import java.util.Properties;
import java.util.UUID;

public class ConsumerCassandraWrite {
    public static void main(String[] args)
    {
        Gson gson = new Gson();
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "grp-1");
        props.put("enable.auto.commit", "false");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        //create CassandraConnector client and establish the connection
        final CassandraConnector client = new CassandraConnector();
        client.connect("localhost", 9042);

        //Create object mapper
        ObjectMapper objectMapper = new ObjectMapper();

    //Cassandra Write test code - enable for debugging
    //        String query1 ="insert into product_keyspace.product(id, pogid, brand, description, category, subcategory, price) VALUES ('"
    //                +UUID.randomUUID().toString()+ "', 'test pogid','test brand','test des','test category','test sub category'," +
    //                "'test price')";
    //        System.out.println(query1);
    //        client.getSession().execute(query1);


        //subscribe to the kafka topic
        consumer.subscribe(Arrays.asList("kafka-topic-partition-one"));
        while (true)
        {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            for (ConsumerRecord<String, String> record : records)
            {
                    try {
                        String message = record.value().replace('&',' ');
                        CatalogDetailDeSerialize catalogDetail = objectMapper.readValue(message, CatalogDetailDeSerialize.class);
                        //prepare insert query
                        String insertSQL ="insert into product_keyspace.product(id, brand, category, description, pogid, price, subcategory) VALUES ('"
                                      +UUID.randomUUID().toString() + "', '"+ catalogDetail.getBand() +"',' "+ catalogDetail.getCategory() +"','"
                                + catalogDetail.getDescription() + "','"+ catalogDetail.getPogId()  +"','"+ catalogDetail.getPrice()
                                + "','"+ catalogDetail.getSubCatagory() +"')";

                        client.getSession().execute(insertSQL);
                    }
                    catch(Exception exception){
                        //some bad records throw error. just log error message and continue.
                        System.out.println("Exception raised : " + exception.getMessage());
                    }

            }
            consumer.commitSync();
        }

    }
}
