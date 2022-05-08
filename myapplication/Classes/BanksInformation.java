package com.nedexplorer.myapplication.Classes;

public class BanksInformation {
    private String branch;
    private String closedOn;
    private String description;
    private String image;
    private String name;
    private String timings;

    public BanksInformation(){

    }

    public BanksInformation(String branch, String closedOn, String description, String image, String name, String timings) {
        this.branch = branch;
        this.closedOn = closedOn;
        this.description = description;
        this.image = image;
        this.name = name;
        this.timings = timings;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getClosedOn() {
        return closedOn;
    }

    public void setClosedOn(String closedOn) {
        this.closedOn = closedOn;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }
}
