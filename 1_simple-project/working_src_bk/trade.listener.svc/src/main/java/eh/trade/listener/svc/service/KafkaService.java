package eh.trade.listener.svc.service;
import java.util.Properties;
import java.util.UUID;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class KafkaService {
    private final String TRADE_TOPIC_NAME = "trade-data";
    private static final Logger logger = LogManager.getLogger(KafkaService.class);
    static Producer<String,String> kafkaProducer = null;

    public KafkaService()
    {

    }
    public void WriteToKafkaTopic(String tradeData){
        String messageId = UUID.randomUUID().toString();
        logger.info("Generated message id : " + messageId);
        getKafkaProducer().send(new ProducerRecord<String, String>(TRADE_TOPIC_NAME,
                messageId, "Message : " +  tradeData));
    }


    private Producer<String,String> getKafkaProducer(){

        if (kafkaProducer == null) {
            Properties props = new Properties();
            props.put("bootstrap.servers", "localhost:9092");
            props.put("acks", "all");
            props.put("retries", 0);
            props.put("batch.size", 16384);
            props.put("linger.ms",1);
            props.put("buffer.memory", 33554432);
            //add key/value serializer
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

            kafkaProducer = new KafkaProducer<String, String> (props);
        }
        return kafkaProducer;
    }
}
