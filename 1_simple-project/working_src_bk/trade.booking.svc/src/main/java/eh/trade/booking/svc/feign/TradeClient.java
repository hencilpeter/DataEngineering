package eh.trade.booking.svc.feign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("TRADE-LISTENER-SVC")
public interface TradeClient {
    @PostMapping(value="/processTrade")
    void postTradeData(@RequestBody Object trade);
}
