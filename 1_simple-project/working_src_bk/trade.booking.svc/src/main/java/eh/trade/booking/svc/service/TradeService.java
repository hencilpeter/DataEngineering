package eh.trade.booking.svc.service;

import eh.trade.booking.svc.feign.TradeClient;
import eh.trade.booking.svc.generator.TradeDataGenerator;
import eh.trade.booking.svc.model.Trade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TradeService {
    @Autowired
    TradeDataGenerator tradeDataGenerator;

    @Autowired
    TradeClient tradeClient;

    private static final Logger logger = LogManager.getLogger(TradeService.class);

    public void sendDynamicTrade(){
        logger.info("Trade: Creating Dynamic Trade Data.");
        Trade trade = tradeDataGenerator.getDynamicTradeData();

        logger.info("Trade :Sending Dynamic Trade Data.");
        logger.info("Trade : Data Being Sent..." + trade.toString());
        tradeClient.postTradeData(trade);
        logger.info("Trade data has been sent.");
    }
}
