package eh.trade.booking.svc.model;

import org.springframework.stereotype.Component;

@Component
public class Trader {
    private String traderName;
    private String soeId;

    public Trader(){

    }

    public Trader(String traderName, String soeId) {
        this.traderName = traderName;
        this.soeId = soeId;
    }

    public String getTraderName() {
        return traderName;
    }

    public String getSoeId() {
        return soeId;
    }

}
