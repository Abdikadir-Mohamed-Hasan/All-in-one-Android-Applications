package com.example.all_in_onecontroller.features.quran.ui;

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
import com.google.android.material.card.MaterialCardView;

public class QuranHomeActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private MaterialCardView cardRecitation;
    private MaterialCardView cardAudioTranslation;

    private long lastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quran_home);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.quranHomeMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        setupClickListeners();
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        cardRecitation = findViewById(R.id.cardRecitation);
        cardAudioTranslation = findViewById(R.id.cardAudioTranslation);
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

        // Card A: Quran Recitation (Tilawah - Active)
        cardRecitation.setOnClickListener(v -> {
            if (isFastDoubleClick()) return;
            Intent intent = new Intent(QuranHomeActivity.this, SheikhSelectionActivity.class);
            intent.putExtra(SheikhSelectionActivity.EXTRA_CATEGORY, SheikhSelectionActivity.CATEGORY_RECITATION);
            startActivity(intent);
        });

        // Card B: Audio Translation (Tafsiir - Active)
        cardAudioTranslation.setOnClickListener(v -> {
            if (isFastDoubleClick()) return;
            Intent intent = new Intent(QuranHomeActivity.this, SheikhSelectionActivity.class);
            intent.putExtra(SheikhSelectionActivity.EXTRA_CATEGORY, SheikhSelectionActivity.CATEGORY_TRANSLATION);
            startActivity(intent);
        });
    }
}
