package com.example.all_in_onecontroller.features.tourguide;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import com.example.all_in_onecontroller.features.tourguide.adapter.AttractionAdapter;
import com.example.all_in_onecontroller.features.tourguide.data.TourData;
import com.example.all_in_onecontroller.features.tourguide.model.Attraction;

import java.util.ArrayList;

/**
 * Screen 2: Tourist Attractions List Screen.
 * Displays attractions filtered by the selected category using a RecyclerView.
 * Explicitly configures a LinearLayoutManager and passes the selected Attraction to AttractionDetailActivity.
 */
public class AttractionsListActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private TextView tvCategoryHeaderTitle;
    private TextView tvCategoryHeaderSubtitle;
    private ImageView ivCategoryBannerIcon;
    private TextView tvCategoryBannerDesc;
    private LinearLayout llEmptyAttractions;
    private RecyclerView rvAttractions;

    private AttractionAdapter adapter;
    private String selectedCategory = TourData.CATEGORY_BEACHES;
    private long lastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_attractions_list);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.attractionsListMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (getIntent() != null && getIntent().hasExtra(TourGuideHomeActivity.EXTRA_CATEGORY_NAME)) {
            selectedCategory = getIntent().getStringExtra(TourGuideHomeActivity.EXTRA_CATEGORY_NAME);
        }

        initViews();
        setupRecyclerView();
        loadAttractions();
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBackAttractionsList);
        tvCategoryHeaderTitle = findViewById(R.id.tvCategoryHeaderTitle);
        tvCategoryHeaderSubtitle = findViewById(R.id.tvCategoryHeaderSubtitle);
        ivCategoryBannerIcon = findViewById(R.id.ivCategoryBannerIcon);
        tvCategoryBannerDesc = findViewById(R.id.tvCategoryBannerDesc);
        llEmptyAttractions = findViewById(R.id.llEmptyAttractions);
        rvAttractions = findViewById(R.id.rvAttractions);

        btnBack.setOnClickListener(v -> finish());
    }

    private void setupRecyclerView() {
        // Explicitly set LinearLayoutManager for the RecyclerView as required
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvAttractions.setLayoutManager(layoutManager);
        rvAttractions.setHasFixedSize(true);

        adapter = new AttractionAdapter(this, new ArrayList<>(), attraction -> {
            if (isFastDoubleClick()) return;
            // Pass the entire Attraction object via Intent to the Detail Screen
            Intent intent = new Intent(AttractionsListActivity.this, AttractionDetailActivity.class);
            intent.putExtra(AttractionDetailActivity.EXTRA_ATTRACTION, attraction);
            startActivity(intent);
        });

        rvAttractions.setAdapter(adapter);
    }

    private void loadAttractions() {
        tvCategoryHeaderTitle.setText(selectedCategory);

        // Customize banner icon & description based on selected category
        if (TourData.CATEGORY_BEACHES.equalsIgnoreCase(selectedCategory)) {
            ivCategoryBannerIcon.setImageResource(R.drawable.ic_category_beach);
            ivCategoryBannerIcon.setColorFilter(0xFF0284C7);
            tvCategoryBannerDesc.setText("Pristine white sands along the Indian Ocean & Gulf of Aden.");
        } else if (TourData.CATEGORY_HISTORICAL.equalsIgnoreCase(selectedCategory)) {
            ivCategoryBannerIcon.setImageResource(R.drawable.ic_category_history);
            ivCategoryBannerIcon.setColorFilter(0xFFD97706);
            tvCategoryBannerDesc.setText("Ancient cave art, Dervish fortresses, and medieval trade ports.");
        } else if (TourData.CATEGORY_NATURE.equalsIgnoreCase(selectedCategory)) {
            ivCategoryBannerIcon.setImageResource(R.drawable.ic_category_nature);
            ivCategoryBannerIcon.setColorFilter(0xFF059669);
            tvCategoryBannerDesc.setText("Misty mountain escarpments, waterfalls, and endemic cloud forests.");
        } else {
            ivCategoryBannerIcon.setImageResource(R.drawable.ic_category_city);
            ivCategoryBannerIcon.setColorFilter(0xFF4F46E5);
            tvCategoryBannerDesc.setText("Vibrant cultural, commercial, and educational urban centers.");
        }

        ArrayList<Attraction> list = TourData.getAttractionsByCategory(selectedCategory);

        if (list.isEmpty()) {
            llEmptyAttractions.setVisibility(View.VISIBLE);
            rvAttractions.setVisibility(View.GONE);
            tvCategoryHeaderSubtitle.setText("0 Attractions Found");
        } else {
            llEmptyAttractions.setVisibility(View.GONE);
            rvAttractions.setVisibility(View.VISIBLE);
            tvCategoryHeaderSubtitle.setText(list.size() + " Tourist Attractions Found");
            adapter.updateList(list);
        }
    }

    private boolean isFastDoubleClick() {
        if (SystemClock.elapsedRealtime() - lastClickTime < 600) {
            return true;
        }
        lastClickTime = SystemClock.elapsedRealtime();
        return false;
    }
}
