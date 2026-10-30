package com.example.all_in_onecontroller.features.barodalkaga;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.all_in_onecontroller.R;
import com.example.all_in_onecontroller.features.barodalkaga.adapter.FeaturedCapitalAdapter;
import com.example.all_in_onecontroller.features.barodalkaga.data.RegionDatabase;
import com.example.all_in_onecontroller.features.barodalkaga.model.Region;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

/**
 * SCREEN 1: Modern Interactive Home Screen for "Baro Dalkaga"
 * Highlights the map of Somalia, quick stats, featured capital cities carousel,
 * and seamless navigation to the Regions List and Detail screens.
 */
public class BaroDalkagaActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private MaterialCardView cardMapContainer;
    private ImageView ivSomaliaMap;
    private RecyclerView rvFeaturedCapitals;
    private MaterialButton btnLearnMore;

    private FeaturedCapitalAdapter featuredAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_baro_dalkaga);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.baroMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        setupFeaturedCapitals();
        setupListeners();
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        cardMapContainer = findViewById(R.id.cardMapContainer);
        ivSomaliaMap = findViewById(R.id.ivSomaliaMap);
        rvFeaturedCapitals = findViewById(R.id.rvFeaturedCapitals);
        btnLearnMore = findViewById(R.id.btnLearnMore);
    }

    private void setupFeaturedCapitals() {
        featuredAdapter = new FeaturedCapitalAdapter(this, region -> {
            Intent intent = new Intent(BaroDalkagaActivity.this, RegionDetailActivity.class);
            intent.putExtra(RegionsListActivity.EXTRA_REGION_NAME, region.getName());
            startActivity(intent);
        });

        rvFeaturedCapitals.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvFeaturedCapitals.setAdapter(featuredAdapter);

        // Curate iconic featured capitals from different zones
        List<Region> all = RegionDatabase.getAllRegions();
        List<Region> featured = new ArrayList<>();
        for (Region r : all) {
            String id = r.getId();
            if (id.equals("banaadir") || id.equals("waqooyi_galbeed") || id.equals("bari")
                    || id.equals("lower_juba") || id.equals("bay") || id.equals("awdal")
                    || id.equals("nugaal") || id.equals("togdheer") || id.equals("sanaag")) {
                featured.add(r);
            }
        }
        featuredAdapter.setRegions(featured);
    }

    private void setupListeners() {
        btnBack.setOnClickListener(v -> finish());

        // Navigates to Screen 2: Regions List Screen
        btnLearnMore.setOnClickListener(v -> {
            Intent intent = new Intent(BaroDalkagaActivity.this, RegionsListActivity.class);
            startActivity(intent);
        });

        // Tapping the map also directly navigates to the list
        if (cardMapContainer != null) {
            cardMapContainer.setOnClickListener(v -> {
                Intent intent = new Intent(BaroDalkagaActivity.this, RegionsListActivity.class);
                startActivity(intent);
            });
        }
    }
}
