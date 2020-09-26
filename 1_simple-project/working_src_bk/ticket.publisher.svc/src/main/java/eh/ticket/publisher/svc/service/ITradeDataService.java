package eh.ticket.publisher.svc.service;

import eh.ticket.publisher.svc.model.Position;
import eh.ticket.publisher.svc.model.Security;
import eh.ticket.publisher.svc.model.Trade;

public interface ITradeDataService {
    Trade readTradeTableData(Long tradeId);
    Security readSecurityTableData(Long securityId);
    Position readPositionTableData(Long positionId);
}
