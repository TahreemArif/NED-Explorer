package com.nedexplorer.myapplication.Classes;

import org.json.JSONObject;
import java.util.HashMap;

public class Marker {

    private double latitude;
    private double longitude;
    private String type;
    private String title;
    public Marker(){

    }

    public Marker(double latitude, double longitude, String type, String title) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
        this.title = title;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public JSONObject toJSONObject(){

        HashMap<String,String> MarkerInformation = new HashMap<>();
        MarkerInformation.put("latitude",String.valueOf(this.getLatitude()));
        MarkerInformation.put("longitude",String.valueOf(this.getLongitude()));
        MarkerInformation.put("type",this.getType());
        MarkerInformation.put("title",this.getTitle());
        return new JSONObject(MarkerInformation);

    }
}
