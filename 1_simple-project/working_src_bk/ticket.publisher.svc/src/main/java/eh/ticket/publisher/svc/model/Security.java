package eh.ticket.publisher.svc.model;


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
    private Long securityId;

    @Column(name = "type")
    private String type;

    @Column(name = "underlier")
    private String underlier;

    @Column(name = "validUntil")
    private Date validUntil;

    public Security() {

    }

    public Security(Long securityId, String type, String underlier, Date validUntil) {
        this.securityId = securityId;
        this.type = type;
        this.underlier = underlier;
        this.validUntil = validUntil;
    }

    public Long getSecurityId() {
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

    public void setSecurityId(Long securityId) {
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
