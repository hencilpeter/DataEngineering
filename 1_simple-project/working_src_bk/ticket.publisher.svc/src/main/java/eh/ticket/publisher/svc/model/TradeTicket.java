package eh.ticket.publisher.svc.model;

public class TradeTicket {
    private String TicketId;
    private int TradeId;
    private int PositionId;
    private int SecurityId;
    private double TradePrice;
    private double TradeQuantity;
    private String FirmAccount;

    public TradeTicket() {
    }

    public TradeTicket(String ticketId, int tradeId, int positionId, int securityId, double tradePrice, double tradeQuantity, String firmAccount) {
        TicketId = ticketId;
        TradeId = tradeId;
        PositionId = positionId;
        SecurityId = securityId;
        TradePrice = tradePrice;
        TradeQuantity = tradeQuantity;
        FirmAccount = firmAccount;
    }

    public String getTicketId() {
        return TicketId;
    }

    public int getTradeId() {
        return TradeId;
    }

    public int getPositionId() {
        return PositionId;
    }

    public int getSecurityId() {
        return SecurityId;
    }

    public double getTradePrice() {
        return TradePrice;
    }

    public double getTradeQuantity() {
        return TradeQuantity;
    }

    public String getFirmAccount() {
        return FirmAccount;
    }

    public void setTicketId(String ticketId) {
        TicketId = ticketId;
    }

    public void setTradeId(int tradeId) {
        TradeId = tradeId;
    }

    public void setPositionId(int positionId) {
        PositionId = positionId;
    }

    public void setSecurityId(int securityId) {
        SecurityId = securityId;
    }

    public void setTradePrice(double tradePrice) {
        TradePrice = tradePrice;
    }

    public void setTradeQuantity(double tradeQuantity) {
        TradeQuantity = tradeQuantity;
    }

    public void setFirmAccount(String firmAccount) {
        FirmAccount = firmAccount;
    }

    @Override
    public String toString(){
        final StringBuilder stringBuilder = new StringBuilder("TRADE_TICKET{");
        stringBuilder.append("TicketId=").append(TicketId);
        stringBuilder.append(", TradeId=").append(TradeId).append('\'');
        stringBuilder.append(", PositionId=").append(PositionId).append('\'');
        stringBuilder.append(", SecurityId=").append(SecurityId).append('\'');
        stringBuilder.append(", TradePrice=").append(TradePrice).append('\'');
        stringBuilder.append(", TradeQuantity=").append(TradeQuantity).append('\'');
        stringBuilder.append(", FirmAccount=").append(FirmAccount);
        stringBuilder.append('}');

        return stringBuilder.toString();

    }
}
