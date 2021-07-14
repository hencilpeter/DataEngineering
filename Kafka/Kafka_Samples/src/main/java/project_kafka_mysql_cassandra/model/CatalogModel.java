/*
 * Author : J Hencil Peter
 * Program : Catalog model class
 * */

package project_kafka_mysql_cassandra.model;

public class CatalogModel {
    public String PogId;
    public String Supc;
    public String Band;
    public String Description;
    public String Size;
    public String Category;
    public String SubCatagory;
    public String Price;
    public String Quantity;

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String Country;
    public String SellerCode;

    public String getPogId() {
        return PogId;
    }

    public void setPogId(String pogId) {
        PogId = pogId;
    }

    public String getSupc() {
        return Supc;
    }

    public void setSupc(String supc) {
        Supc = supc;
    }

    public String getBand() {
        return Band;
    }

    public void setBand(String band) {
        Band = band;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getSubCatagory() {
        return SubCatagory;
    }

    public void setSubCatagory(String subCatagory) {
        SubCatagory = subCatagory;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getSellerCode() {
        return SellerCode;
    }

    public void setSellerCode(String sellerCode) {
        SellerCode = sellerCode;
    }

    public String getCreationCode() {
        return CreationCode;
    }

    public void setCreationCode(String creationCode) {
        CreationCode = creationCode;
    }

    public String getStock() {
        return Stock;
    }

    public void setStock(String stock) {
        Stock = stock;
    }

    public String CreationCode;
    public String Stock;

    public CatalogModel(String pogId, String supc, String band, String description, String size, String category, String subCatagory, String price,
                        String Country, String quantity, String sellerCode, String creationCode, String stock) {
        this.PogId = pogId;
        this.Supc = supc;
        this.Band = band;
        this.Description = description;
        this.Size = size;
        this.Category = category;
        this.SubCatagory = subCatagory;
        this.Price = price;
        this.Country = Country;
        this.Quantity = quantity;
        this.SellerCode = sellerCode;
        this.CreationCode = creationCode;
        this.Stock = stock;
    }

    public CatalogModel() {
        this.PogId = "";
        this.Supc = "";
        this.Band = "";
        this.Description = "";
        this.Size = "";
        this.Category = "";
        this.SubCatagory = "";
        this.Price = "";
        this.Quantity = "";
        this.SellerCode = "";
        this.CreationCode = "";
        this.Stock = "";
    }

}
