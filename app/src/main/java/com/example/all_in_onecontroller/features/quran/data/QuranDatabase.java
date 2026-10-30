package com.example.all_in_onecontroller.features.quran.data;

import android.content.Context;

import com.example.all_in_onecontroller.features.quran.model.Dars;
import com.example.all_in_onecontroller.features.quran.model.QuranVerse;
import com.example.all_in_onecontroller.features.quran.model.Sheikh;
import com.example.all_in_onecontroller.features.quran.model.Surah;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class QuranDatabase {

    public static List<Sheikh> getSheikhs() {
        List<Sheikh> list = new ArrayList<>();
        list.add(new Sheikh("dirir", "Sh. Maxamed Cumar Dirir", "Tafsiirka Quraanka Kariimka", true, "TafsiirQuraankaKariimka-AfSoomali"));
        list.add(new Sheikh("faruq", "Sh. Cumar Xaaji Cabdi Suldaan (Faaruuq)", "Tafsiirka Cumar Faaruuq", false, "Tafsiirka_Cumar_Faaruuq_1"));
        list.add(new Sheikh("xayi", "Sh. Cabdi Xayi Sheekh Aadan", "Tafsiirka Af-Somali", false, null));
        list.add(new Sheikh("qudubi", "Dr. Sh. Axmed Qudubi", "Tafsiirka Af-Somali", false, null));
        list.add(new Sheikh("suuley", "Sh. Maxamuud Sh. Ibrahim Suuley", "Tafsiirka Af-Somali", false, null));
        return list;
    }

    public static List<Sheikh> getReciters() {
        List<Sheikh> list = new ArrayList<>();
        list.add(new Sheikh("suufi", "Sheikh Abdul Rashid Ali Sufi", "Tilawaadka Quraanka Kariimka (Riwayah Hafs)", true, "local"));
        return list;
    }

    public static List<Surah> getSurahs() {
        List<Surah> list = new ArrayList<>();
        list.add(new Surah(1, "الفاتحة", "Al-Fatiha", "The Opening", 7, "Makki", false));
        list.add(new Surah(2, "البقرة", "Al-Baqarah", "The Cow", 286, "Madani", true));
        list.add(new Surah(3, "آل عمران", "Ali 'Imran", "Family of Imran", 200, "Madani", true));
        list.add(new Surah(4, "النساء", "An-Nisa", "The Women", 176, "Madani", true));
        list.add(new Surah(5, "المائدة", "Al-Ma'idah", "The Table Spread", 120, "Madani", true));
        list.add(new Surah(6, "الأنعام", "Al-An'am", "The Cattle", 165, "Makki", true));
        list.add(new Surah(7, "الأعراف", "Al-A'raf", "The Heights", 206, "Makki", true));
        list.add(new Surah(8, "الأنفال", "Al-Anfal", "The Spoils of War", 75, "Madani", false));
        list.add(new Surah(9, "التوبة", "At-Tawbah", "The Repentance", 129, "Madani", true));
        list.add(new Surah(10, "يونس", "Yunus", "Jonah", 109, "Makki", false));
        list.add(new Surah(11, "هود", "Hud", "Hud", 123, "Makki", false));
        list.add(new Surah(12, "يوسف", "Yusuf", "Joseph", 111, "Makki", false));
        list.add(new Surah(13, "الرعد", "Ar-Ra'd", "The Thunder", 43, "Madani", false));
        list.add(new Surah(14, "إبراهيم", "Ibrahim", "Abraham", 52, "Makki", false));
        list.add(new Surah(15, "الحجر", "Al-Hijr", "The Rocky Tract", 99, "Makki", false));
        list.add(new Surah(16, "النحل", "An-Nahl", "The Bee", 128, "Makki", false));
        list.add(new Surah(17, "الإسراء", "Al-Isra", "The Night Journey", 111, "Makki", false));
        list.add(new Surah(18, "الكهف", "Al-Kahf", "The Cave", 110, "Makki", true));
        list.add(new Surah(19, "مريم", "Maryam", "Mary", 98, "Makki", false));
        list.add(new Surah(20, "طه", "Taha", "Ta-Ha", 135, "Makki", false));
        list.add(new Surah(21, "الأنبياء", "Al-Anbiya", "The Prophets", 112, "Makki", false));
        list.add(new Surah(22, "الحج", "Al-Hajj", "The Pilgrimage", 78, "Madani", false));
        list.add(new Surah(23, "المؤمنون", "Al-Mu'minun", "The Believers", 118, "Makki", false));
        list.add(new Surah(24, "النور", "An-Nur", "The Light", 64, "Madani", false));
        list.add(new Surah(25, "الفرقان", "Al-Furqan", "The Criterion", 77, "Makki", false));
        list.add(new Surah(26, "الشعراء", "Ash-Shu'ara", "The Poets", 227, "Makki", false));
        list.add(new Surah(27, "النمل", "An-Naml", "The Ant", 93, "Makki", false));
        list.add(new Surah(28, "القصص", "Al-Qasas", "The Stories", 88, "Makki", false));
        list.add(new Surah(29, "العنكبوت", "Al-Ankabut", "The Spider", 69, "Makki", false));
        list.add(new Surah(30, "الروم", "Ar-Rum", "The Romans", 60, "Makki", false));
        list.add(new Surah(31, "لقمان", "Luqman", "Luqman", 34, "Makki", false));
        list.add(new Surah(32, "السجدة", "As-Sajdah", "The Prostration", 30, "Makki", false));
        list.add(new Surah(33, "الأحزاب", "Al-Ahzab", "The Combined Forces", 73, "Madani", false));
        list.add(new Surah(34, "سبأ", "Saba", "Sheba", 54, "Makki", false));
        list.add(new Surah(35, "فاطر", "Fatir", "Originator", 45, "Makki", false));
        list.add(new Surah(36, "يس", "Yasin", "Ya-Sin", 83, "Makki", true));
        list.add(new Surah(37, "الصافات", "As-Saffat", "Those Who Set The Ranks", 182, "Makki", false));
        list.add(new Surah(38, "ص", "Sad", "The Letter Sad", 88, "Makki", false));
        list.add(new Surah(39, "الزمر", "Az-Zumar", "The Troops", 75, "Makki", false));
        list.add(new Surah(40, "غافر", "Ghafir", "The Forgiver", 85, "Makki", false));
        list.add(new Surah(41, "فصلت", "Fussilat", "Explained In Detail", 54, "Makki", false));
        list.add(new Surah(42, "الشورى", "Ash-Shura", "The Consultation", 53, "Makki", false));
        list.add(new Surah(43, "الزخرف", "Az-Zukhruf", "The Ornaments of Gold", 89, "Makki", false));
        list.add(new Surah(44, "الدخان", "Ad-Dukhan", "The Smoke", 59, "Makki", false));
        list.add(new Surah(45, "الجاثية", "Al-Jathiyah", "The Crouching", 37, "Makki", false));
        list.add(new Surah(46, "الأحقاف", "Al-Ahqaf", "The Wind-Curved Sandhills", 35, "Makki", false));
        list.add(new Surah(47, "محمد", "Muhammad", "Muhammad", 38, "Madani", false));
        list.add(new Surah(48, "الفتح", "Al-Fath", "The Victory", 29, "Madani", false));
        list.add(new Surah(49, "الحجرات", "Al-Hujurat", "The Dwellings", 18, "Madani", false));
        list.add(new Surah(50, "ق", "Qaf", "The Letter Qaf", 45, "Makki", false));
        list.add(new Surah(51, "الذاريات", "Adh-Dhariyat", "The Winnowing Winds", 60, "Makki", false));
        list.add(new Surah(52, "الطور", "At-Tur", "The Mount", 49, "Makki", false));
        list.add(new Surah(53, "النجم", "An-Najm", "The Star", 62, "Makki", false));
        list.add(new Surah(54, "القمر", "Al-Qamar", "The Moon", 55, "Makki", false));
        list.add(new Surah(55, "الرحمن", "Ar-Rahman", "The Beneficent", 78, "Madani", false));
        list.add(new Surah(56, "الواقعة", "Al-Waqi'ah", "The Inevitable", 96, "Makki", false));
        list.add(new Surah(57, "الحديد", "Al-Hadid", "The Iron", 29, "Madani", false));
        list.add(new Surah(58, "المجادلة", "Al-Mujadila", "The Pleading Woman", 22, "Madani", false));
        list.add(new Surah(59, "الحشر", "Al-Hashr", "The Exile", 24, "Madani", false));
        list.add(new Surah(60, "الممتحنة", "Al-Mumtahanah", "She That Is To Be Examined", 13, "Madani", false));
        list.add(new Surah(61, "الصف", "As-Saff", "The Ranks", 14, "Madani", false));
        list.add(new Surah(62, "الجمعة", "Al-Jumu'ah", "Friday", 11, "Madani", false));
        list.add(new Surah(63, "المنافقون", "Al-Munafiqun", "The Hypocrites", 11, "Madani", false));
        list.add(new Surah(64, "التغابن", "At-Taghabun", "Mutual Disillusion", 18, "Madani", false));
        list.add(new Surah(65, "الطلاق", "At-Talaq", "The Divorce", 12, "Madani", false));
        list.add(new Surah(66, "التحريم", "At-Tahrim", "The Prohibition", 12, "Madani", false));
        list.add(new Surah(67, "الملك", "Al-Mulk", "The Sovereignty", 30, "Makki", false));
        list.add(new Surah(68, "القلم", "Al-Qalam", "The Pen", 52, "Makki", false));
        list.add(new Surah(69, "الحاقة", "Al-Haqqah", "The Inevitable Reality", 52, "Makki", false));
        list.add(new Surah(70, "المعارج", "Al-Ma'arij", "The Ascending Stairways", 44, "Makki", false));
        list.add(new Surah(71, "نوح", "Nuh", "Noah", 28, "Makki", false));
        list.add(new Surah(72, "الجن", "Al-Jinn", "The Jinn", 28, "Makki", false));
        list.add(new Surah(73, "المزمل", "Al-Muzzammil", "The Enshrouded One", 20, "Makki", false));
        list.add(new Surah(74, "المدثر", "Al-Muddaththir", "The Cloaked One", 56, "Makki", false));
        list.add(new Surah(75, "القيامة", "Al-Qiyamah", "The Resurrection", 40, "Makki", false));
        list.add(new Surah(76, "الإنسان", "Al-Insan", "Man", 31, "Madani", false));
        list.add(new Surah(77, "المرسلات", "Al-Mursalat", "The Emissaries", 50, "Makki", false));
        list.add(new Surah(78, "النبأ", "An-Naba", "The Tidings", 40, "Makki", false));
        list.add(new Surah(79, "النازعات", "An-Nazi'at", "Those Who Drag Forth", 46, "Makki", false));
        list.add(new Surah(80, "عبس", "Abasa", "He Frowned", 42, "Makki", false));
        list.add(new Surah(81, "التكوير", "At-Takwir", "The Overthrowing", 29, "Makki", false));
        list.add(new Surah(82, "الانفطار", "Al-Infitar", "The Cleaving", 19, "Makki", false));
        list.add(new Surah(83, "المطففين", "Al-Mutaffifin", "Defrauding", 36, "Makki", false));
        list.add(new Surah(84, "الانشقاق", "Al-Inshiqaq", "The Splitting Open", 25, "Makki", false));
        list.add(new Surah(85, "البروج", "Al-Buruj", "The Mansions of the Stars", 22, "Makki", false));
        list.add(new Surah(86, "الطارق", "At-Tariq", "The Morning Star", 17, "Makki", false));
        list.add(new Surah(87, "الأعلى", "Al-A'la", "The Most High", 19, "Makki", false));
        list.add(new Surah(88, "الغاشية", "Al-Ghashiyah", "The Overwhelming Event", 26, "Makki", false));
        list.add(new Surah(89, "الفجر", "Al-Fajr", "The Dawn", 30, "Makki", false));
        list.add(new Surah(90, "البلد", "Al-Balad", "The City", 20, "Makki", false));
        list.add(new Surah(91, "الشمس", "Ash-Shams", "The Sun", 15, "Makki", false));
        list.add(new Surah(92, "الليل", "Al-Layl", "The Night", 21, "Makki", false));
        list.add(new Surah(93, "الضحى", "Ad-Duha", "The Morning Hours", 11, "Makki", false));
        list.add(new Surah(94, "الشرح", "Ash-Sharh", "The Relief", 8, "Makki", false));
        list.add(new Surah(95, "التين", "At-Tin", "The Fig", 8, "Makki", false));
        list.add(new Surah(96, "العلق", "Al-Alaq", "The Clot", 19, "Makki", false));
        list.add(new Surah(97, "القدر", "Al-Qadr", "The Night of Decree", 5, "Makki", false));
        list.add(new Surah(98, "البينة", "Al-Bayyinah", "The Clear Proof", 8, "Madani", false));
        list.add(new Surah(99, "الزلزلة", "Az-Zalzalah", "The Earthquake", 8, "Madani", false));
        list.add(new Surah(100, "العاديات", "Al-Adiyat", "The Courser", 11, "Makki", false));
        list.add(new Surah(101, "القارعة", "Al-Qari'ah", "The Calamity", 11, "Makki", false));
        list.add(new Surah(102, "التكاثر", "At-Takathur", "Rivalry in World Increase", 8, "Makki", false));
        list.add(new Surah(103, "العصر", "Al-Asr", "The Declining Day", 3, "Makki", false));
        list.add(new Surah(104, "الهمزة", "Humazah", "The Traducer", 9, "Makki", false));
        list.add(new Surah(105, "الفيل", "Al-Fil", "The Elephant", 5, "Makki", false));
        list.add(new Surah(106, "قريش", "Quraysh", "Quraysh", 4, "Makki", false));
        list.add(new Surah(107, "الماعون", "Al-Ma'un", "Small Kindnesses", 7, "Makki", false));
        list.add(new Surah(108, "الكوثر", "Al-Kawthar", "Abundance", 3, "Makki", false));
        list.add(new Surah(109, "الكافرون", "Al-Kafirun", "The Disbelievers", 6, "Makki", false));
        list.add(new Surah(110, "النصر", "An-Nasr", "The Divine Support", 3, "Madani", false));
        list.add(new Surah(111, "المسد", "Al-Masad", "The Palm Fiber", 5, "Makki", false));
        list.add(new Surah(112, "الإخلاص", "Al-Ikhlas", "Sincerity", 4, "Makki", false));
        list.add(new Surah(113, "الفلق", "Al-Falaq", "The Daybreak", 5, "Makki", false));
        list.add(new Surah(114, "الناس", "An-Nas", "Mankind", 6, "Makki", false));
        return list;
    }

    public static Surah getSurahByNumber(int number) {
        for (Surah s : getSurahs()) {
            if (s.getNumber() == number) return s;
        }
        return null;
    }

    public static List<Dars> getDarsListForSurah(String sheikhId, int surahNumber) {
        List<Dars> darsList = new ArrayList<>();

        if ("suufi".equalsIgnoreCase(sheikhId) || "sufi".equalsIgnoreCase(sheikhId)) {
            // Sheikh Abdul Rashid Ali Sufi - Online Streaming (Hafs / Murattal)
            if (surahNumber >= 1 && surahNumber <= 114) {
                String numStr = String.format(Locale.US, "%03d", surahNumber);
                String fileName = numStr + ".mp3";
                // High-speed CDN stream for Abdul Rashid Ali Sufi
                String streamUrl = "https://server16.mp3quran.net/soufi/" + fileName;
                String archiveId = "AbdurRashid_Sufi_As-Soosi_An_Abi_Amer";

                Surah s = getSurahByNumber(surahNumber);
                String title = s != null ? "Surah " + s.getTransliteration() : "Surah " + surahNumber;
                darsList.add(new Dars(
                        "suufi_" + numStr + "_1",
                        surahNumber,
                        1,
                        title,
                        "Full Surah",
                        streamUrl,
                        archiveId,
                        fileName,
                        true
                ));
            }
        } else if ("dirir".equalsIgnoreCase(sheikhId)) {
            // Sheikh Dirir - Internet Archive Streaming
            String archiveId = "TafsiirQuraankaKariimka-AfSoomali";
            String[][] entries = getDirirDarsTable(surahNumber);
            for (int i = 0; i < entries.length; i++) {
                String[] d = entries[i];
                String id = "dirir_" + String.format(Locale.US, "%03d", surahNumber) + "_" + (i + 1);
                String streamUrl = AudioCatalogResolver.resolvePlayableUrl(archiveId, d[2]);
                darsList.add(new Dars(id, surahNumber, i + 1, d[0], d[1], streamUrl, archiveId, d[2], true));
            }
        } else if ("faruq".equalsIgnoreCase(sheikhId)) {
            String archiveId = "Tafsiirka_Cumar_Faaruuq_1";
            if (surahNumber == 1) {
                String fileName = "001 (1-7).mp3";
                String url = AudioCatalogResolver.resolvePlayableUrl(archiveId, fileName);
                darsList.add(new Dars("faruq_001_1", 1, 1, "Dars 1: Surah Al-Fatiha (Ayah 1-7)", "1-7", url, archiveId, fileName, true));
            } else if (surahNumber == 2) {
                String fileName = "001 (1-7).mp3";
                String url = AudioCatalogResolver.resolvePlayableUrl(archiveId, fileName);
                darsList.add(new Dars("faruq_002_1", 2, 1, "Dars 1: Al-Baqarah (Ayah 1-75)", "1-75", url, archiveId, fileName, true));
                darsList.add(new Dars("faruq_002_2", 2, 2, "Dars 2: Al-Baqarah (Ayah 76-150)", "76-150", url, archiveId, fileName, true));
                darsList.add(new Dars("faruq_002_3", 2, 3, "Dars 3: Al-Baqarah (Ayah 151-286)", "151-286", url, archiveId, fileName, true));
            } else {
                String fileName = "001 (1-7).mp3";
                String url = AudioCatalogResolver.resolvePlayableUrl(archiveId, fileName);
                darsList.add(new Dars("faruq_" + surahNumber + "_1", surahNumber, 1, "Surah Tafsiir", "Full Surah", url, archiveId, fileName, true));
            }
        }

        return darsList;
    }

    /**
     * Returns rows of { title, verseRange, assetFileName } for Dirir's local asset files.
     * Files in assets/dirir/ often span multiple surahs; each surah maps to its relevant file(s).
     */
    private static String[][] getDirirDarsTable(int surahNumber) {
        switch (surahNumber) {
            case 1:  return new String[][]{
                    {"Dars 1: Al-Fatiha (Ayah 1-7)", "1-7", "001alfaatixa.mp3"}};
            case 2:  return new String[][]{
                    {"Dars 1: Al-Baqarah (Ayah 1-43)", "1-43", "002Albaqara1-43.mp3"},
                    {"Dars 2: Al-Baqarah (Ayah 44-74)", "44-74", "003Albaqara44-74.mp3"},
                    {"Dars 3: Al-Baqarah (Ayah 75-105)", "75-105", "004Albaqara75-105.mp3"},
                    {"Dars 4: Al-Baqarah (Ayah 106-141)", "106-141", "005Albaqara106-141.mp3"},
                    {"Dars 5: Al-Baqarah (Ayah 142-176)", "142-176", "006Albaqara-142-176.mp3"},
                    {"Dars 6: Al-Baqarah (Ayah 177-197)", "177-197", "007Albaqara177-197.mp3"},
                    {"Dars 7: Al-Baqarah (Ayah 198-220)", "198-220", "008Albaqara198-220.mp3"},
                    {"Dars 8: Al-Baqarah (Ayah 221-234)", "221-234", "009Albaqara221-234.mp3"},
                    {"Dars 9: Al-Baqarah (Ayah 235-252)", "235-252", "10Albaqara235-252.mp3"},
                    {"Dars 10: Al-Baqarah (Ayah 253-271)", "253-271", "011Albaqa253-271.mp3"},
                    {"Dars 11: Al-Baqarah (Ayah 272-286)", "272-286", "012Albaqara272Alcimraan14.mp3"}};
            case 3:  return new String[][]{
                    {"Dars 1: Ali-Imran (Ayah 15-51)", "15-51", "013AlCimraan15-51.mp3"},
                    {"Dars 2: Ali-Imran (Ayah 52-92)", "52-92", "014AlCimraan52-92.mp3"},
                    {"Dars 3: Ali-Imran (Ayah 93-132)", "93-132", "015AlCimraan93--132.mp3"},
                    {"Dars 4: Ali-Imran (Ayah 133-170)", "133-170", "016Alcimraan133--170.mp3"},
                    {"Dars 5: Ali-Imran (Ayah 171-200)", "171-200", "017AlCimraan171--200.mp3"}};
            case 4:  return new String[][]{
                    {"Dars 1: An-Nisa (Ayah 1-12)", "1-12", "018Anisaa1-12.mp3"},
                    {"Dars 2: An-Nisa (Ayah 13-31)", "13-31", "019Anisaa13-31.mp3"},
                    {"Dars 3: An-Nisa (Ayah 32-57)", "32-57", "020Anisaa32-57.mp3"},
                    {"Dars 4: An-Nisa (Ayah 58-84)", "58-84", "021Alnisaa58-84.mp3"},
                    {"Dars 5: An-Nisa (Ayah 85-113)", "85-113", "022Anisaa85-113.mp3"},
                    {"Dars 6: An-Nisa (Ayah 114-147)", "114-147", "023Anisaa114-147.mp3"},
                    {"Dars 7: An-Nisa (Ayah 148-176)", "148-176", "024Anisaa148-176.mp3"}};
            case 5:  return new String[][]{
                    {"Dars 1: Al-Ma'idah (Ayah 1-13)", "1-13", "025Almaaida1-13.mp3"},
                    {"Dars 2: Al-Ma'idah (Ayah 14-40)", "14-40", "026Almaaida14-40.mp3"},
                    {"Dars 3: Al-Ma'idah (Ayah 41-64)", "41-64", "027Almaaida41-64.mp3"},
                    {"Dars 4: Al-Ma'idah (Ayah 65-96)", "65-96", "028Almaaida65-96.mp3"}};
            case 6:  return new String[][]{
                    {"Dars 1: Al-An'am (Ayah 10-54)", "10-54", "030Ancaam10-54.mp3"},
                    {"Dars 2: Al-An'am (Ayah 55-92)", "55-92", "031Ancaam55-92.mp3"},
                    {"Dars 3: Al-An'am (Ayah 93-126)", "93-126", "032Ancaam93-126.mp3"},
                    {"Dars 4: Al-An'am (Ayah 127-152)", "127-152", "033Ancaam127-152.mp3"}};
            case 7:  return new String[][]{
                    {"Dars 1: Al-A'raf (Ayah 31-64)", "31-64", "035Acraaf31-64.mp3"},
                    {"Dars 2: Al-A'raf (Ayah 65-104)", "65-104", "036Acraaf65-104.mp3"},
                    {"Dars 3: Al-A'raf (Ayah 156-197)", "156-197", "038Acraaf156-197.mp3"}};
            case 8:  return new String[][]{
                    {"Dars 1: Al-Anfal (Ayah 41-72)", "41-72", "040Anfaal41-72.mp3"}};
            case 9:  return new String[][]{
                    {"Dars 1: At-Tawbah (Ayah 31-59)", "31-59", "042Atawba31-59.mp3"},
                    {"Dars 2: At-Tawbah (Ayah 60-92)", "60-92", "043Atawba60-92.mp3"},
                    {"Dars 3: At-Tawbah (Ayah 93-119)", "93-119", "044Atawba93-119.mp3"}};
            case 10: return new String[][]{
                    {"Dars 1: Yunus (Ayah 23-64)", "23-64", "046Yuunas23-64.mp3"},
                    {"Dars 2: Yunus (Ayah 65-109)", "65-109", "047Yuunas65-109.mp3"}};
            case 11: return new String[][]{
                    {"Dars 1: Hud (Ayah 1-40)", "1-40", "048Huud1-40.mp3"},
                    {"Dars 2: Hud (Ayah 41-80)", "41-80", "049Huud41-80.mp3"},
                    {"Dars 3: Hud (Ayah 81-123)", "81-123", "050Huud81-123.mp3"}};
            case 12: return new String[][]{
                    {"Dars 1: Yusuf (Ayah 1-33)", "1-33", "051Yuusuf1-33.mp3"},
                    {"Dars 2: Yusuf (Ayah 34-65)", "34-65", "052Yuusuf34-65.mp3"},
                    {"Dars 3: Yusuf (Ayah 66-102)", "66-102", "053Yuusuf66-102.mp3"}};
            case 13: return new String[][]{
                    {"Dars 1: Ar-Ra'd", "Full", "054Yuusuf103--racad18.mp3"}};
            case 14: return new String[][]{
                    {"Dars 1: Ibrahim (Ayah 7-47)", "7-47", "056Ibraahim-7-47.mp3"}};
            case 15: return new String[][]{
                    {"Dars 1: Al-Hijr", "Full", "057Ibraahiim48-Xajar57.mp3"}};
            case 16: return new String[][]{
                    {"Dars 1: An-Nahl (Ayah 45-79)", "45-79", "059Anaxli45-79.mp3"},
                    {"Dars 2: An-Nahl (Ayah 80-128)", "80-128", "060Anaxli80-128.mp3"}};
            case 17: return new String[][]{
                    {"Dars 1: Al-Isra (Ayah 1-44)", "1-44", "061Israa1-44.mp3"},
                    {"Dars 2: Al-Isra (Ayah 45-93)", "45-93", "062Israa45-93.mp3"}};
            case 18: return new String[][]{
                    {"Dars 1: Al-Kahf (Ayah 31-74)", "31-74", "064Alkahfi31-74.mp3"}};
            case 19: return new String[][]{
                    {"Dars 1: Maryam", "Full", "065Alkahfi75-Maryam15.mp3"}};
            case 20: return new String[][]{
                    {"Dars 1: Taha", "Full", "068Dah71-165.mp3"}};
            case 21: return new String[][]{
                    {"Dars 1: Al-Anbiya (Ayah 1-50)", "1-50", "069AlAnbiyaa1-50.mp3"},
                    {"Dars 2: Al-Anbiya (Ayah 51-112)", "51-112", "070AlAnbiyaa51-112.mp3"}};
            case 22: return new String[][]{
                    {"Dars 1: Al-Hajj (Ayah 1-35)", "1-35", "071AlXaj1-35.mp3"},
                    {"Dars 2: Al-Hajj (Ayah 36-78)", "36-78", "072Alxaj36-78.mp3"}};
            case 23: return new String[][]{
                    {"Dars 1: Al-Mu'minun (Ayah 1-71)", "1-71", "073AlMuuminuun1-71.mp3"}};
            case 24: return new String[][]{
                    {"Dars 1: An-Nur (Ayah 15-42)", "15-42", "075AlNuur15-42.mp3"}};
            case 25: return new String[][]{
                    {"Dars 1: Al-Furqan", "Full", "077AlFuqaan21-73.mp3"}};
            case 26: return new String[][]{
                    {"Dars 1: Ash-Shu'ara (Ayah 89-223)", "89-223", "079Ashucraa89-223.mp3"}};
            case 27: return new String[][]{
                    {"Dars 1: An-Naml (Ayah 45-93)", "45-93", "081Anamli45-93.mp3"}};
            case 28: return new String[][]{
                    {"Dars 1: Al-Qasas (Ayah 1-35)", "1-35", "082Alqasas135.mp3"},
                    {"Dars 2: Al-Qasas (Ayah 36-83)", "36-83", "083Alqasas-3683.mp3"}};
            case 29: return new String[][]{
                    {"Dars 1: Al-Ankabut", "Full", "084Alqasas84-45Cankabuut.mp3"}};
            case 30: return new String[][]{
                    {"Dars 1: Ar-Rum", "Full", "085Cankabuut46Ruum21.mp3"}};
            case 31: return new String[][]{
                    {"Dars 1: Luqman", "Full", "086Ruum22Luqmaan5.mp3"}};
            case 32: return new String[][]{
                    {"Dars 1: As-Sajdah", "Full", "087Luqmaan6Sajad23.mp3"}};
            case 33: return new String[][]{
                    {"Dars 1: Al-Ahzab (Ayah 26-49)", "26-49", "089Axsaab26-49.mp3"},
                    {"Dars 2: Al-Ahzab (Ayah 50-69)", "50-69", "090Axzaab50-69.mp3"}};
            case 34: return new String[][]{
                    {"Dars 1: Saba", "Full", "091Axzaab70Saba33.mp3"}};
            case 35: return new String[][]{
                    {"Dars 1: Fatir", "Full", "092Saba34-faadir27.mp3"}};
            case 36: return new String[][]{
                    {"Dars 1: Yasin", "Full", "093Faadir28Yaasiin41.mp3"}};
            case 37: return new String[][]{
                    {"Dars 1: As-Saffat", "Full", "094Yaasiin42Saafaat62.mp3"}};
            case 38: return new String[][]{
                    {"Dars 1: Sad (Ayah 9-70)", "9-70", "096Saad9-saad70.mp3"}};
            case 39: return new String[][]{
                    {"Dars 1: Az-Zumar", "Full", "097Saad71Zumar37.mp3"}};
            case 40: return new String[][]{
                    {"Dars 1: Ghafir (Ayah 15-67)", "15-67", "099Qaafir15-67.mp3"}};
            case 41: return new String[][]{
                    {"Dars 1: Fussilat", "Full", "100Qaafi68-Fusilat32.mp3"}};
            case 42: return new String[][]{
                    {"Dars 1: Ash-Shura", "Full", "101Fusilat33-Shuura15.mp3"}};
            case 43: return new String[][]{
                    {"Dars 1: Az-Zukhruf (Ayah 8-71)", "8-71", "103Zukhruf8-71.mp3"}};
            case 44: return new String[][]{
                    {"Dars 1: Ad-Dukhan", "Full", "104Zukhruf72Jaasiya18.mp3"}};
            case 45: return new String[][]{
                    {"Dars 1: Al-Jathiyah (Ayah 19+)", "19+", "105Jaasiya19Axqaaf23.mp3"}};
            case 46: return new String[][]{
                    {"Dars 1: Al-Ahqaf (Ayah 24+)", "24+", "106Axqaaf24-Muxamad29.mp3"}};
            case 47: return new String[][]{
                    {"Dars 1: Muhammad (Ayah 30+)", "30+", "107Muxamad30Alfatxi21.mp3"}};
            case 48: return new String[][]{
                    {"Dars 1: Al-Fath", "Full", "108Alfatxi23Alxujuraat12.mp3"}};
            case 49: return new String[][]{
                    {"Dars 1: Al-Hujurat (Ayah 13+)", "13+", "109Alxujuraat13qaaf45.mp3"}};
            case 51: return new String[][]{
                    {"Dars 1: Adh-Dhariyat (Ayah 1+)", "1+", "110Wadaariyaat1Waduur28.mp3"}};
            case 52: return new String[][]{
                    {"Dars 1: At-Tur (Ayah 29+)", "29+", "111Waduur29-Anajmi35.mp3"}};
            case 53: return new String[][]{
                    {"Dars 1: An-Najm (Ayah 36+)", "36+", "112Anajmi36Araxmaan12.mp3"}};
            case 55: return new String[][]{
                    {"Dars 1: Ar-Rahman (Ayah 13+)", "13+", "113Araxmaan13Alwaaqica70.mp3"}};
            case 56: return new String[][]{
                    {"Dars 1: Al-Waqi'ah (Ayah 71+)", "71+", "114Alwaaqica71-Alxadiid27.mp3"}};
            case 57: return new String[][]{
                    {"Dars 1: Al-Hadid (Ayah 28+)", "28+", "115Alxadiid28Almujaadala21.mp3"}};
            case 58: return new String[][]{
                    {"Dars 1: Al-Mujadila (Ayah 22+)", "22+", "116Almujaadala22Alxashri22.mp3"}};
            case 59: return new String[][]{
                    {"Dars 1: Al-Hashr (Ayah 23+)", "23+", "117Alxashar23Alsaf9.mp3"}};
            case 61: return new String[][]{
                    {"Dars 1: As-Saff (Ayah 10+)", "10+", "118Asafa10Almunaafiquun11.mp3"}};
            case 63: return new String[][]{
                    {"Dars 1: Al-Munafiqun", "Full", "118Asafa10Almunaafiquun11.mp3"}};
            case 64: return new String[][]{
                    {"Dars 1: At-Taghabun (Ayah 1+)", "1+", "119Ataqaabun1Adalaaq5.mp3"}};
            case 65: return new String[][]{
                    {"Dars 1: At-Talaq (Ayah 6+)", "6+", "120adalaaq6Ataxriim10.mp3"}};
            case 66: return new String[][]{
                    {"Dars 1: At-Tahrim (Ayah 11+)", "11+", "121Ataxriim11Alqalam16.mp3"}};
            case 68: return new String[][]{
                    {"Dars 1: Al-Qalam (Ayah 17+)", "17+", "122Alqalam17Alxaaqa52.mp3"}};
            case 70: return new String[][]{
                    {"Dars 1: Al-Ma'arij (Ayah 1+)", "1+", "123Almacaarij1Nuux28.mp3"}};
            case 71: return new String[][]{
                    {"Dars 1: Nuh (Ayah 29+)", "29+", "123Almacaarij1Nuux28.mp3"}};
            case 72: return new String[][]{
                    {"Dars 1: Al-Jinn (Ayah 1+)", "1+", "124Aljini1Almuzamil20.mp3"}};
            case 73: return new String[][]{
                    {"Dars 1: Al-Muzzammil", "Full", "124Aljini1Almuzamil20.mp3"}};
            case 74: return new String[][]{
                    {"Dars 1: Al-Muddaththir (Ayah 1+)", "1+", "125Almudasir1Alqiyaama40.mp3"}};
            case 75: return new String[][]{
                    {"Dars 1: Al-Qiyamah", "Full", "125Almudasir1Alqiyaama40.mp3"}};
            case 76: return new String[][]{
                    {"Dars 1: Al-Insan (Ayah 1+)", "1+", "126Alinsaan1Almursalaat50.mp3"}};
            case 77: return new String[][]{
                    {"Dars 1: Al-Mursalat", "Full", "126Alinsaan1Almursalaat50.mp3"}};
            case 78: return new String[][]{
                    {"Dars 1: An-Naba (Ayah 1+)", "1+", "127Camma1Anaasicaat46.mp3"}};
            case 80: return new String[][]{
                    {"Dars 1: Abasa (Ayah 1+)", "1+", "128Cabasa1Almudafifiin6.mp3"}};
            case 83: return new String[][]{
                    {"Dars 1: Al-Mutaffifin (Ayah 7+)", "7+", "129Almudafifiin7Adaariq17.mp3"}};
            case 87: return new String[][]{
                    {"Dars 1: Al-A'la (Ayah 1+)", "1+", "130AlAcla1Albalad20.mp3"}};
            case 91: return new String[][]{
                    {"Dars 1: Ash-Shams (Ayah 1+)", "1+", "131Ashamsi1AlCalaq5.mp3"}};
            case 96: return new String[][]{
                    {"Dars 1: Al-Alaq (Ayah 6+)", "6+", "132Alcalaq6Alcasri3.mp3"}};
            case 103: return new String[][]{
                    {"Dars 1: Al-Asr", "Full", "132Alcalaq6Alcasri3.mp3"}};
            case 104: return new String[][]{
                    {"Dars 1: Al-Humaza - An-Nas", "Full", "133Alhumaza1-Anaas7.mp3"}};
            default:
                return new String[0][0];
        }
    }

    public static final String[] SUUFI_SURAH_FILES = new String[]{
        "abdul-rashid-ali-sufi-001-al-fatiha-2058-880.mp3",
        "abdul-rashid-ali-sufi-002-al-baqara-2244-2796.mp3",
        "abdul-rashid-ali-sufi-003-aal-e-imran-2243-6513.mp3",
        "abdul-rashid-ali-sufi-004-an-nisa-2256-7743.mp3",
        "abdul-rashid-ali-sufi-005-al-maeda-2257-8301.mp3",
        "abdul-rashid-ali-sufi-006-al-anaam-2258-3396.mp3",
        "abdul-rashid-ali-sufi-007-al-araf-2259-618.mp3",
        "abdul-rashid-ali-sufi-008-al-anfal-2260-4325.mp3",
        "abdul-rashid-ali-sufi-009-at-taubah-2261-9228.mp3",
        "abdul-rashid-ali-sufi-010-yunus-2262-297.mp3",
        "abdul-rashid-ali-sufi-011-hud-2263-4019.mp3",
        "abdul-rashid-ali-sufi-012-yusuf-2264-732.mp3",
        "abdul-rashid-ali-sufi-013-ar-rad-2265-4568.mp3",
        "abdul-rashid-ali-sufi-014-ibrahim-2266-8058.mp3",
        "abdul-rashid-ali-sufi-015-al-hijr-2267-9683.mp3",
        "abdul-rashid-ali-sufi-016-an-nahl-2268-6755.mp3",
        "abdul-rashid-ali-sufi-017-al-isra-2269-8261.mp3",
        "abdul-rashid-ali-sufi-018-al-kahf-2270-8841.mp3",
        "abdul-rashid-ali-sufi-019-maryam-2271-4515.mp3",
        "abdul-rashid-ali-sufi-020-taha-2272-1306.mp3",
        "abdul-rashid-ali-sufi-021-al-anbiya-2273-1069.mp3",
        "abdul-rashid-ali-sufi-022-al-hajj-2274-2618.mp3",
        "abdul-rashid-ali-sufi-023-al-mumenoon-2275-5743.mp3",
        "abdul-rashid-ali-sufi-024-an-noor-2245-3124.mp3",
        "abdul-rashid-ali-sufi-025-al-furqan-2246-1524.mp3",
        "abdul-rashid-ali-sufi-026-ash-shuara-2247-2436.mp3",
        "abdul-rashid-ali-sufi-027-an-naml-2248-3865.mp3",
        "abdul-rashid-ali-sufi-028-al-qasas-2249-9388.mp3",
        "abdul-rashid-ali-sufi-029-al-ankaboot-2250-8510.mp3",
        "abdul-rashid-ali-sufi-030-ar-room-2251-2354.mp3",
        "abdul-rashid-ali-sufi-031-luqman-2252-6967.mp3",
        "abdul-rashid-ali-sufi-032-as-sajda-2253-9391.mp3",
        "abdul-rashid-ali-sufi-033-al-ahzab-2254-5151.mp3",
        "abdul-rashid-ali-sufi-034-saba-2255-3479.mp3",
        "abdul-rashid-ali-sufi-035-fatir-2284-7134.mp3",
        "abdul-rashid-ali-sufi-036-ya-seen-2285-3451.mp3",
        "abdul-rashid-ali-sufi-037-as-saaffat-2286-6875.mp3",
        "abdul-rashid-ali-sufi-038-sad-2287-7752.mp3",
        "abdul-rashid-ali-sufi-039-az-zumar-2288-7776.mp3",
        "abdul-rashid-ali-sufi-040-ghafir-2289-6103.mp3",
        "abdul-rashid-ali-sufi-041-fussilat-2290-8050.mp3",
        "abdul-rashid-ali-sufi-042-ash-shura-2291-1795.mp3",
        "abdul-rashid-ali-sufi-043-az-zukhruf-2292-6835.mp3",
        "abdul-rashid-ali-sufi-044-ad-dukhan-2293-2618.mp3",
        "abdul-rashid-ali-sufi-045-al-jathiya-2294-9853.mp3",
        "abdul-rashid-ali-sufi-046-al-ahqaf-2295-6518.mp3",
        "abdul-rashid-ali-sufi-047-muhammad-2296-9373.mp3",
        "abdul-rashid-ali-sufi-048-al-fath-2297-8114.mp3",
        "abdul-rashid-ali-sufi-049-al-hujraat-2298-5359.mp3",
        "abdul-rashid-ali-sufi-050-qaf-2299-3888.mp3",
        "abdul-rashid-ali-sufi-051-adh-dhariyat-2300-9420.mp3",
        "abdul-rashid-ali-sufi-052-at-tur-2301-6428.mp3",
        "abdul-rashid-ali-sufi-053-an-najm-2302-6506.mp3",
        "abdul-rashid-ali-sufi-054-al-qamar-2303-5162.mp3",
        "abdul-rashid-ali-sufi-055-al-rahman-2304-9552.mp3",
        "abdul-rashid-ali-sufi-056-al-waqia-2305-8030.mp3",
        "abdul-rashid-ali-sufi-057-al-hadid-2306-7598.mp3",
        "abdul-rashid-ali-sufi-058-al-mujadala-2307-3417.mp3",
        "abdul-rashid-ali-sufi-059-al-hashr-2308-7417.mp3",
        "abdul-rashid-ali-sufi-060-al-mumtahina-2309-6108.mp3",
        "abdul-rashid-ali-sufi-061-as-saff-2310-5771.mp3",
        "abdul-rashid-ali-sufi-062-al-jumua-2311-4384.mp3",
        "abdul-rashid-ali-sufi-063-al-munafiqoon-2312-5499.mp3",
        "abdul-rashid-ali-sufi-064-at-taghabun-2313-922.mp3",
        "abdul-rashid-ali-sufi-065-at-talaq-2314-7863.mp3",
        "abdul-rashid-ali-sufi-066-at-tahrim-2276-2632.mp3",
        "abdul-rashid-ali-sufi-067-al-mulk-2277-4373.mp3",
        "abdul-rashid-ali-sufi-068-al-qalam-2278-4738.mp3",
        "abdul-rashid-ali-sufi-069-al-haaqqa-2279-384.mp3",
        "abdul-rashid-ali-sufi-070-al-maarij-31294-8890.mp3",
        "abdul-rashid-ali-sufi-071-nooh-2281-840.mp3",
        "abdul-rashid-ali-sufi-072-al-jinn-31296-2428.mp3",
        "abdul-rashid-ali-sufi-073-al-muzzammil-2283-3945.mp3",
        "abdul-rashid-ali-sufi-074-al-muddathir-2349-7676.mp3",
        "abdul-rashid-ali-sufi-075-al-qiyamah-31299-2198.mp3",
        "abdul-rashid-ali-sufi-076-al-insan-31300-6680.mp3",
        "abdul-rashid-ali-sufi-077-al-mursalat-2352-4194.mp3",
        "abdul-rashid-ali-sufi-078-an-naba-2353-424.mp3",
        "abdul-rashid-ali-sufi-079-an-nazi-at-31303-7514.mp3",
        "abdul-rashid-ali-sufi-080-abasa-31304-5424.mp3",
        "abdul-rashid-ali-sufi-081-at-takwir-31305-3290.mp3",
        "abdul-rashid-ali-sufi-082-al-infitar-31306-3120.mp3",
        "abdul-rashid-ali-sufi-083-al-moutaffifin-31307-7534.mp3",
        "abdul-rashid-ali-sufi-084-al-inshiqaq-31308-5488.mp3",
        "abdul-rashid-ali-sufi-085-al-bourouj-31309-3022.mp3",
        "abdul-rashid-ali-sufi-086-at-tariq-31310-7974.mp3",
        "abdul-rashid-ali-sufi-087-al-ala-31311-8179.mp3",
        "abdul-rashid-ali-sufi-088-al-ghashiya-2322-4091.mp3",
        "abdul-rashid-ali-sufi-089-al-fajr-31313-7365.mp3",
        "abdul-rashid-ali-sufi-090-al-balad-31314-5630.mp3",
        "abdul-rashid-ali-sufi-091-ash-shams-31315-6739.mp3",
        "abdul-rashid-ali-sufi-092-al-layl-31316-2471.mp3",
        "abdul-rashid-ali-sufi-093-ad-dhuha-31317-6143.mp3",
        "abdul-rashid-ali-sufi-094-ash-sharh-31318-2169.mp3",
        "abdul-rashid-ali-sufi-095-at-tin-31319-9873.mp3",
        "abdul-rashid-ali-sufi-096-al-alaq-31320-4008.mp3",
        "abdul-rashid-ali-sufi-097-al-qadr-31321-9337.mp3",
        "abdul-rashid-ali-sufi-098-al-bayyinah-31322-108.mp3",
        "abdul-rashid-ali-sufi-099-al-zalzalah-31323-3927.mp3",
        "abdul-rashid-ali-sufi-100-al-adiyat-31324-9628.mp3",
        "abdul-rashid-ali-sufi-101-al-qariah-31325-8998.mp3",
        "abdul-rashid-ali-sufi-102-at-takathour-31326-6628.mp3",
        "abdul-rashid-ali-sufi-103-al-asr-31327-2056.mp3",
        "abdul-rashid-ali-sufi-104-al-humaza-31328-7730.mp3",
        "abdul-rashid-ali-sufi-105-al-fil-31329-962.mp3",
        "abdul-rashid-ali-sufi-106-quraish-31330-4254.mp3",
        "abdul-rashid-ali-sufi-107-al-maun-31331-4410.mp3",
        "abdul-rashid-ali-sufi-108-al-kawtar-31332-5004.mp3",
        "abdul-rashid-ali-sufi-109-al-kafiroune-31333-3092.mp3",
        "abdul-rashid-ali-sufi-110-an-nasr-31334-1924.mp3",
        "abdul-rashid-ali-sufi-111-al-masad-31335-428.mp3",
        "abdul-rashid-ali-sufi-112-al-ikhlas-31336-6382.mp3",
        "abdul-rashid-ali-sufi-113-al-falaq-31337-5044.mp3",
        "abdul-rashid-ali-sufi-114-an-nas-31338-7962.mp3",
    };

    public static List<QuranVerse> getVersesForSurah(Context context, int surahNumber) {
        List<QuranVerse> verses = new ArrayList<>();

        if (context != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                    context.getAssets().open("quran-uthmani-min.txt"), StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty()) continue;
                    String[] parts = line.split("\\|");
                    if (parts.length >= 3) {
                        int sNum = Integer.parseInt(parts[0]);
                        if (sNum == surahNumber) {
                            int vNum = Integer.parseInt(parts[1]);
                            String text = parts[2];
                            verses.add(new QuranVerse(sNum, vNum, text, text));
                        } else if (sNum > surahNumber) {
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return verses;
    }
}
