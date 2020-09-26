package eh.trade.process.svc.service;

import java.time.Duration;
import java.util.Properties;
import java.util.Arrays;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import eh.trade.process.svc.model.*;
import eh.trade.process.svc.provider.IEntityProvider;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class KafkaService {
 private final String TRADE_DATA_TOPIC_NAME = "trade-data";
 private final String TICKET_EVENT_TOPIC_NAME = "ticket-event";
    private static final Logger logger = LogManager.getLogger(KafkaService.class);
    private static KafkaConsumer<String, String> consumer = null;
    private static KafkaProducer<String, String> producer = null;
    private static ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private IEntityProvider tradeEntityProvider;

    @Autowired
    TradeService tradeService;

    public KafkaService()
    {
        subscribeTopic();
    }
    public void subscribeTopic(){
        try {
            getConsumer().subscribe(Arrays.asList(TRADE_DATA_TOPIC_NAME));
            logger.info("Consumer is subscribed to topic : " + TRADE_DATA_TOPIC_NAME);
        }
        catch (Exception exception)
        {
            logger.error("Exception during topic subscription");
            logger.error("Exception : " + exception );
        }
    }

    public void processMessage(){
          ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for(ConsumerRecord<String, String> record : records) {
                logger.info("Ticket processor received new message.");
                logger.info(String.format("Message partition = %d, offset = %d \n", record.partition(), record.offset()));
                logger.info(String.format("Message Key =  %s\n", record.key()));
                String tradeData = record.value();
                logger.info(String.format("Message value =  %s\n", tradeData));

                //save trade data
                saveTradeData(tradeData);
                //write ticket event to generate actual ticket
                writeTicketEventToTopic(tradeData);

                logger.info("trade processed successfully.");
            }
    }

    private void saveTradeData(String tradeData){
        logger.info("saving trade data - start.");
        Trade trade = tradeEntityProvider.getTradeEntity(tradeData);
        tradeService.saveTrade(trade);

        Trader trader = tradeEntityProvider.getTraderEntity(tradeData);
        tradeService.saveTrader(trader);

        Position position = tradeEntityProvider.getPositionEntity(tradeData);
        tradeService.savePosition(position);

        Security security = tradeEntityProvider.getSecurityEntity(tradeData);
        tradeService.saveSecurity(security);
        logger.info("saving trade data - end.");
    }

    private void writeTicketEventToTopic(String tradeData){
        Trade trade = tradeEntityProvider.getTradeEntity(tradeData);
        //write trade-event message to Kafka
        try {
            //construct Trade Event
            logger.info("sending trade event - start.");
            TradeEvent tradeEvent = new TradeEvent(trade.getTradeId(), trade.getTradeType());
            String messageId = UUID.randomUUID().toString();
            String tradeEventStr = mapper.writeValueAsString(tradeEvent);
            getProducer().send(new ProducerRecord<String, String>(TICKET_EVENT_TOPIC_NAME, messageId, tradeEventStr));
            logger.info("sending trade event - end.");
        }
        catch (Exception exception){
            logger.error("Exception while writing trade to Kafka Topic.");
            logger.error("Exception : " + exception);
        }
    }

    private KafkaConsumer<String, String> getConsumer() {

        if (consumer == null) {
            Properties props = new Properties();

            props.put("bootstrap.servers", "localhost:9092");
            props.put("group.id", "test");
            props.put("enable.auto.commit", "true");
            props.put("auto.commit.interval.ms", "1000");
            props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

            consumer = new KafkaConsumer<String, String>(props);
        }
        return consumer;
    }

    private KafkaProducer<String, String> getProducer(){

        if (producer == null) {
            Properties props = new Properties();

            props.put("bootstrap.servers", "localhost:9092");
            props.put("acks", "all");
            props.put("retries", 0);
            props.put("batch.size", 16384);
            props.put("linger.ms",1);
            props.put("buffer.memory", 33554432);
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

            producer = new KafkaProducer<String, String> (props);
        }
        return producer;
    }

}
