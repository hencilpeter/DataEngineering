/*
 * Author : J Hencil Peter
 * Program : Data class with Catalog Detail.
 * */


package kafkacassandra;

public class CatalogDetail {
        CatalogDetail(String _PogId, String _Supc, String _Band, String _Description,
                      String _Size, String _Category, String _SubCategory, String _Price, String _Quantity,
                      String _Country, String _SellerCode, String _CreationCode, String _Stock){
            PogId = _PogId;
            Supc = _Supc;
            Band = _Band;
            Description = _Description;
            Size = _Size;
            Category = _Category;
            SubCatagory = _SubCategory;
            Price = _Price;
            Quantity = _Quantity;
            Country = _Country;
            SellerCode = _SellerCode;
            CreationCode = _CreationCode;
            Stock = _Stock;
        }
        public String PogId;
        public String Supc;
        public String Band;
        public String Description;
        public String Size;
        public String Category;
        public String SubCatagory;
        public String Price;
        public String Quantity;
        public String Country;
        public String SellerCode;
        public String CreationCode;
        public String Stock;

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

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
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


}
