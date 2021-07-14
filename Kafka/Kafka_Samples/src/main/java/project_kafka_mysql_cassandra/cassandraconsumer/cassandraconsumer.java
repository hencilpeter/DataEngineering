/**
 * Author : J  Hencil Peter
 * Description : Cassandra consumer programs reads the message from kafka topic, construct the json object from the message,
 * prepare the insert statement and execute the same.
 */

package project_kafka_mysql_cassandra.cassandraconsumer;

import com.google.gson.Gson;
import kafkacassandra.CassandraConnector;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.codehaus.jackson.map.ObjectMapper;
import project_kafka_mysql_cassandra.model.CatalogModel;

import java.util.Arrays;
import java.util.Properties;
import java.util.UUID;

public class cassandraconsumer {
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
                    //cleaning few records
                    String message = record.value().replace('&',' ');

                    //construct catalog detail from the JSON object
                    CatalogModel catalogDetail = objectMapper.readValue(message, CatalogModel.class);

                    //prepare insert script
                    String insertSQL = "insert into product_catalog_keyspace.product(id, PogId, Supc, Brand, Description, Size, Category,  SubCategory, " +
                            "Country, SellerCode) values('" + UUID.randomUUID().toString() +
                                    "','" + catalogDetail.getPogId() + "','" + catalogDetail.getSupc() + "','" + catalogDetail.getBand() +
                                    "','" + catalogDetail.getDescription() + "','" + catalogDetail.getSize() + "','" + catalogDetail.getCategory() +
                                    "','" + catalogDetail.getSubCatagory() + "','" + catalogDetail.getCountry() + "','" + catalogDetail.getSellerCode() +
                                    "')";



                    //execute the insert script
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
