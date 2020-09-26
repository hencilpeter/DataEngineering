package eh.trade.booking.svc.model;

import org.springframework.stereotype.Component;

@Component
public class Security {
    private long id;
    private String type;
    private String underlier;
    private String validUntil;

    public Security(){

    }

    public Security(long id, String type, String underlier, String validUntil) {
        this.id = id;
        this.type = type;
        this.underlier = underlier;
        this.validUntil = validUntil;
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getUnderlier() {
        return underlier;
    }

    public String getValidUntil() {
        return validUntil;
    }

}
