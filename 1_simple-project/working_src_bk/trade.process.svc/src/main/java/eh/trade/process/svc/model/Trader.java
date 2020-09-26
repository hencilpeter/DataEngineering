package eh.trade.process.svc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "trader")
public class Trader {

    @Id
    @Column(name="soeId")
    private String soeId;

    @Column(name="traderName")
    private String traderName;

    public Trader() {
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

    public void setTraderName(String traderName) {
        this.traderName = traderName;
    }

    public void setSoeId(String soeId) {
        this.soeId = soeId;
    }
}
