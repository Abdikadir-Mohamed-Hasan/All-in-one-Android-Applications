package com.example.all_in_onecontroller.features.quran.model;

public class Surah {
    private final int number;
    private final String arabicName;
    private final String transliteration;
    private final String englishMeaning;
    private final int versesCount;
    private final String revelationType; // "Makki" or "Madani"
    private final boolean hasMultipleDars;

    public Surah(int number, String arabicName, String transliteration, String englishMeaning, int versesCount, String revelationType, boolean hasMultipleDars) {
        this.number = number;
        this.arabicName = arabicName;
        this.transliteration = transliteration;
        this.englishMeaning = englishMeaning;
        this.versesCount = versesCount;
        this.revelationType = revelationType;
        this.hasMultipleDars = hasMultipleDars;
    }

    public int getNumber() { return number; }
    public String getArabicName() { return arabicName; }
    public String getTransliteration() { return transliteration; }
    public String getEnglishMeaning() { return englishMeaning; }
    public int getVersesCount() { return versesCount; }
    public String getRevelationType() { return revelationType; }
    public boolean isHasMultipleDars() { return hasMultipleDars; }
}