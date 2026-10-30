package com.example.all_in_onecontroller.features.quran.data;

import android.net.Uri;

public class AudioCatalogResolver {

    public static String resolvePlayableUrl(String archiveIdentifier, String rawFileName) {
        if (archiveIdentifier == null || rawFileName == null) {
            return null;
        }

        // Properly encode the filename path segment
        String encodedFileName = Uri.encode(rawFileName)
                .replace("(", "%28")
                .replace(")", "%29")
                .replace("+", "%20");

        return "https://archive.org/download/" + archiveIdentifier + "/" + encodedFileName;
    }
}