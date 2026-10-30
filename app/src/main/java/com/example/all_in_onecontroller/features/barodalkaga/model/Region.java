package com.example.all_in_onecontroller.features.barodalkaga.model;

import android.content.Context;
import java.io.Serializable;

/**
 * Data model representing a Somali region (Gobol).
 * Holds administrative attributes, geographic zone, and hero imagery metadata.
 */
public class Region implements Serializable {
    private final String id;
    private final String name;
    private final String capitalCity;
    private final String establishedYear;
    private final String famousFor;
    private final String description;
    private final String zone;              // "Northern", "Central", "Southern"
    private final String imageDrawableName; // Resource identifier for the capital hero image

    public Region(String id, String name, String capitalCity, String establishedYear,
                  String famousFor, String description, String zone, String imageDrawableName) {
        this.id = id;
        this.name = name;
        this.capitalCity = capitalCity;
        this.establishedYear = establishedYear;
        this.famousFor = famousFor;
        this.description = description;
        this.zone = zone;
        this.imageDrawableName = imageDrawableName;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCapitalCity() {
        return capitalCity;
    }

    public String getEstablishedYear() {
        return establishedYear;
    }

    public String getFamousFor() {
        return famousFor;
    }

    public String getDescription() {
        return description;
    }

    public String getZone() {
        return zone != null ? zone : "Somalia";
    }

    public String getImageDrawableName() {
        return imageDrawableName;
    }

    /**
     * Dynamically resolves the hero image drawable resource ID.
     * Looks up the primary imageDrawableName, attempts normalized variations,
     * and falls back to a beautiful default hero gradient so the UI is always reliable.
     */
    public int getImageResId(Context context) {
        if (context == null) return 0;

        // 1. Try explicit image drawable name
        if (imageDrawableName != null && !imageDrawableName.trim().isEmpty()) {
            int resId = context.getResources().getIdentifier(imageDrawableName.trim(), "drawable", context.getPackageName());
            if (resId != 0) return resId;
        }

        // 2. Try city name prefix (e.g. "hero_borama", "borama")
        if (capitalCity != null) {
            String sanitized = capitalCity.toLowerCase().split("[^a-z0-9]")[0];
            int res = context.getResources().getIdentifier("hero_" + sanitized, "drawable", context.getPackageName());
            if (res != 0) return res;

            res = context.getResources().getIdentifier(sanitized, "drawable", context.getPackageName());
            if (res != 0) return res;
        }

        // 3. Graceful fallback to default hero background
        int defaultHero = context.getResources().getIdentifier("bg_region_hero_default", "drawable", context.getPackageName());
        if (defaultHero != 0) return defaultHero;

        return context.getResources().getIdentifier("somalia_map", "drawable", context.getPackageName());
    }
}
