package eh.ticket.publisher.svc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.xstream.XStream;
import eh.ticket.publisher.svc.model.Position;
import eh.ticket.publisher.svc.model.Security;
import eh.ticket.publisher.svc.model.Trade;
import eh.ticket.publisher.svc.model.TradeTicket;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

@Component
public class KafkaService {
    private final String TICKET_EVENT_TOPIC_NAME = "ticket-event";
    private static final Logger logger = LogManager.getLogger(KafkaService.class);
    private static ObjectMapper mapper = new ObjectMapper();
    private static KafkaConsumer<String, String> consumer = null;

    @Autowired
    ITradeDataService tradeDataService;

    public KafkaService()
    {
        subscribeTopic();
    }

    public void subscribeTopic(){
        try {
            getConsumer().subscribe(Arrays.asList(TICKET_EVENT_TOPIC_NAME));
            logger.info("Consumer is subscribed to topic : " + TICKET_EVENT_TOPIC_NAME);
        }
        catch (Exception exception)
        {
            logger.error("Exception during topic subscription");
            logger.error("Exception : " + exception );
        }
    }

    public boolean procesEvent(){

        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
        for(ConsumerRecord<String, String> record : records) {
            logger.info("Ticket publisher received event message.");
            logger.info(String.format("Message partition = %d, offset = %d \n", record.partition(), record.offset()));
            logger.info(String.format("Message Key =  %s\n", record.key()));
            String ticketEvent = record.value();
            logger.info(String.format("Message value =  %s\n", ticketEvent));

            long tradeId = -1;
            try {
                //get ticket details
                JSONObject jsonObject = new JSONObject(ticketEvent);
                 tradeId = jsonObject.getInt("tradeId");
                String tradeType = jsonObject.getString("tradeType");

            }
            catch(Exception exception){
                logger.error("Exception while parsing received ticket event object.");
                logger.error("Exception : "+ exception);
            }
            //mapper.writeValueAsString(ticketEvent);
            TradeTicket tradeTicket = getTradeTicket(record.key(), tradeId);
            logger.info("TICKET PROCESSED SUCCESSFULLY FOR TRADE : "+ tradeId);
            logger.info("\nTICKET (STRING FORMAT....): \n" + tradeTicket.toString());

            //convert to xml
            try {

                XStream xstream = new XStream();
                xstream.alias("TradeTicket", TradeTicket.class);
                String xmlString = xstream.toXML(tradeTicket);
                logger.info("TICKET (XML FORMAT....)\n" + xmlString);
            }
            catch (Exception exception){
                logger.error("Exception raised while converting the ticket object to XML.");
                logger.error("Exception : " + exception);
            }

        }
            return true;
    }

    private TradeTicket getTradeTicket(String ticketId, Long tradeId){


        //get Trade Data
        Trade trade = tradeDataService.readTradeTableData(tradeId);

        // get Position Data
        Position position = tradeDataService.readPositionTableData((long)trade.getPositionId());

        // get Security Data
        Security security = tradeDataService.readSecurityTableData( (long) position.getSecurityId()); //(long)20097);

        TradeTicket tradeTicket = new TradeTicket(ticketId, (int)(long) trade.getTradeId(), trade.getPositionId(),
                (int)(long) security.getSecurityId(),
                trade.getPrice(),trade.getQuantity(), position.getFirmAccount());
      return tradeTicket;
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

}
