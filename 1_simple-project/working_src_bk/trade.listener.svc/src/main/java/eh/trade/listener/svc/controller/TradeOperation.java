package eh.trade.listener.svc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import eh.trade.listener.svc.service.KafkaService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TradeOperation {


    private static final Logger logger = LogManager.getLogger(TradeOperation.class);
    private static ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private KafkaService kafkaService;

    @PostMapping(value="/processTrade")
    public void postTradeData(@RequestBody Object trade)
    {
        try
        {
            logger.info("Trade : Trade Data Received...");
            String tradeStr = mapper.writeValueAsString(trade);
            logger.info("Trade : Received Trade Data :  " + tradeStr );
            kafkaService.WriteToKafkaTopic(tradeStr);
            logger.info("Trade : Written to Kafka topic.");
        }
        catch (Exception exception) {
            logger.error("Exception while writing trade to Kafka Topic.");
            logger.error("Exception : " + exception);
        }
    }

}
