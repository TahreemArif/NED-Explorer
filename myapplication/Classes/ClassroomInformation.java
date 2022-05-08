package com.nedexplorer.myapplication.Classes;

public class ClassroomInformation {
    private String name;
    private String department;
    private String location;
    private String timeTable;

    public ClassroomInformation(){

    }
    public ClassroomInformation(String name, String department, String location, String timeTable) {
        this.name = name;
        this.department = department;
        this.location = location;
        this.timeTable = timeTable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(String timeTable) {
        this.timeTable = timeTable;
    }
}
