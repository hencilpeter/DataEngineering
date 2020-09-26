package eh.trade.process.svc.service;

import eh.trade.process.svc.model.Position;
import eh.trade.process.svc.model.Security;
import eh.trade.process.svc.model.Trade;
import eh.trade.process.svc.model.Trader;

public interface ITradeService {
    void saveTrade(Trade trade);
    void saveTrader(Trader trader);
    void savePosition(Position position);
    void saveSecurity(Security security);
}
