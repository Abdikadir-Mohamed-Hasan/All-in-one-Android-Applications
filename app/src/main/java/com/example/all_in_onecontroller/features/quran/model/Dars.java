package com.example.all_in_onecontroller.features.quran.model;

public class Dars {
    private final String id;
    private final int surahNumber;
    private final int darsNumber;
    private final String title;
    private final String verseRange;
    private final String audioUrl;
    private final String archiveIdentifier;
    private final String fileName;
    private final boolean verified;

    public Dars(String id, int surahNumber, int darsNumber, String title, String verseRange, String audioUrl, String archiveIdentifier, String fileName, boolean verified) {
        this.id = id;
        this.surahNumber = surahNumber;
        this.darsNumber = darsNumber;
        this.title = title;
        this.verseRange = verseRange;
        this.audioUrl = audioUrl;
        this.archiveIdentifier = archiveIdentifier;
        this.fileName = fileName;
        this.verified = verified;
    }

    public String getId() { return id; }
    public int getSurahNumber() { return surahNumber; }
    public int getDarsNumber() { return darsNumber; }
    public String getTitle() { return title; }
    public String getVerseRange() { return verseRange; }
    public String getAudioUrl() { return audioUrl; }
    public String getArchiveIdentifier() { return archiveIdentifier; }
    public String getFileName() { return fileName; }
    public boolean isVerified() { return verified; }
}