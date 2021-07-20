package producer;

import model.MotorMessage;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.codehaus.jackson.map.ObjectMapper;

import java.sql.Timestamp;
import java.util.Properties;
import java.util.UUID;


public class MessageProducer {
    private static void printMetaData(final RecordMetadata metadata, int messageCount){
        System.out.println("Sent message # " + (messageCount+1));
        System.out.println("Message written Topic : "+ metadata.topic());
        System.out.println("Partition : "+ metadata.partition());
        System.out.println("Offset : "+ metadata.offset());
    }

    public static void main(String args[]){
        String kafkaTopic = "kafka-topic-one-partition";

        Properties kafkaProperties = new Properties();
        kafkaProperties.put("bootstrap.servers", "localhost:9092,localhost:9093");
        kafkaProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        ObjectMapper objectMapper = new ObjectMapper();

        Producer<String, String> producer = new KafkaProducer<String, String>(kafkaProperties);

        System.out.println(UUID.randomUUID().toString());

        long messageCount = 10000;
        String motor_name = "Fulton-A32";

        Timestamp startTime = new Timestamp(System.currentTimeMillis());

        System.out.println("Producer Started...");
        System.out.println("Start Timestamp : " + startTime);

        for(int count = 0; count< messageCount; count++){
            // pressure between 1 and 100
            int pressure = (int) (Math.random() * 100 ) + 1;

            // temperature between 1 and 150
            int temperature = (int) (Math.random() * 150 ) + 1;

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            try {

                //construct the message
                MotorMessage motorMessage = new MotorMessage(UUID.randomUUID().toString(),
                        motor_name,
                        timestamp.toString(),
                        String.valueOf( pressure),
                        String.valueOf(temperature));

                //convert to json object
                String jsonString = objectMapper.writeValueAsString(motorMessage);

                System.out.println(jsonString);
                //construct the record
                final ProducerRecord<String, String> producerRecord = new ProducerRecord<>(kafkaTopic, "motor-message", jsonString);

                //send the message to kafka topic
                RecordMetadata metadata = producer.send(producerRecord).get();

                //print the message info
                printMetaData(metadata, count);

            }
            catch (Exception exception){
                System.out.println("Exception raised while sending the message.");
                System.out.print("Exception : " + exception);
            }

        }

        Timestamp endTime = new Timestamp(System.currentTimeMillis());
        System.out.println("End Timestamp : " + endTime);

    }
}
