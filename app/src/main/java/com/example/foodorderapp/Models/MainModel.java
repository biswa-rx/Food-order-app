package com.example.foodorderapp.Models;

public class MainModel {
    private int image;
    private String foodName;
    private String price;
    private String description;

    public MainModel(int image, String foodName, String price, String description) {
        this.image = image;
        this.foodName = foodName;
        this.price = price;
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
