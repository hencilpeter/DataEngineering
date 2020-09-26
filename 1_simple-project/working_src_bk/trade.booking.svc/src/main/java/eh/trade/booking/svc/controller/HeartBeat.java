package eh.trade.booking.svc.controller;

import eh.trade.booking.svc.feign.TradeClient;
import eh.trade.booking.svc.generator.TradeDataGenerator;
import eh.trade.booking.svc.model.Trade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeartBeat {
    @Autowired
    TradeDataGenerator tradeDataGenerator;

    private static final Logger logger = LogManager.getLogger(HeartBeat.class);


    @GetMapping(value="/heart-beat")
    public String heartBeat(){
        logger.info( "heart beat call successful.");
       return "trade booking service alive!!!";
    }

    @GetMapping(value="/trade")
    public Trade message(){
        logger.info( "trade creation request received.");
        Trade trade = tradeDataGenerator.getDynamicTradeData();

        logger.info( "trade data generated.");
        return trade;
     }
}
