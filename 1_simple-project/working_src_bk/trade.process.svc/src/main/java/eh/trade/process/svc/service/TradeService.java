package eh.trade.process.svc.service;

import eh.trade.process.svc.model.Position;
import eh.trade.process.svc.model.Security;
import eh.trade.process.svc.model.Trade;
import eh.trade.process.svc.model.Trader;
import eh.trade.process.svc.repository.PositionRepository;
import eh.trade.process.svc.repository.SecurityRepository;
import eh.trade.process.svc.repository.TradeRepository;
import eh.trade.process.svc.repository.TraderRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeService implements ITradeService {
    private static final Logger logger = LogManager.getLogger(TradeService.class);
    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private TraderRepository traderRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private SecurityRepository securityRepository;

    public void saveTrade(Trade trade) {
        try {
            tradeRepository.save(trade);
        } catch (Exception exception) {
            logger.error("Exception while saving trade data.");
            logger.error("Exception : " + exception);
        }
    }

    public void saveTrader(Trader trader) {
        try {
            traderRepository.save(trader);
        } catch (Exception exception) {
            logger.error("Exception while saving trader data.");
            logger.error("Exception : " + exception);
        }
    }

    public void savePosition(Position position){
        try {
            positionRepository.save(position);
        } catch (Exception exception) {
            logger.error("Exception while saving position data.");
            logger.error("Exception : " + exception);
        }
    }

    public void saveSecurity(Security security){
        try {
            securityRepository.save(security);
        } catch (Exception exception) {
            logger.error("Exception while saving security data.");
            logger.error("Exception : " + exception);
        }
    }
}
