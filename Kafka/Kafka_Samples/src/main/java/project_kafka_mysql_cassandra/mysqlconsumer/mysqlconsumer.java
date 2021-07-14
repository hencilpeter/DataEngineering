/*
* Author: J Hencil Peter
* Description: MySQL based Kafka consumer program. This client program listen to the kafka topic, receive the JSON message,
*              process the received message and write the same to MySQL DB table.
* */

package project_kafka_mysql_cassandra.mysqlconsumer;

import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.codehaus.jackson.map.ObjectMapper;
import project_kafka_mysql_cassandra.model.CatalogModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Properties;

public class mysqlconsumer {
    static final String MySQL_DB_URL = "jdbc:mysql://localhost/PRODUCTCATALOG";
    static final String USERNAME = "user1";
    static final String PASSWORD = "password";

    public static void main(String[] args) {

        Gson gson = new Gson();

        //kafka properties
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "grp-1");
        props.put("enable.auto.commit", "false");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        //initialize kafka consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        //create object mapper for consuming JSON message
        ObjectMapper objectMapper = new ObjectMapper();

        consumer.subscribe(Arrays.asList("kafka-topic-partition-one"));
        try {
            Class.forName("com.mysql.jdbc.Driver");

            //Create connection object
            Connection conn = DriverManager.getConnection(MySQL_DB_URL, USERNAME, PASSWORD);

            //statement object
            Statement stmt = conn.createStatement();


            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(1000);
                for (ConsumerRecord<String, String> record : records) {
                    //try {
                        String message = record.value();
                        CatalogModel catalogDetail = objectMapper.readValue(message, CatalogModel.class);

                        //prepare insert statement
                        String sql = "insert into PRODUCT values('" + catalogDetail.getPogId() + "','" + catalogDetail.getSupc()
                                + "','" + catalogDetail.getPrice() + "','"+ catalogDetail.getQuantity()+"')";

                        //execute the statement
                        stmt.executeUpdate(sql);

                }
                //commit offset
                consumer.commitSync();
            }
        }
        catch (SQLException exception) {
            System.out.println("SQL Exception raised  while processing the incoming messages. Exception message : " + exception.getMessage());
        }
        catch (Exception exception) {
            System.out.println("Exception raised  while processing the incoming messages. Exception message : " + exception.getMessage());
        }
    }
}
