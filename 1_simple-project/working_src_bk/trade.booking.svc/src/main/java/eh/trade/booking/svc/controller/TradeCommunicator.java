package eh.trade.booking.svc.controller;
import eh.trade.booking.svc.service.TradeService;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.Logger;

@RestController
public class TradeCommunicator {

    @Autowired
    TradeService tradeService;

    private static final Logger logger = LogManager.getLogger(TradeCommunicator.class);

    @PostMapping(value = "/trade-data")
    public void postTradeData(){
        logger.info( "trade creation request received.");
        tradeService.sendDynamicTrade();
        logger.info( "trade creation request processed.");
    }



}
