package eh.trade.booking.svc.model;

import org.springframework.stereotype.Component;

@Component
public class Position {
    private long id;
    private String firmAccount;

    public Position(){

    }

    public Position(long id, String firmAccount) {
        this.id = id;
        this.firmAccount = firmAccount;
    }

    public long getId() {
        return id;
    }

    public String getFirmAccount() {
        return firmAccount;
    }



}
