package com.example.all_in_onecontroller.features.quran.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.all_in_onecontroller.R;
import com.google.android.material.card.MaterialCardView;

public class SheikhSelectionActivity extends AppCompatActivity {

    public static final String EXTRA_SHEIKH_ID = "extra_sheikh_id";
    public static final String EXTRA_SHEIKH_NAME = "extra_sheikh_name";
    public static final String EXTRA_CATEGORY = "extra_category";

    public static final String CATEGORY_RECITATION = "recitation";
    public static final String CATEGORY_TRANSLATION = "translation";

    private ImageButton btnBack;
    private TextView tvHeaderTitle;
    private TextView tvHeaderSubtitle;

    private TextView btnTabRecitation;
    private TextView btnTabTranslation;

    private LinearLayout layoutRecitation;
    private LinearLayout layoutTranslation;

    // Recitation Cards
    private MaterialCardView cardSheikhSufi;

    // Translation Cards
    private MaterialCardView cardSheikhDirir;
    private MaterialCardView cardSheikhFaruq;
    private MaterialCardView cardSheikhXayi;
    private MaterialCardView cardSheikhQudubi;
    private MaterialCardView cardSheikhSuuley;

    private long lastClickTime = 0;
    private String currentCategory = CATEGORY_RECITATION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sheikh_selection);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.sheikhMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (getIntent() != null && getIntent().hasExtra(EXTRA_CATEGORY)) {
            currentCategory = getIntent().getStringExtra(EXTRA_CATEGORY);
            if (currentCategory == null) currentCategory = CATEGORY_RECITATION;
        }

        initViews();
        setupClickListeners();
        switchCategory(currentCategory);
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        tvHeaderTitle = findViewById(R.id.tvHeaderTitle);
        tvHeaderSubtitle = findViewById(R.id.tvHeaderSubtitle);

        btnTabRecitation = findViewById(R.id.btnTabRecitation);
        btnTabTranslation = findViewById(R.id.btnTabTranslation);

        layoutRecitation = findViewById(R.id.layoutRecitation);
        layoutTranslation = findViewById(R.id.layoutTranslation);

        // Recitation Card
        cardSheikhSufi = findViewById(R.id.cardSheikhSufi);

        // Translation Cards
        cardSheikhDirir = findViewById(R.id.cardSheikhDirir);
        cardSheikhFaruq = findViewById(R.id.cardSheikhFaruq);
        cardSheikhXayi = findViewById(R.id.cardSheikhXayi);
        cardSheikhQudubi = findViewById(R.id.cardSheikhQudubi);
        cardSheikhSuuley = findViewById(R.id.cardSheikhSuuley);
    }

    private boolean isFastDoubleClick() {
        if (SystemClock.elapsedRealtime() - lastClickTime < 600) {
            return true;
        }
        lastClickTime = SystemClock.elapsedRealtime();
        return false;
    }

    private void switchCategory(String category) {
        this.currentCategory = category;

        if (CATEGORY_TRANSLATION.equalsIgnoreCase(category)) {
            layoutRecitation.setVisibility(View.GONE);
            layoutTranslation.setVisibility(View.VISIBLE);

            tvHeaderTitle.setText("Select Sheikh");
            tvHeaderSubtitle.setText("Somali Quran Audio Tafsiir Reciters");

            btnTabTranslation.setBackgroundResource(R.drawable.bg_tab_pill_active);
            btnTabTranslation.setTextColor(0xFF059669);

            btnTabRecitation.setBackgroundResource(R.drawable.bg_tab_pill_inactive);
            btnTabRecitation.setTextColor(0xFF6B7280);
        } else {
            layoutRecitation.setVisibility(View.VISIBLE);
            layoutTranslation.setVisibility(View.GONE);

            tvHeaderTitle.setText("Select Reciter");
            tvHeaderSubtitle.setText("Somali Quran Tilawah Reciters");

            btnTabRecitation.setBackgroundResource(R.drawable.bg_tab_pill_active);
            btnTabRecitation.setTextColor(0xFF059669);

            btnTabTranslation.setBackgroundResource(R.drawable.bg_tab_pill_inactive);
            btnTabTranslation.setTextColor(0xFF6B7280);
        }
    }

    private void setupClickListeners() {
        btnBack.setOnClickListener(v -> finish());

        // Category Tab switching
        btnTabRecitation.setOnClickListener(v -> switchCategory(CATEGORY_RECITATION));
        btnTabTranslation.setOnClickListener(v -> switchCategory(CATEGORY_TRANSLATION));

        // 1. Sheikh Abdul Rashid Ali Sufi (Tilawah - Active)
        cardSheikhSufi.setOnClickListener(v -> {
            if (isFastDoubleClick()) return;
            openSurahLibrary("suufi", "Abdul Rashid Ali Sufi");
        });

        // 2. Sheikh Dirir (Tafsiir - Active)
        cardSheikhDirir.setOnClickListener(v -> {
            if (isFastDoubleClick()) return;
            openSurahLibrary("dirir", "Sheekh Maxamed Cumar Dirir");
        });

        // Tafsiir Coming Soon
        cardSheikhFaruq.setOnClickListener(v -> {
            if (isFastDoubleClick()) return;
            Toast.makeText(this, "Sheekh Cumar Xaaji Cabdi Suldaan (Faaruuq) - Coming Soon", Toast.LENGTH_SHORT).show();
        });

        cardSheikhXayi.setOnClickListener(v -> {
            if (isFastDoubleClick()) return;
            Toast.makeText(this, "Sheekh Cabdi Xayi Sheekh Aadan - Coming Soon", Toast.LENGTH_SHORT).show();
        });

        cardSheikhQudubi.setOnClickListener(v -> {
            if (isFastDoubleClick()) return;
            Toast.makeText(this, "Dr. Sheekh Axmed Qudubi - Coming Soon", Toast.LENGTH_SHORT).show();
        });

        cardSheikhSuuley.setOnClickListener(v -> {
            if (isFastDoubleClick()) return;
            Toast.makeText(this, "Sheekh Maxamuud Sheekh Ibrahim Suuley - Coming Soon", Toast.LENGTH_SHORT).show();
        });
    }

    private void openSurahLibrary(String sheikhId, String sheikhName) {
        Intent intent = new Intent(SheikhSelectionActivity.this, SurahLibraryActivity.class);
        intent.putExtra(EXTRA_SHEIKH_ID, sheikhId);
        intent.putExtra(EXTRA_SHEIKH_NAME, sheikhName);
        intent.putExtra(EXTRA_CATEGORY, currentCategory);
        startActivity(intent);
    }
}
