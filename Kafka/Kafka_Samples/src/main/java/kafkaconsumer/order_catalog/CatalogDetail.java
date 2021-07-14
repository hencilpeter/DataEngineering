/*
 * Author : J Hencil Peter
 * Program : Data class with Catalog Detail.
 * */


package kafkaconsumer.order_catalog;

public class CatalogDetail {
        CatalogDetail(String _PogId, String _Supc, String _Band, String _Description,
                       String _Size,String _Category,String _SubCategory,String _Price,String _Quantity,
                       String _Country,String _SellerCode,String _CreationCode, String _Stock){
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
}
