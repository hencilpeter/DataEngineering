package eh.trade.process.svc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "security")
public class Security {
    @Id
    @Column(name = "securityId")
    private int securityId;

    @Column(name = "type")
    private String type;

    @Column(name = "underlier")
    private String underlier;

    @Column(name = "validUntil")
    private Date validUntil;

    public Security() {

    }

    public Security(int securityId, String type, String underlier, Date validUntil) {
        this.securityId = securityId;
        this.type = type;
        this.underlier = underlier;
        this.validUntil = validUntil;
    }

    public int getSecurityId() {
        return securityId;
    }

    public String getType() {
        return type;
    }

    public String getUnderlier() {
        return underlier;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setSecurityId(int securityId) {
        this.securityId = securityId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUnderlier(String underlier) {
        this.underlier = underlier;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }
}
