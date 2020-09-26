package eh.ticket.publisher.svc.repository;

import eh.ticket.publisher.svc.model.Trade;
import org.springframework.data.repository.CrudRepository;

public interface TradeRepository extends CrudRepository<Trade, Long> {
}
