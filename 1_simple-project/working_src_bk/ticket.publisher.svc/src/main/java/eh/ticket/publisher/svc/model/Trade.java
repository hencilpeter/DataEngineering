package eh.ticket.publisher.svc.model;


import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "trade")
public class Trade {

    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Id
    @Column(name="tradeId")
    private Long tradeId;

    @Column(name="positionId")
    private int positionId;

    private String tradeType;

    private float quantity;

    private float price;

    private Timestamp timeStamp;

    public Trade() {
    }

    public Trade(int id, Long tradeId, int positionId, String tradeType, float quantity, float price, Timestamp timeStamp) {
        this.id = id;
        this.tradeId = tradeId;
        this.positionId = positionId;
        this.tradeType = tradeType;
        this.quantity = quantity;
        this.price = price;
        this.timeStamp = timeStamp;
    }

    public int getId() {
        return id;
    }

    public Long getTradeId() {
        return tradeId;
    }

    public int getPositionId() {
        return positionId;
    }

    public String getTradeType() {
        return tradeType;
    }

    public float getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString(){
        final StringBuilder stringBuilder = new StringBuilder("trade{");
        stringBuilder.append("id=").append(id);
        stringBuilder.append(", tradeId=").append(tradeId).append('\'');
        stringBuilder.append(", positionId=").append(positionId).append('\'');
        stringBuilder.append(", tradeType=").append(tradeType).append('\'');
        stringBuilder.append(", quantity=").append(quantity).append('\'');
        stringBuilder.append(", price=").append(price).append('\'');
        stringBuilder.append(", timeStamp=").append(timeStamp);
        stringBuilder.append('}');

        return stringBuilder.toString();
    }
}
