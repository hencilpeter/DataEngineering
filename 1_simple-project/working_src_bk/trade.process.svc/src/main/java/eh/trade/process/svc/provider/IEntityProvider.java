package eh.trade.process.svc.provider;

import eh.trade.process.svc.model.Position;
import eh.trade.process.svc.model.Security;
import eh.trade.process.svc.model.Trade;
import eh.trade.process.svc.model.Trader;


public interface IEntityProvider {
     Trade getTradeEntity(String tradeData);
     Trader getTraderEntity(String tradeData);
     Position getPositionEntity(String tradeData);
     Security getSecurityEntity(String tradeData);

}
