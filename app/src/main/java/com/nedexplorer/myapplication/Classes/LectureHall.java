package com.nedexplorer.myapplication.Classes;

public class LectureHall {

    private String department;
    private String description;
    private String image;

    public LectureHall() {
    }

    public LectureHall(String department, String description, String image) {

        this.department = department;
        this.description = description;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
