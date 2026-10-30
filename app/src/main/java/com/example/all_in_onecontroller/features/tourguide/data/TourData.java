package com.example.all_in_onecontroller.features.tourguide.data;

import com.example.all_in_onecontroller.R;
import com.example.all_in_onecontroller.features.tourguide.model.Attraction;

import java.util.ArrayList;
import java.util.List;

/**
 * TourData repository providing rich mock data of authentic Somali tourist attractions.
 * Offline-ready without requiring any remote backend database.
 */
public class TourData {

    public static final String CATEGORY_BEACHES = "Beautiful Beaches";
    public static final String CATEGORY_HISTORICAL = "Historical Sites";
    public static final String CATEGORY_NATURE = "Nature & Mountains";
    public static final String CATEGORY_CITIES = "Major Cities";

    public static ArrayList<Attraction> getAllAttractions() {
        ArrayList<Attraction> list = new ArrayList<>();

        // --- Beautiful Beaches ---
        list.add(new Attraction(
                "Liido Beach",
                CATEGORY_BEACHES,
                "Mogadishu, Banaadir",
                "Liido Beach is the vibrant coastal heart of Mogadishu, famous for its sweeping white sands, azure Indian Ocean waters, fresh seafood restaurants, and weekend family gatherings. A symbol of peace, recreation, and resilience.",
                R.drawable.liido_beach,
                2.0371, 45.3638
        ));

        list.add(new Attraction(
                "Kismayo Coastal Haven",
                CATEGORY_BEACHES,
                "Kismayo, Lower Juba",
                "Kismayo boasts pristine tropical coastlines fringed with Indian Ocean breezes, Bajuni island channels, and lush coastal palms. It is renowned for world-class fishing, untouched dunes, and warm coastal hospitality.",
                R.drawable.hero_kismayo,
                -0.3582, 42.5454
        ));

        list.add(new Attraction(
                "Berbera Gulf Coast",
                CATEGORY_BEACHES,
                "Berbera, Sahil",
                "Berbera features warm, tranquil waters along the Gulf of Aden with rich coral reefs, warm golden sands, and scenic sunset views across the Red Sea maritime gateway.",
                R.drawable.hero_bosaso,
                10.4396, 45.0143
        ));

        // --- Historical Sites ---
        list.add(new Attraction(
                "Laas Geel Cave Paintings",
                CATEGORY_HISTORICAL,
                "Near Hargeysa, Waqooyi Galbeed",
                "Laas Geel is one of the most magnificent and well-preserved rock art complexes in the Horn of Africa, dating back over 5,000 years (circa 3,500 - 2,500 BCE). The vivid polychrome murals depict ceremonial cows, herders, and ancient pastoral life.",
                R.drawable.laas_geel,
                9.7758, 44.4444
        ));

        list.add(new Attraction(
                "Historic Ancient Port of Zeila",
                CATEGORY_HISTORICAL,
                "Saylac, Awdal",
                "An ancient medieval trading emporium mentioned in classical Greek and Arab maritime itineraries. Zeila is home to the historic Masjid al-Qiblatayn (Mosque of the Two Qiblas), established in the earliest days of Islam with two distinct prayer directions.",
                R.drawable.hero_borama,
                11.3533, 43.4733
        ));

        list.add(new Attraction(
                "Taleh Dervish Fortress",
                CATEGORY_HISTORICAL,
                "Taleh, Sool",
                "The historic stone citadel and headquarters of the anti-colonial Dervish movement led by Sayid Mohamed Abdulle Hassan in the early 20th century. A massive complex of fortifications echoing legendary Somali history.",
                R.drawable.bg_region_hero_default,
                9.1469, 48.4231
        ));

        // --- Nature & Mountains ---
        list.add(new Attraction(
                "Cal Madow Mountain Range",
                CATEGORY_NATURE,
                "Sanaag Region",
                "A dramatic mountain escarpment rising abruptly from the Gulf of Aden coast, peaking at Shimbiris (2,450 meters), the highest point in Somalia. Featuring misty juniper forests, waterfalls, and endemic wildlife.",
                R.drawable.cal_madow,
                10.8833, 47.4167
        ));

        list.add(new Attraction(
                "Daallo Cloud Forest",
                CATEGORY_NATURE,
                "Erigavo, Sanaag",
                "A lush, ancient temperate rainforest perched on the northern cliffs of Sanaag. Rich with frankincense (Boswellia), myrrh, ancient African pencil cedars, and breathtaking canyon views plunging thousands of feet toward the coast.",
                R.drawable.cal_madow,
                10.7500, 47.3333
        ));

        // --- Major Cities ---
        list.add(new Attraction(
                "Mogadishu (The White Pearl)",
                CATEGORY_CITIES,
                "Banaadir Region",
                "The historic national capital on the Indian Ocean shore. Famed for Italian colonial architecture, the ancient Hamar Weyne quarter, lively Arba'a Rukun mosque (dating to 1268 CE), and bustling markets.",
                R.drawable.liido_beach,
                2.0469, 45.3182
        ));

        list.add(new Attraction(
                "Garowe Central City",
                CATEGORY_CITIES,
                "Nugaal Region",
                "A fast-growing, peaceful administrative capital situated at the geographic crossroads of Somalia. Home to the Puntland State House, modern universities, and vital inland trade routes.",
                R.drawable.hero_garowe,
                8.4021, 48.4845
        ));

        list.add(new Attraction(
                "Baidoa (City of Springs)",
                CATEGORY_CITIES,
                "Bay Region",
                "A vital agricultural and cultural hub nestled between the Jubba and Shabelle rivers, famous for natural freshwater springs, fertile red earth farmlands, and vibrant Somali Southern traditions.",
                R.drawable.hero_baidoa,
                3.1138, 43.6498
        ));

        list.add(new Attraction(
                "Borama Hill City",
                CATEGORY_CITIES,
                "Awdal Region",
                "A refreshing university town nestled in green rolling hills near the Ethiopian border. Known as Somalia's pioneer city of modern higher education (Amoud University) with a cool, pleasant climate.",
                R.drawable.hero_borama,
                9.9361, 43.1842
        ));

        return list;
    }

    /**
     * Filters attractions by category.
     */
    public static ArrayList<Attraction> getAttractionsByCategory(String category) {
        ArrayList<Attraction> filtered = new ArrayList<>();
        if (category == null || category.trim().isEmpty() || category.equalsIgnoreCase("All")) {
            return getAllAttractions();
        }
        for (Attraction a : getAllAttractions()) {
            if (a.getCategory().equalsIgnoreCase(category)) {
                filtered.add(a);
            }
        }
        return filtered;
    }

    /**
     * Returns count of attractions in a given category.
     */
    public static int getCategoryCount(String category) {
        return getAttractionsByCategory(category).size();
    }
}

