package eh.trade.process.svc.repository;

import eh.trade.process.svc.model.Trader;
import org.springframework.data.repository.CrudRepository;

public interface TraderRepository extends CrudRepository<Trader, String> {
    
}
