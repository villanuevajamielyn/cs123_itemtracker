package com.example.cs123_itemtracker;

import java.util.ArrayList;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Item extends RealmObject{

    @PrimaryKey
    private String item_ID;

    private String name;

    private String description;

    private String location;

    private double gpsPingLatitude;

    private double gpsPingLongitude;

    private long timeStamp;

    private String imageName;

/*    private ArrayList<String> categories;

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }*/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getGpsPingLatitude() {
        return gpsPingLatitude;
    }

    public void setGpsPingLatitude(double gpsPingLatitude) {
        this.gpsPingLatitude = gpsPingLatitude;
    }

    public String getItem_ID() {
        return item_ID;
    }

    public void setItem_ID(String item_ID) {
        this.item_ID = item_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getGpsPingLongitude() {
        return gpsPingLongitude;
    }

    public void setGpsPingLongitude(double gpsPingLongitude) {
        this.gpsPingLongitude = gpsPingLongitude;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getImageName() {
        return item_ID+".jpeg";
    }

    @Override
    public String toString() {
        return "Item{" +
                "item_ID='" + item_ID + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", gpsPingLatitude=" + gpsPingLatitude +
                ", gpsPingLongitude=" + gpsPingLongitude +
                ", timeStamp=" + timeStamp +
                ", imageName='" + imageName + '\'' +
                '}';
    }
}
