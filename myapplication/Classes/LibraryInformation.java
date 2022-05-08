package com.nedexplorer.myapplication.Classes;

public class LibraryInformation {
    private String description;
    private String image;
    private String location;
    private String name;
    private String section;
    private String timings;
    private String ClosedOn;

    public String getClosedOn() {
        return ClosedOn;
    }

    public void setClosedOn(String closedOn) {
        ClosedOn = closedOn;
    }

    public LibraryInformation(){

    }

    public LibraryInformation(String description, String image, String location, String name, String section, String timings, String closedOn) {
        this.description = description;
        this.image = image;
        this.location = location;
        this.name = name;
        this.section = section;
        this.timings = timings;
        ClosedOn = closedOn;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }
}
