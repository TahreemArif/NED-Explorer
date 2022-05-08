package com.nedexplorer.myapplication.Classes;

public class ShopInformation {

    private String name;
    private String description;
    private String image;

    public ShopInformation() {
    }

    public ShopInformation(String name, String description, String image) {

        this.name = name;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
