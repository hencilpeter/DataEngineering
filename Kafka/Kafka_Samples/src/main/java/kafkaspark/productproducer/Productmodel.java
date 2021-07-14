package kafkaspark.productproducer;


public class Productmodel {

    private static final long serialVersionUID = 1L;

    private String TransactionDate;
    private String Product;
    private String Price;
    private String PaymentType;
    private String Name;
    private String City;
    private String State;
    private String Country;
    private String AccountCreated;
    private String LastLogin;
    private String Latitude;
    private String Longitude;


    public Productmodel(String transactionDate, String product, String price, String paymentType, String name, String city, String state, String country, String accountCreated, String lastLogin, String latitude, String longitude) {
        TransactionDate = transactionDate;
        Product = product;
        Price = price;
        PaymentType = paymentType;
        Name = name;
        City = city;
        State = state;
        Country = country;
        AccountCreated = accountCreated;
        LastLogin = lastLogin;
        Latitude = latitude;
        Longitude = longitude;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTransactionDate() {
        return TransactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        TransactionDate = transactionDate;
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String product) {
        Product = product;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getPaymentType() {
        return PaymentType;
    }

    public void setPaymentType(String paymentType) {
        PaymentType = paymentType;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getAccountCreated() {
        return AccountCreated;
    }

    public void setAccountCreated(String accountCreated) {
        AccountCreated = accountCreated;
    }

    public String getLastLogin() {
        return LastLogin;
    }

    public void setLastLogin(String lastLogin) {
        LastLogin = lastLogin;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }


}
