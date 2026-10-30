package com.example.all_in_onecontroller.features.barodalkaga;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.all_in_onecontroller.R;
import com.example.all_in_onecontroller.features.barodalkaga.adapter.RegionAdapter;
import com.example.all_in_onecontroller.features.barodalkaga.data.RegionDatabase;
import com.example.all_in_onecontroller.features.barodalkaga.model.Region;

import java.util.ArrayList;
import java.util.List;

/**
 * SCREEN 2: Regions List Screen with Real-Time Search & Interactive Zone Filtering.
 * Tapping a region navigates to Screen 3 (RegionDetailActivity).
 */
public class RegionsListActivity extends AppCompatActivity implements RegionAdapter.OnRegionClickListener {

    public static final String EXTRA_REGION_ID = "extra_region_id";
    public static final String EXTRA_REGION_NAME = "extra_region_name";

    private ImageButton btnBack;
    private EditText etSearchRegion;
    private ImageButton btnClearSearch;
    private TextView tvRegionsCount;
    private RecyclerView rvRegions;
    private LinearLayout layoutEmptyState;

    private TextView chipFilterAll;
    private TextView chipFilterNorth;
    private TextView chipFilterCentral;
    private TextView chipFilterSouth;

    private RegionAdapter regionAdapter;
    private List<Region> allRegions = new ArrayList<>();
    private String selectedZone = "All";
    private String currentSearchQuery = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_regions_list);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.regionsListMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        setupRecyclerView();
        loadData();
        setupSearchFilter();
        setupZoneChips();
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        etSearchRegion = findViewById(R.id.etSearchRegion);
        btnClearSearch = findViewById(R.id.btnClearSearch);
        tvRegionsCount = findViewById(R.id.tvRegionsCount);
        rvRegions = findViewById(R.id.rvRegions);
        layoutEmptyState = findViewById(R.id.layoutEmptyState);

        chipFilterAll = findViewById(R.id.chipFilterAll);
        chipFilterNorth = findViewById(R.id.chipFilterNorth);
        chipFilterCentral = findViewById(R.id.chipFilterCentral);
        chipFilterSouth = findViewById(R.id.chipFilterSouth);

        btnBack.setOnClickListener(v -> finish());
        btnClearSearch.setOnClickListener(v -> etSearchRegion.setText(""));
    }

    private void setupRecyclerView() {
        regionAdapter = new RegionAdapter(this, this);
        rvRegions.setLayoutManager(new LinearLayoutManager(this));
        rvRegions.setAdapter(regionAdapter);
    }

    private void loadData() {
        allRegions = RegionDatabase.getAllRegions();
        applyFilters();
    }

    private void setupSearchFilter() {
        etSearchRegion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                currentSearchQuery = s.toString();
                btnClearSearch.setVisibility(currentSearchQuery.isEmpty() ? View.GONE : View.VISIBLE);
                applyFilters();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void setupZoneChips() {
        chipFilterAll.setOnClickListener(v -> setZoneFilter("All"));
        chipFilterNorth.setOnClickListener(v -> setZoneFilter("Northern"));
        chipFilterCentral.setOnClickListener(v -> setZoneFilter("Central"));
        chipFilterSouth.setOnClickListener(v -> setZoneFilter("Southern"));
    }

    private void setZoneFilter(String zone) {
        selectedZone = zone;
        updateChipStyles();
        applyFilters();
    }

    private void updateChipStyles() {
        updateSingleChip(chipFilterAll, selectedZone.equals("All"));
        updateSingleChip(chipFilterNorth, selectedZone.equals("Northern"));
        updateSingleChip(chipFilterCentral, selectedZone.equals("Central"));
        updateSingleChip(chipFilterSouth, selectedZone.equals("Southern"));
    }

    private void updateSingleChip(TextView chip, boolean isActive) {
        if (isActive) {
            chip.setBackgroundResource(R.drawable.bg_tab_pill_active);
            chip.setTextColor(0xFF059669);
        } else {
            chip.setBackgroundResource(R.drawable.bg_tab_pill_inactive);
            chip.setTextColor(0xFF6B7280);
        }
    }

    private void applyFilters() {
        List<Region> filtered = new ArrayList<>();
        String query = currentSearchQuery.toLowerCase().trim();

        for (Region r : allRegions) {
            boolean matchesZone = selectedZone.equals("All") || r.getZone().equalsIgnoreCase(selectedZone);
            boolean matchesQuery = query.isEmpty() ||
                    r.getName().toLowerCase().contains(query) ||
                    r.getCapitalCity().toLowerCase().contains(query);

            if (matchesZone && matchesQuery) {
                filtered.add(r);
            }
        }

        regionAdapter.setRegions(filtered);
        tvRegionsCount.setText(filtered.size() + " of 18 Regions");

        if (filtered.isEmpty()) {
            layoutEmptyState.setVisibility(View.VISIBLE);
            rvRegions.setVisibility(View.GONE);
        } else {
            layoutEmptyState.setVisibility(View.GONE);
            rvRegions.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onRegionClick(Region region) {
        Intent intent = new Intent(RegionsListActivity.this, RegionDetailActivity.class);
        intent.putExtra(EXTRA_REGION_ID, region.getId());
        intent.putExtra(EXTRA_REGION_NAME, region.getName());
        startActivity(intent);
    }
}
