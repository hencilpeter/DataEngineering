package kafkastream.invoiceproducer;

/*
* Author : J Hencil Peter
* Description: Invoice data class.
* */

import java.time.LocalDateTime;

public class Invoice {
    public int invoiceNumber;
    public LocalDateTime date;
    public String customerName;
    public long invoiceAmount;
    public String cityCode;
    public long discount;
    public long total;
    public Invoice(){

    }

    public Invoice(int invoiceNumber, LocalDateTime date, String customerName, long invoiceAmount, String cityCode, long discount, long total) {
        this.invoiceNumber = invoiceNumber;
        this.date = date;
        this.customerName = customerName;
        this.invoiceAmount = invoiceAmount;
        this.cityCode = cityCode;
        this.discount = discount;
        this.total = total;
    }

    public Invoice(int invoiceNumber, String customerName,
                   long invoiceAmount, String cityCode) {
        this.invoiceNumber = invoiceNumber;
        this.date = LocalDateTime.now();
        this.customerName = customerName;
        this.invoiceAmount = invoiceAmount;
        this.cityCode = cityCode;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public long getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(long invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public long getDiscount() {
        return discount;
    }

    public void setDiscount(long discount) {
        this.discount = discount;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
