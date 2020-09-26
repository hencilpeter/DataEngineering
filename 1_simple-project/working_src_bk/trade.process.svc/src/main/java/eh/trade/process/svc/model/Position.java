package eh.trade.process.svc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="position")
public class Position {
    @Id
    @Column(name="positionId")
    private int positionId;

    @Column(name="securityId")
    private int securityId;

    @Column(name="firmAccount")
    private String firmAccount;

    public Position() {
    }

    public Position(int positionId, String firmAccount, int securityId) {
        this.positionId = positionId;
        this.securityId = securityId;
        this.firmAccount = firmAccount;
    }

    public int getPositionId() {
        return positionId;
    }

    public int getSecurityId() {
        return securityId;
    }

    public String getFirmAccount() {
        return firmAccount;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public void setFirmAccount(String firmAccount) {
        this.firmAccount = firmAccount;
    }

    public void setSecurityId(int securityId) {
        this.securityId = securityId;
    }
}
