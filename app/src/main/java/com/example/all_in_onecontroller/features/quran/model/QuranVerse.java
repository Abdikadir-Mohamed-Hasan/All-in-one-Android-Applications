package com.example.all_in_onecontroller.features.quran.model;

public class QuranVerse {
    private final int surahNumber;
    private final int verseNumber;
    private final String uthmaniText;
    private final String simpleText;

    public QuranVerse(int surahNumber, int verseNumber, String uthmaniText, String simpleText) {
        this.surahNumber = surahNumber;
        this.verseNumber = verseNumber;
        this.uthmaniText = uthmaniText;
        this.simpleText = simpleText;
    }

    public int getSurahNumber() { return surahNumber; }
    public int getVerseNumber() { return verseNumber; }
    public String getUthmaniText() { return uthmaniText; }
    public String getSimpleText() { return simpleText; }
}