package com.example.all_in_onecontroller.features.quran.model;

public class Sheikh {
    private final String id;
    private final String name;
    private final String title;
    private final boolean active;
    private final String archiveIdentifier;

    public Sheikh(String id, String name, String title, boolean active, String archiveIdentifier) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.active = active;
        this.archiveIdentifier = archiveIdentifier;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getTitle() { return title; }
    public boolean isActive() { return active; }
    public String getArchiveIdentifier() { return archiveIdentifier; }
}