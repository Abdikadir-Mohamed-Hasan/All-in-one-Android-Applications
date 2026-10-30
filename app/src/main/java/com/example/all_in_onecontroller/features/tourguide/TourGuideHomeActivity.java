package com.example.all_in_onecontroller.features.tourguide;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.all_in_onecontroller.R;
import com.example.all_in_onecontroller.features.tourguide.data.TourData;
import com.google.android.material.card.MaterialCardView;

/**
 * Screen 1: Somalia Tour Guide Home / Dashboard Screen.
 * Displays an authentic hero header and interactive category cards.
 * Clicking any category navigates to AttractionsListActivity passing the selected category name.
 */
public class TourGuideHomeActivity extends AppCompatActivity {

    public static final String EXTRA_CATEGORY_NAME = "extra_category_name";

    private ImageButton btnBack;
    private MaterialCardView cardCategoryBeaches;
    private MaterialCardView cardCategoryHistorical;
    private MaterialCardView cardCategoryNature;
    private MaterialCardView cardCategoryCities;

    private long lastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tour_guide_home);

        // Edge-to-edge window insets padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tourGuideHomeMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        setupClickListeners();
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBackTourGuide);
        cardCategoryBeaches = findViewById(R.id.cardCategoryBeaches);
        cardCategoryHistorical = findViewById(R.id.cardCategoryHistorical);
        cardCategoryNature = findViewById(R.id.cardCategoryNature);
        cardCategoryCities = findViewById(R.id.cardCategoryCities);
    }

    private boolean isFastDoubleClick() {
        if (SystemClock.elapsedRealtime() - lastClickTime < 600) {
            return true;
        }
        lastClickTime = SystemClock.elapsedRealtime();
        return false;
    }

    private void setupClickListeners() {
        btnBack.setOnClickListener(v -> finish());

        // Category 1: Beautiful Beaches
        cardCategoryBeaches.setOnClickListener(v -> {
            if (isFastDoubleClick()) return;
            openAttractionsList(TourData.CATEGORY_BEACHES);
        });

        // Category 2: Historical Sites
        cardCategoryHistorical.setOnClickListener(v -> {
            if (isFastDoubleClick()) return;
            openAttractionsList(TourData.CATEGORY_HISTORICAL);
        });

        // Category 3: Nature & Mountains
        cardCategoryNature.setOnClickListener(v -> {
            if (isFastDoubleClick()) return;
            openAttractionsList(TourData.CATEGORY_NATURE);
        });

        // Category 4: Major Cities
        cardCategoryCities.setOnClickListener(v -> {
            if (isFastDoubleClick()) return;
            openAttractionsList(TourData.CATEGORY_CITIES);
        });
    }

    private void openAttractionsList(String categoryName) {
        Intent intent = new Intent(TourGuideHomeActivity.this, AttractionsListActivity.class);
        intent.putExtra(EXTRA_CATEGORY_NAME, categoryName);
        startActivity(intent);
    }
}

