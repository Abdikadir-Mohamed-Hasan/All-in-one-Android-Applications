package com.example.all_in_onecontroller.features.hub;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.all_in_onecontroller.R;
import com.example.all_in_onecontroller.features.barodalkaga.BaroDalkagaActivity;
import com.example.all_in_onecontroller.features.phonecontroller.PhoneControllerActivity;
import com.example.all_in_onecontroller.features.projectorcontroller.ProjectorControllerActivity;
import com.example.all_in_onecontroller.features.quran.ui.QuranHomeActivity;
import com.example.all_in_onecontroller.features.tourguide.TourGuideHomeActivity;
import com.google.android.material.card.MaterialCardView;

public class AssignmentHubActivity extends AppCompatActivity {

    private MaterialCardView cardPhoneController;
    private MaterialCardView cardQuranApp;
    private MaterialCardView cardBaroDalkaga;
    private MaterialCardView cardProjectorController;
    private MaterialCardView cardTourGuide;

    private long lastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_assignment_hub);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.hubMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        setupClickListeners();
    }

    private void initViews() {
        cardPhoneController = findViewById(R.id.cardPhoneController);
        cardQuranApp = findViewById(R.id.cardQuranApp);
        cardBaroDalkaga = findViewById(R.id.cardBaroDalkaga);
        cardProjectorController = findViewById(R.id.cardProjectorController);
        cardTourGuide = findViewById(R.id.cardTourGuide);
    }

    private boolean isFastDoubleClick() {
        if (SystemClock.elapsedRealtime() - lastClickTime < 600) {
            return true;
        }
        lastClickTime = SystemClock.elapsedRealtime();
        return false;
    }

    private void setupClickListeners() {
        // Phone Controller
        cardPhoneController.setOnClickListener(v -> {
            if (isFastDoubleClick()) return;
            Intent intent = new Intent(AssignmentHubActivity.this, PhoneControllerActivity.class);
            startActivity(intent);
        });

        // Quran App
        cardQuranApp.setOnClickListener(v -> {
            if (isFastDoubleClick()) return;
            Intent intent = new Intent(AssignmentHubActivity.this, QuranHomeActivity.class);
            startActivity(intent);
        });

        // Baro Dalkaga
        cardBaroDalkaga.setOnClickListener(v -> {
            if (isFastDoubleClick()) return;
            Intent intent = new Intent(AssignmentHubActivity.this, BaroDalkagaActivity.class);
            startActivity(intent);
        });

        // Projector Controller
        cardProjectorController.setOnClickListener(v -> {
            if (isFastDoubleClick()) return;
            Intent intent = new Intent(AssignmentHubActivity.this, ProjectorControllerActivity.class);
            startActivity(intent);
        });

        // Somalia Tour Guide
        cardTourGuide.setOnClickListener(v -> {
            if (isFastDoubleClick()) return;
            Intent intent = new Intent(AssignmentHubActivity.this, TourGuideHomeActivity.class);
            startActivity(intent);
        });
    }
}