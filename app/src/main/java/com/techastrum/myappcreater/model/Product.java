package com.techastrum.myappcreater.model;

public class Product {
    String Name,Category,Sub_Category,Price,Detail,imageurl;

    public Product(String name, String category, String sub_Category, String price, String detail, String imageurl) {
        Name = name;
        Category = category;
        Sub_Category = sub_Category;
        Price = price;
        Detail = detail;
        this.imageurl = imageurl;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getSub_Category() {
        return Sub_Category;
    }

    public void setSub_Category(String sub_Category) {
        Sub_Category = sub_Category;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
