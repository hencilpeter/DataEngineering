package eh.ticket.publisher.svc.service;

import com.netflix.discovery.converters.Auto;
import eh.ticket.publisher.svc.model.Position;
import eh.ticket.publisher.svc.model.Security;
import eh.ticket.publisher.svc.model.Trade;
import eh.ticket.publisher.svc.repository.PositionRepository;
import eh.ticket.publisher.svc.repository.SecurityRepository;
import eh.ticket.publisher.svc.repository.TradeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TradeDataService implements  ITradeDataService{
    private static final Logger logger = LogManager.getLogger(TradeDataService.class);
    @Autowired
    TradeRepository tradeRepository;

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    SecurityRepository securityRepository;

    public Trade readTradeTableData(Long tradeId){
        try {
            Optional<Trade> trade = tradeRepository.findById( tradeId);

            return trade.get();
        }
        catch (Exception exception){
            logger.error("Exception raised while reading trade data.");
            logger.error("Exception : "+ exception);
        }
        return null;
    }

    public Position readPositionTableData(Long positionId){
        try {
            Optional<Position> position = positionRepository.findById( positionId);

            return position.get();
        }
        catch (Exception exception){
            logger.error("Exception raised while reading position data.");
            logger.error("Exception : "+ exception);
        }
        return null;
    }

    public Security readSecurityTableData(Long securityId)
    {
        try {
            Optional<Security> security = securityRepository.findById(securityId);

            return security.get();
        }
        catch (Exception exception){
            logger.error("Exception raised while reading Security data.");
            logger.error("Exception : "+ exception);
        }

        return null;
    }
}
