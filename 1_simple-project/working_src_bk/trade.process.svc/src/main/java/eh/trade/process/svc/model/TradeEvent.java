package eh.trade.process.svc.model;

public class TradeEvent {
    private int tradeId;
    private String tradeType;

    public TradeEvent() {
    }

    public TradeEvent(int tradeId, String tradeType) {
        this.tradeId = tradeId;
        this.tradeType = tradeType;
    }

    public int getTradeId() {
        return tradeId;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeId(int tradeId) {
        this.tradeId = tradeId;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }
}
