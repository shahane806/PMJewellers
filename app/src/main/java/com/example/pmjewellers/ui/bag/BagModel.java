package com.example.pmjewellers.ui.bag;


public class BagModel {
    String ProductImage,ProductName,ProductCategory,ProductOffer,ProductPrice;
     String TotalCost = "0";
    static String Username;
    public BagModel(String ProductImage , String ProductName, String ProductCategory,String ProductOffer,String ProductPrice){
        this.ProductImage = ProductImage;
        this.ProductName = ProductName;
        this.ProductCategory = ProductCategory;
        this.ProductOffer = ProductOffer;
        this.ProductPrice = ProductPrice;

    }

    public  String getTotalCost() {
        return TotalCost;
    }

    public  void setTotalCost(String totalCost) {
        this.TotalCost = totalCost;
    }

    public BagModel(String image){
        this.ProductImage = image;
    }

    public BagModel(){}

    public String getProductCategory() {
        return ProductCategory;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getUsername() {
        return Username;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public String setProductPrice(String productPrice) {
        ProductPrice = productPrice;
        return productPrice;
    }

    public void setProductCategory(String productCategory) {
        ProductCategory = productCategory;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String productImage) {
        ProductImage = productImage;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }
    public void setProductOffer(String productOffer){
        ProductOffer = productOffer;
    }

    public String getProductOffer() {
        return ProductOffer;
    }


}


