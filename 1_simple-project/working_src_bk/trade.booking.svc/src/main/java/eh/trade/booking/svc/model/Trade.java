package eh.trade.booking.svc.model;

public class Trade {
    private int tradeId;
    private String tradeType;
    private double quantity;
    private double price;
    private String timeStamp;
    private Security security;
    private Position position;
    private Trader trader;

    public Trade() {
    }

    public Trade(int tradeId, String tradeType, double quantity, double price, String timeStamp, Security security, Position position, Trader trader) {
        this.tradeId = tradeId;
        this.tradeType = tradeType;
        this.quantity = quantity;
        this.price = price;
        this.timeStamp = timeStamp;
        this.security = security;
        this.position = position;
        this.trader = trader;
    }

    public Trade(int tradeId,  String tradeType, double quantity, double price, String timeStamp) {
        this.tradeId = tradeId;
        this.tradeType = tradeType;

        this.quantity = quantity;
        this.price = price;

        this.timeStamp = timeStamp;
    }

    public Security getSecurity(){
    return security;
    }

    public Position getPosition(){
        return position;
    }

    public Trader getTrader(){
        return trader;
    }
    public int getTradeId() {
        return tradeId;
    }

    public String getTradeType() {
        return tradeType;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

}
