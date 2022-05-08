package com.nedexplorer.myapplication.Classes;

import android.webkit.SafeBrowsingResponse;

public class CanteenInformation {
    private String image;
    private String menu;
    private String name;
    private String timings;
    private String closedOn;

    public CanteenInformation(){

    }
    public CanteenInformation(String image, String menu, String name, String timings, String closedOn) {
        this.image = image;
        this.menu = menu;
        this.name = name;
        this.timings = timings;
        this.closedOn = closedOn;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
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

    public String getClosedOn() {
        return closedOn;
    }

    public void setClosedOn(String closedOn) {
        this.closedOn = closedOn;
    }
}
