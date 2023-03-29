package com.example.pmjewellers.ui.home;


import com.google.firebase.firestore.auth.User;

public class HomeModel {
    String ProductImage,ProductName,ProductCategory,ProductOffer,ProductPrice;
    public HomeModel(String ProductImage , String ProductName, String ProductCategory,String ProductOffer,String ProductPrice,String UserName){
        this.ProductImage = ProductImage;
        this.ProductName = ProductName;
        this.ProductCategory = ProductCategory;
        this.ProductOffer = ProductOffer;
        this.ProductPrice = ProductPrice;

    }
    public HomeModel(String image){
        this.ProductImage = image;
    }

    public HomeModel(){}

    public String getProductCategory() {
        return ProductCategory;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
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


