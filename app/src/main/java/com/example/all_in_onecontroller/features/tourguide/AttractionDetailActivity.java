package com.example.all_in_onecontroller.features.tourguide;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.all_in_onecontroller.R;
import com.example.all_in_onecontroller.features.tourguide.model.Attraction;
import com.google.android.material.button.MaterialButton;

import java.util.Locale;

/**
 * Screen 3: Attraction Detail Screen.
 * Displays a large hero image, location, detailed description, and an
 * "Open in Google Maps" button that fires an implicit Intent using coordinates.
 */
public class AttractionDetailActivity extends AppCompatActivity {

    public static final String EXTRA_ATTRACTION = "extra_attraction";

    private ImageButton btnBack;
    private ImageButton btnShare;
    private ImageView ivAttractionHero;
    private TextView tvDetailCategoryBadge;
    private TextView tvDetailAttractionTitle;
    private TextView tvDetailLocationBanner;
    private TextView tvDetailType;
    private TextView tvDetailCoordinates;
    private TextView tvDetailFullDescription;
    private MaterialButton btnOpenGoogleMaps;

    private Attraction currentAttraction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_attraction_detail);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.attractionDetailMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Retrieve Serializable Attraction object from Intent
        if (getIntent() != null && getIntent().hasExtra(EXTRA_ATTRACTION)) {
            currentAttraction = (Attraction) getIntent().getSerializableExtra(EXTRA_ATTRACTION);
        }

        initViews();
        displayAttractionDetails();
        setupClickListeners();
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBackDetail);
        btnShare = findViewById(R.id.btnShareAttraction);
        ivAttractionHero = findViewById(R.id.ivAttractionHero);
        tvDetailCategoryBadge = findViewById(R.id.tvDetailCategoryBadge);
        tvDetailAttractionTitle = findViewById(R.id.tvDetailAttractionTitle);
        tvDetailLocationBanner = findViewById(R.id.tvDetailLocationBanner);
        tvDetailType = findViewById(R.id.tvDetailType);
        tvDetailCoordinates = findViewById(R.id.tvDetailCoordinates);
        tvDetailFullDescription = findViewById(R.id.tvDetailFullDescription);
        btnOpenGoogleMaps = findViewById(R.id.btnOpenGoogleMaps);
    }

    private void displayAttractionDetails() {
        if (currentAttraction == null) {
            Toast.makeText(this, "Attraction details not available", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        tvDetailCategoryBadge.setText(currentAttraction.getCategory());
        tvDetailAttractionTitle.setText(currentAttraction.getName());
        tvDetailLocationBanner.setText(currentAttraction.getLocation());
        tvDetailType.setText(currentAttraction.getCategory());

        String coords = String.format(Locale.US, "%.4f° N, %.4f° E",
                currentAttraction.getLatitude(), currentAttraction.getLongitude());
        tvDetailCoordinates.setText(coords);

        tvDetailFullDescription.setText(currentAttraction.getDescription());

        int imgRes = currentAttraction.getImageResource();
        if (imgRes != 0) {
            ivAttractionHero.setImageResource(imgRes);
        } else {
            ivAttractionHero.setImageResource(R.drawable.liido_beach);
        }
    }

    private void setupClickListeners() {
        btnBack.setOnClickListener(v -> finish());

        // Feature: Open in Google Maps via implicit Intent (Intent.ACTION_VIEW)
        btnOpenGoogleMaps.setOnClickListener(v -> openInGoogleMaps());

        btnShare.setOnClickListener(v -> shareAttractionDetails());
    }

    private void openInGoogleMaps() {
        if (currentAttraction == null) return;

        double lat = currentAttraction.getLatitude();
        double lng = currentAttraction.getLongitude();
        String label = Uri.encode(currentAttraction.getName());

        // Standard geo URI for Google Maps
        Uri gmmIntentUri = Uri.parse("geo:" + lat + "," + lng + "?q=" + lat + "," + lng + "(" + label + ")");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");

        try {
            startActivity(mapIntent);
        } catch (Exception e) {
            // Fallback to web browser Google Maps if Maps app is not installed
            Uri webUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=" + lat + "," + lng);
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webUri);
            startActivity(webIntent);
        }
    }

    private void shareAttractionDetails() {
        if (currentAttraction == null) return;

        String shareText = "Somalia Tour Guide: " + currentAttraction.getName() + "\n\n"
                + "• Category: " + currentAttraction.getCategory() + "\n"
                + "• Location: " + currentAttraction.getLocation() + "\n"
                + "• Coordinates: " + currentAttraction.getLatitude() + ", " + currentAttraction.getLongitude() + "\n\n"
                + currentAttraction.getDescription() + "\n\n"
                + "Explore more on Google Maps: https://www.google.com/maps/search/?api=1&query="
                + currentAttraction.getLatitude() + "," + currentAttraction.getLongitude();

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, currentAttraction.getName() + " - Somalia Tour Guide");
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);

        try {
            startActivity(Intent.createChooser(shareIntent, "Share " + currentAttraction.getName()));
        } catch (Exception e) {
            Toast.makeText(this, "Unable to share at this moment", Toast.LENGTH_SHORT).show();
        }
    }
}

