package eh.trade.process.svc.repository;

import eh.trade.process.svc.model.Position;
import org.springframework.data.repository.CrudRepository;

public interface PositionRepository  extends CrudRepository<Position, Long> {
}
