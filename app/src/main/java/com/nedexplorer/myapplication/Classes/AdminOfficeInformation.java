package com.nedexplorer.myapplication.Classes;

public class AdminOfficeInformation {
    private String name;
    private String description;
    private String location;
    private String image;

    public AdminOfficeInformation() {
    }

    public AdminOfficeInformation(String name, String description, String location, String image) {
        this.name = name;
        this.description = description;
        this.location = location;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
