package com.example.all_in_onecontroller.features.barodalkaga;

import android.content.Intent;
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
import com.example.all_in_onecontroller.features.barodalkaga.data.RegionDatabase;
import com.example.all_in_onecontroller.features.barodalkaga.model.Region;
import com.google.android.material.button.MaterialButton;

/**
 * SCREEN 3: Interactive Region Detail Screen with Capital City Hero Image.
 * Displays immersive imagery, regional metrics, cultural highlights, and interactive share actions.
 */
public class RegionDetailActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private ImageButton btnShare;
    private ImageView ivRegionHero;
    private TextView tvDetailZoneBadge;
    private TextView tvDetailHeroYear;
    private TextView tvDetailRegionName;
    private TextView tvDetailCapitalBanner;
    private TextView tvDetailCapital;
    private TextView tvDetailEstablished;
    private TextView tvDetailFamousFor;
    private TextView tvDetailDescription;
    private MaterialButton btnShareBottom;

    private String regionName = "Awdal";
    private Region currentRegion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_region_detail);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.regionDetailMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. Receive Intent extras passed from Screen 1 or Screen 2
        if (getIntent() != null && getIntent().hasExtra(RegionsListActivity.EXTRA_REGION_NAME)) {
            String name = getIntent().getStringExtra(RegionsListActivity.EXTRA_REGION_NAME);
            if (name != null) {
                regionName = name;
            }
        }

        initViews();
        displayRegionDetails();
        setupListeners();
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        btnShare = findViewById(R.id.btnShare);
        ivRegionHero = findViewById(R.id.ivRegionHero);
        tvDetailZoneBadge = findViewById(R.id.tvDetailZoneBadge);
        tvDetailHeroYear = findViewById(R.id.tvDetailHeroYear);
        tvDetailRegionName = findViewById(R.id.tvDetailRegionName);
        tvDetailCapitalBanner = findViewById(R.id.tvDetailCapitalBanner);
        tvDetailCapital = findViewById(R.id.tvDetailCapital);
        tvDetailEstablished = findViewById(R.id.tvDetailEstablished);
        tvDetailFamousFor = findViewById(R.id.tvDetailFamousFor);
        tvDetailDescription = findViewById(R.id.tvDetailDescription);
        btnShareBottom = findViewById(R.id.btnShareBottom);
    }

    private void setupListeners() {
        btnBack.setOnClickListener(v -> finish());
        btnShare.setOnClickListener(v -> shareRegionFacts());
        if (btnShareBottom != null) {
            btnShareBottom.setOnClickListener(v -> shareRegionFacts());
        }
    }

    private void displayRegionDetails() {
        // Fetch the region data model from our repository
        currentRegion = RegionDatabase.getRegionByName(regionName);

        if (currentRegion != null) {
            tvDetailRegionName.setText(currentRegion.getName());
            tvDetailCapitalBanner.setText("Capital: " + currentRegion.getCapitalCity());
            tvDetailZoneBadge.setText(currentRegion.getZone() + " Zone");
            tvDetailHeroYear.setText("Est. " + currentRegion.getEstablishedYear());

            tvDetailCapital.setText(currentRegion.getCapitalCity());
            tvDetailEstablished.setText(currentRegion.getEstablishedYear());
            tvDetailFamousFor.setText(currentRegion.getFamousFor());
            tvDetailDescription.setText(currentRegion.getDescription());

            // Load Capital City Hero Image
            int heroRes = currentRegion.getImageResId(this);
            if (heroRes != 0) {
                ivRegionHero.setImageResource(heroRes);
            } else {
                ivRegionHero.setImageResource(R.drawable.bg_region_hero_default);
            }
        }
    }

    private void shareRegionFacts() {
        if (currentRegion == null) return;

        String shareText = "Explore Somalia - " + currentRegion.getName() + " Region\n\n"
                + "• Capital City: " + currentRegion.getCapitalCity() + "\n"
                + "• Established: " + currentRegion.getEstablishedYear() + "\n"
                + "• Geographic Zone: " + currentRegion.getZone() + " Somalia\n"
                + "• Famous For: " + currentRegion.getFamousFor() + "\n\n"
                + currentRegion.getDescription();

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, currentRegion.getName() + " - Somalia");
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);

        try {
            startActivity(Intent.createChooser(shareIntent, "Share " + currentRegion.getName() + " Facts"));
        } catch (Exception e) {
            Toast.makeText(this, "Unable to share at this moment", Toast.LENGTH_SHORT).show();
        }
    }
}
