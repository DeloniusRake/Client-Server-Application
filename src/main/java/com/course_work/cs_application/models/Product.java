package com.course_work.cs_application.models;

public class Product {

    private int productID;
    private int categoryID;
    private int manufacturerID;
    private String productName;
    private String img;
    private String description;
    private double price;
    private double weight;

    public int getProductID() {return productID;}
    public int getCategoryID() {return categoryID;}
    public int getManufacturerID() {return manufacturerID;}
    public String getProductName() {return productName;}
    public String getImg() {return img;}
    public String getDescription() {return description;}
    public double getPrice() {return price;}
    public double getWeight() {return weight;}

    public void setCategoryID(int categoryID) {this.categoryID = categoryID;}
    public void setProductID(int productID){this.productID = productID;}
    public void setManufacturerID(int manufacturerID) {this.manufacturerID = manufacturerID;}
    public void setProductName(String productName) {this.productName = productName;}
    public void setImg(String img) {this.img = img;}
    public void setDescription(String description) {this.description = description;}
    public void setPrice(double price) {this.price = price;}
    public void setWeight(double weight) {this.weight = weight;}

}
