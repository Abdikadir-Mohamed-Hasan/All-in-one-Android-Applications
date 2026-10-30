package com.example.all_in_onecontroller.features.barodalkaga.data;

import com.example.all_in_onecontroller.features.barodalkaga.model.Region;

import java.util.ArrayList;
import java.util.List;

/**
 * Local in-memory repository providing historical, geographical, and hero imagery
 * metadata for all 18 administrative regions of Somalia.
 */
public class RegionDatabase {

    public static List<Region> getAllRegions() {
        List<Region> regions = new ArrayList<>();

        // 1. Awdal (Northern)
        regions.add(new Region(
                "awdal",
                "Awdal",
                "Borama",
                "1984",
                "Amoud University, lush agriculture, and the historic ancient port of Zeila (Saylac)",
                "Awdal is situated in the northwesternmost corner of Somalia. It has a rich history tied to the medieval Adal Sultanate, lush mountainous terrain, and holds the oldest higher education institution in modern Somali history (Amoud University).",
                "Northern",
                "hero_borama"
        ));

        // 2. Waqooyi Galbeed (Northern)
        regions.add(new Region(
                "waqooyi_galbeed",
                "Waqooyi Galbeed",
                "Hargeysa",
                "1960",
                "Laas Geel prehistoric cave paintings, vibrant livestock commerce, and Somali poetry",
                "Waqooyi Galbeed is a pivotal cultural and trade hub in northern Somalia. Its capital, Hargeysa, is famous for lively markets, cultural arts, and the world-renowned Neolithic rock art site at Laas Geel dating back over 5,000 years.",
                "Northern",
                "laas_geel"
        ));

        // 3. Togdheer (Northern)
        regions.add(new Region(
                "togdheer",
                "Togdheer",
                "Burao",
                "1973",
                "Burao Grand Livestock Market, renowned classical poets, and the seasonal Togdheer River",
                "Togdheer is named after the Togdheer River flowing through it. The region is widely celebrated as the cradle of legendary Somali poetry, folklore, and one of the largest livestock trading centers in the Horn of Africa.",
                "Northern",
                "hero_burao"
        ));

        // 4. Sanaag (Northern)
        regions.add(new Region(
                "sanaag",
                "Sanaag",
                "Erigavo (Ceerigaabo)",
                "1984",
                "Mount Shimbiris (highest peak in Somalia), Cal Madow forests, natural frankincense harvesting",
                "Sanaag is the largest region in northern Somalia, renowned for its dramatic mountain ranges, Daallo National Forest, natural frankincense harvesting, and pristine Gulf of Aden coastline.",
                "Northern",
                "cal_madow"
        ));

        // 5. Bari (Northern)
        regions.add(new Region(
                "bari",
                "Bari",
                "Bosaso (Boosaaso)",
                "1960",
                "Major commercial deep seaport, date palm plantations, Cape Guardafui, and ancient spice trade",
                "Bari occupies the tip of the Horn of Africa. Boosaaso is an energetic port connecting Somalia to the Arabian Peninsula. The region features historic lighthouses, date groves, and extensive marine fisheries.",
                "Northern",
                "hero_bosaso"
        ));

        // 6. Sool (Northern)
        regions.add(new Region(
                "sool",
                "Sool",
                "Las Anod (Laascaanood)",
                "1984",
                "Taleh historical Dervish stone forts, camel pastoralism, and patriotic heritage",
                "Sool is steeped in history as the capital of the anti-colonial Dervish movement led by Sayid Mohamed Abdullah Hassan in Taleh. The region is also famous for nomadic livestock wealth and rich oral poetry.",
                "Northern",
                "hero_las_anod"
        ));

        // 7. Nugaal (Central)
        regions.add(new Region(
                "nugaal",
                "Nugaal",
                "Garowe (Garoowe)",
                "1973",
                "Nugaal Valley pastoral grazing lands, administrative institutions, and central crossroads",
                "Nugaal connects the northern and central parts of Somalia through its scenic valley. Garowe is an important political center with a booming educational, governmental, and commercial sector.",
                "Central",
                "hero_garowe"
        ));

        // 8. Mudug (Central)
        regions.add(new Region(
                "mudug",
                "Mudug",
                "Galkayo (Gaalkacyo)",
                "1960",
                "Central trade crossroads, Hobyo ancient sultanate coastal port, and livestock trade",
                "Mudug sits in central Somalia with an expansive coastline along the Indian Ocean featuring the historic sultanate town of Hobyo. Galkayo serves as a major commercial transit point connecting south and north.",
                "Central",
                "hero_galkayo"
        ));

        // 9. Galguduud (Central)
        regions.add(new Region(
                "galguduud",
                "Galguduud",
                "Dhusamareb (Dhuusamarreeb)",
                "1973",
                "Pastoral heritage, traditional governance, and central commercial trade routes",
                "Galguduud is located in central Somalia, bordered by Ethiopia and the Indian Ocean. It is known for resilient livestock pastoralism and strategic crossroads towns like Cadaado and Guriceel.",
                "Central",
                "hero_dhusamareb"
        ));

        // 10. Hiran (Central)
        regions.add(new Region(
                "hiran",
                "Hiran",
                "Beledweyne",
                "1960",
                "Shabelle River agriculture, sweet melons, and the historic Beledweyne River Bridge",
                "Hiran is bisected by the Shabelle River, creating fertile agricultural lands along its banks. Beledweyne is an ancient commercial and transportation hub connecting southern Somalia to the central regions.",
                "Central",
                "hero_beledweyne"
        ));

        // 11. Middle Shabelle (Southern)
        regions.add(new Region(
                "middle_shabelle",
                "Middle Shabelle",
                "Jowhar",
                "1973",
                "Sugarcane plantations, rice and maize farming, and the Shabelle River irrigation delta",
                "Middle Shabelle (Shabeellaha Dhexe) has historically been an agricultural breadbasket, known for sugar production, tropical fruits, and scenic riverine farming villages along the Shabelle River.",
                "Southern",
                "hero_jowhar"
        ));

        // 12. Banaadir (Southern)
        regions.add(new Region(
                "banaadir",
                "Banaadir",
                "Mogadishu (Muqdisho)",
                "1960",
                "National Capital City, Indian Ocean beaches (Lido), historic Hamar Weyne, and trade",
                "Banaadir is the smallest yet most populous region, encompassing the capital city of Mogadishu. It boasts centuries-old mosques, Italian colonial architecture, lively universities, and port trade.",
                "Southern",
                "liido_beach"
        ));

        // 13. Lower Shabelle (Southern)
        regions.add(new Region(
                "lower_shabelle",
                "Lower Shabelle",
                "Merca (Marka)",
                "1984",
                "Banana and citrus plantations, Indian Ocean fisheries, and historic coastal architecture",
                "Lower Shabelle (Shabeellaha Hoose) is famous for fertile farmland producing bananas, mangoes, and papayas, as well as historic trade ports like Merca and Baraawe with distinct stone architecture.",
                "Southern",
                "hero_merca"
        ));

        // 14. Bakool (Southern)
        regions.add(new Region(
                "bakool",
                "Bakool",
                "Hudur (Xudur)",
                "1973",
                "Sorghum agriculture, cattle breeding, and resilient regional market towns",
                "Bakool is situated in southwestern Somalia bordering Ethiopia. It is known for dryland grain farming (sorghum), pastoral communities, and resilient local trade centers.",
                "Southern",
                "hero_hudur"
        ));

        // 15. Bay (Southern)
        regions.add(new Region(
                "bay",
                "Bay",
                "Baidoa (Baydhabo)",
                "1960",
                "City of Sorghum ('Baydhabo Janaay'), natural water springs, and rich agricultural harvests",
                "Bay is widely called the granary of Somalia. Baidoa, its capital, enjoys abundant underground water springs, limestone formations, and produces a major share of the nation's grains.",
                "Southern",
                "hero_baidoa"
        ));

        // 16. Gedo (Southern)
        regions.add(new Region(
                "gedo",
                "Gedo",
                "Garbahareey",
                "1980",
                "Juba River valleys, tri-border trade (Kenya & Ethiopia), and lush riverine farms",
                "Gedo is located in southwestern Somalia, traversed by the Juba River and sharing borders with both Ethiopia and Kenya. Important economic towns include Luuq, Baardheere, and Beled Xaawo.",
                "Southern",
                "hero_garbahareey"
        ));

        // 17. Middle Juba (Southern)
        regions.add(new Region(
                "middle_juba",
                "Middle Juba",
                "Bu'aale",
                "1984",
                "Rich agricultural river plains, natural forestry, and traditional artisanal river fishing",
                "Middle Juba (Jubbada Dhexe) lies along the lower reaches of the Juba River. Its fertile alluvial soil supports extensive farming, tropical wildlife, and natural forests.",
                "Southern",
                "hero_buale"
        ));

        // 18. Lower Juba (Southern)
        regions.add(new Region(
                "lower_juba",
                "Lower Juba",
                "Kismayo (Kismaayo)",
                "1960",
                "Kismayo deepwater port, Bajuni Islands, livestock export, and Indian Ocean fisheries",
                "Lower Juba (Jubbada Hoose) sits in the southern tip of Somalia. Kismayo is an economic powerhouse with an active port, beautiful coastline, nearby national parks, and fertile river mouths.",
                "Southern",
                "hero_kismayo"
        ));

        return regions;
    }

    public static Region getRegionByName(String name) {
        if (name == null) return null;
        for (Region r : getAllRegions()) {
            if (r.getName().equalsIgnoreCase(name.trim())) {
                return r;
            }
        }
        return null;
    }

    public static List<Region> getRegionsByZone(String zone) {
        if (zone == null || zone.equalsIgnoreCase("All")) {
            return getAllRegions();
        }
        List<Region> filtered = new ArrayList<>();
        for (Region r : getAllRegions()) {
            if (r.getZone().equalsIgnoreCase(zone.trim())) {
                filtered.add(r);
            }
        }
        return filtered;
    }
}
