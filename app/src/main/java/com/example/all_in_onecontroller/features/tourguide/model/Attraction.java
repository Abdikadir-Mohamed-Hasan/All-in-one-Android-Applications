package com.example.all_in_onecontroller.features.tourguide.model;

import java.io.Serializable;

/**
 * Robust Java Data Model for a Somali tourist attraction.
 * Implements Serializable so it can be passed across Activities via Android Intents.
 */
public class Attraction implements Serializable {
    private String name;
    private String category;
    private String location;
    private String description;
    private int imageResource;
    private double latitude;
    private double longitude;

    public Attraction() {
    }

    public Attraction(String name, String category, String location, String description,
                      int imageResource, double latitude, double longitude) {
        this.name = name;
        this.category = category;
        this.location = location;
        this.description = description;
        this.imageResource = imageResource;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
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
}

