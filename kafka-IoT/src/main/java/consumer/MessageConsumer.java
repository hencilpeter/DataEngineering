package consumer;

import com.google.gson.Gson;
import connector.CassandraConnector;
import model.MotorMessage;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.codehaus.jackson.map.ObjectMapper;

import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Properties;

public class MessageConsumer {
    public static void main(String[] args)
    {
        Gson gson = new Gson();
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "grp-1");
        props.put("enable.auto.commit", "false");
        props.put("auto.commit.interval.ms", "100");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        //create CassandraConnector client and establish the connection
        final CassandraConnector client = new CassandraConnector();
        client.connect("localhost", 9042);

        //Create object mapper
        ObjectMapper objectMapper = new ObjectMapper();

        //subscribe to the kafka topic
        consumer.subscribe(Arrays.asList("kafka-topic-one-partition"));
        System.out.println("Cassandra based consumer started...");
        long startTimeInMilliSeconds = System.currentTimeMillis();
        Timestamp startTime = new Timestamp(startTimeInMilliSeconds);

        System.out.println("Start Timestamp : " + startTime);

        long consumedMessageCount = 0;

        while (true)
        {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records)
            {
                try {
                    //read the incoming message
                    String message = record.value();

                    //construct the object
                    MotorMessage motorMessage = objectMapper.readValue(message, MotorMessage.class);

                    //construct the insert statement
                    String insertSQL = "insert into iot_keyspace.motor_messages(messageid, motorname , timestamp , pressure , temperature ) values('"
                            + motorMessage.getMessageid() + "', '" + motorMessage.getMotorname() + "', '" + motorMessage.getTimestamp() + "', '"
                            + motorMessage.getPressure()  + "', '" +  motorMessage.getTemperature() +"')";

                    //execute the statement
                    client.getSession().execute(insertSQL);

                    //increment the counter
                    consumedMessageCount++;
                }
                catch(Exception exception){
                    //some bad records throw error. just log error message and continue.
                    System.out.println("Exception raised : " + exception.getMessage());
                }

            }
            long currentTimeInMilliSeconds = System.currentTimeMillis();
            Timestamp currentTime = new Timestamp(currentTimeInMilliSeconds);
            System.out.println(MessageFormat.format("Consumed Message Count : {0} , current timestamp {1} "
                    ,consumedMessageCount
                    , currentTime.toString()) );


            consumer.commitSync();
        }

    }
}