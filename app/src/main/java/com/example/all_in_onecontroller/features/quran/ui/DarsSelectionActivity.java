package com.example.all_in_onecontroller.features.quran.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.all_in_onecontroller.R;
import com.example.all_in_onecontroller.features.quran.data.QuranDatabase;
import com.example.all_in_onecontroller.features.quran.model.Dars;
import com.example.all_in_onecontroller.features.quran.ui.adapter.DarsAdapter;

import java.util.List;

public class DarsSelectionActivity extends AppCompatActivity implements DarsAdapter.OnDarsClickListener {

    private ImageButton btnBack;
    private TextView tvSurahTitle;
    private TextView tvSheikhTitle;
    private RecyclerView rvDars;

    private DarsAdapter darsAdapter;
    private String selectedSheikhId = "dirir";
    private String selectedSheikhName = "Sheikh Dirir";
    private int surahNumber = 1;
    private String surahTitle = "Surah Al-Fatiha";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dars_selection);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.darsMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (getIntent() != null) {
            String id = getIntent().getStringExtra(SheikhSelectionActivity.EXTRA_SHEIKH_ID);
            String name = getIntent().getStringExtra(SheikhSelectionActivity.EXTRA_SHEIKH_NAME);
            surahNumber = getIntent().getIntExtra("extra_surah_number", 1);
            String title = getIntent().getStringExtra("extra_surah_title");
            if (id != null) selectedSheikhId = id;
            if (name != null) selectedSheikhName = name;
            if (title != null) surahTitle = title;
        }

        initViews();
        setupRecyclerView();
        loadDarsList();
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        tvSurahTitle = findViewById(R.id.tvSurahTitle);
        tvSheikhTitle = findViewById(R.id.tvSheikhTitle);
        rvDars = findViewById(R.id.rvDars);

        btnBack.setOnClickListener(v -> finish());
        tvSurahTitle.setText(surahTitle + " - Lessons");
        tvSheikhTitle.setText("Reciter: " + selectedSheikhName);
    }

    private void setupRecyclerView() {
        darsAdapter = new DarsAdapter(this);
        rvDars.setLayoutManager(new LinearLayoutManager(this));
        rvDars.setAdapter(darsAdapter);
    }

    private void loadDarsList() {
        List<Dars> darsList = QuranDatabase.getDarsListForSurah(selectedSheikhId, surahNumber);
        darsAdapter.setDarsList(darsList);
    }

    @Override
    public void onDarsClick(Dars dars) {
        Intent intent = new Intent(this, QuranPlayerActivity.class);
        intent.putExtra(SheikhSelectionActivity.EXTRA_SHEIKH_ID, selectedSheikhId);
        intent.putExtra(SheikhSelectionActivity.EXTRA_SHEIKH_NAME, selectedSheikhName);
        intent.putExtra("extra_surah_number", surahNumber);
        intent.putExtra("extra_surah_title", surahTitle);
        intent.putExtra("extra_dars_id", dars.getId());
        intent.putExtra("extra_dars_title", dars.getTitle());
        intent.putExtra("extra_audio_url", dars.getAudioUrl());
        startActivity(intent);
    }
}