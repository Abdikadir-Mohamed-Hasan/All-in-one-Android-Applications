package com.example.all_in_onecontroller.features.quran.data;

import java.util.HashMap;
import java.util.Map;

public class QuranTranslationProvider {

    private static final Map<String, String> somaliTranslations = new HashMap<>();

    static {
        // Surah Al-Fatiha (Ayah 1 to 7 without Bismillah counted as a verse)
        somaliTranslations.put("1_1", "Mahad dhammaanteed Eebbaa iska leh oo ah Barbaariyaha caalamka.");
        somaliTranslations.put("1_2", "Waana Naxariis guud iyo mid gaaraba Naxariista.");
        somaliTranslations.put("1_3", "Waana Boqorka Maalinta Abaalmarinta (Qayaamada).");
        somaliTranslations.put("1_4", "Adiga kaliya ayaan ku caabudaynaa, Adiga kaliyana ayaan kaalmo kaa waydiisanaynaa.");
        somaliTranslations.put("1_5", "Eebbow nagu toosi jidka toosan.");
        somaliTranslations.put("1_6", "Jidka kuwii aad u nimcaysay.");
        somaliTranslations.put("1_7", "Aan ahayn kuwii loo carooday iyo kuwa lumay toona.");

        // Surah Al-Ikhlas (112:1 - 112:4)
        somaliTranslations.put("112_1", "Dheh: Isagu Eebbe waa Mid Keliya.");
        somaliTranslations.put("112_2", "Eebbe waa kan wax kasta loo baahdo oo isagu aan cidna u baahnayn.");
        somaliTranslations.put("112_3", "Waxna ma dhalin isagana lama dhalin.");
        somaliTranslations.put("112_4", "Wax la mid ah ama la simanna ma jiro.");

        // Surah Al-Falaq (113:1 - 113:5)
        somaliTranslations.put("113_1", "Dheh: Waxaan magangalayaa Eebbaha waaberiga.");
        somaliTranslations.put("113_2", "Sharkii waxa uu abuuray oo dhan.");
        somaliTranslations.put("113_3", "Iyo sharka habeenka marka uu gudcurka noqdo.");
        somaliTranslations.put("113_4", "Iyo sharka kuwa guntimaha afuufa (sixiroolayaasha).");
        somaliTranslations.put("113_5", "Iyo sharka xaasidka marka uu wax xaasido.");

        // Surah An-Nas (114:1 - 114:6)
        somaliTranslations.put("114_1", "Dheh: Waxaan magangalayaa Eebbaha dadka.");
        somaliTranslations.put("114_2", "Boqorka dadka.");
        somaliTranslations.put("114_3", "Ilaaha dadka.");
        somaliTranslations.put("114_4", "Sharkii waswaasiga dhuunta (shaydaanka).");
        somaliTranslations.put("114_5", "Kee ah kan ku waswaasiya laabaha dadka.");
        somaliTranslations.put("114_6", "Hadday jinn yihiin iyo hadday dad yihiinba.");
    }

    public static String getSomaliTranslation(int surah, int ayah) {
        String key = surah + "_" + ayah;
        String translation = somaliTranslations.get(key);
        if (translation != null) {
            return translation;
        }
        return "Tafsiirka Aayadda " + ayah + " ee Suuradda " + surah + " (Dhageyso codka sheikha sare).";
    }
}
