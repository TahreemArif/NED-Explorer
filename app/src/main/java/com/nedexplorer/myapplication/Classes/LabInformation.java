package com.nedexplorer.myapplication.Classes;

public class LabInformation {
    private String name;
    private String department;
    private String description;
    private String labIncharge;
    private String image;
    private String timings;
    private String closedOn;

    public LabInformation(String name, String department, String description, String labIncharge, String image, String timings, String closedOn) {
        this.name = name;
        this.department = department;
        this.description = description;
        this.labIncharge = labIncharge;
        this.image = image;
        this.timings = timings;
        this.closedOn = closedOn;
    }

    public LabInformation() {
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabIncharge() {
        return labIncharge;
    }

    public void setLabIncharge(String labIncharge) {
        this.labIncharge = labIncharge;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
