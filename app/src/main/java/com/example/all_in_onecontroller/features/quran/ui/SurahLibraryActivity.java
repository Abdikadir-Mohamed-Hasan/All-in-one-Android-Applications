package com.example.all_in_onecontroller.features.quran.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
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
import com.example.all_in_onecontroller.features.quran.model.Surah;
import com.example.all_in_onecontroller.features.quran.ui.adapter.SurahAdapter;

import java.util.ArrayList;
import java.util.List;

public class SurahLibraryActivity extends AppCompatActivity implements SurahAdapter.OnSurahClickListener {

    private ImageButton btnBack;
    private TextView tvSelectedSheikhBadge;
    private EditText etSearchSurah;
    private RecyclerView rvSurahs;

    private SurahAdapter surahAdapter;
    private String selectedSheikhId = "dirir";
    private String selectedSheikhName = "Sheikh Dirir";
    private String category = SheikhSelectionActivity.CATEGORY_RECITATION;
    private boolean isRecitation = false;

    private List<Surah> allSurahs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_surah_library);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.surahLibraryMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (getIntent() != null) {
            String id = getIntent().getStringExtra(SheikhSelectionActivity.EXTRA_SHEIKH_ID);
            String name = getIntent().getStringExtra(SheikhSelectionActivity.EXTRA_SHEIKH_NAME);
            String cat = getIntent().getStringExtra(SheikhSelectionActivity.EXTRA_CATEGORY);
            if (id != null) selectedSheikhId = id;
            if (name != null) selectedSheikhName = name;
            if (cat != null) category = cat;
        }

        isRecitation = SheikhSelectionActivity.CATEGORY_RECITATION.equalsIgnoreCase(category)
                || "suufi".equalsIgnoreCase(selectedSheikhId)
                || "sufi".equalsIgnoreCase(selectedSheikhId);

        initViews();
        setupRecyclerView();
        loadSurahs();
        setupSearchFilter();
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        tvSelectedSheikhBadge = findViewById(R.id.tvSelectedSheikhBadge);
        etSearchSurah = findViewById(R.id.etSearchSurah);
        rvSurahs = findViewById(R.id.rvSurahs);

        btnBack.setOnClickListener(v -> finish());
        String label = isRecitation ? "Reciter: " : "Sheikh: ";
        tvSelectedSheikhBadge.setText(label + selectedSheikhName);
    }

    private void setupRecyclerView() {
        surahAdapter = new SurahAdapter(this);
        surahAdapter.setRecitationMode(isRecitation);
        rvSurahs.setLayoutManager(new LinearLayoutManager(this));
        rvSurahs.setAdapter(surahAdapter);
    }

    private void loadSurahs() {
        allSurahs = QuranDatabase.getSurahs();
        surahAdapter.setSurahs(allSurahs);
    }

    private void setupSearchFilter() {
        etSearchSurah.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterSurahs(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void filterSurahs(String query) {
        if (query == null || query.trim().isEmpty()) {
            surahAdapter.setSurahs(allSurahs);
            return;
        }

        String lowerQuery = query.toLowerCase().trim();
        List<Surah> filtered = new ArrayList<>();
        for (Surah surah : allSurahs) {
            if (surah.getTransliteration().toLowerCase().contains(lowerQuery)
                    || surah.getArabicName().contains(lowerQuery)
                    || surah.getEnglishMeaning().toLowerCase().contains(lowerQuery)
                    || String.valueOf(surah.getNumber()).equals(lowerQuery)) {
                filtered.add(surah);
            }
        }
        surahAdapter.setSurahs(filtered);
    }

    @Override
    public void onSurahClick(Surah surah) {
        List<Dars> darsList = QuranDatabase.getDarsListForSurah(selectedSheikhId, surah.getNumber());
        if (!isRecitation && darsList != null && darsList.size() > 1) {
            Intent intent = new Intent(this, DarsSelectionActivity.class);
            intent.putExtra(SheikhSelectionActivity.EXTRA_SHEIKH_ID, selectedSheikhId);
            intent.putExtra(SheikhSelectionActivity.EXTRA_SHEIKH_NAME, selectedSheikhName);
            intent.putExtra(SheikhSelectionActivity.EXTRA_CATEGORY, category);
            intent.putExtra("extra_surah_number", surah.getNumber());
            intent.putExtra("extra_surah_title", surah.getTransliteration() + " (" + surah.getArabicName() + ")");
            startActivity(intent);
        } else {
            Dars darsToPlay = (darsList != null && !darsList.isEmpty()) ? darsList.get(0) : null;

            Intent intent = new Intent(this, QuranPlayerActivity.class);
            intent.putExtra(SheikhSelectionActivity.EXTRA_SHEIKH_ID, selectedSheikhId);
            intent.putExtra(SheikhSelectionActivity.EXTRA_SHEIKH_NAME, selectedSheikhName);
            intent.putExtra(SheikhSelectionActivity.EXTRA_CATEGORY, category);
            intent.putExtra("extra_surah_number", surah.getNumber());
            intent.putExtra("extra_surah_title", surah.getTransliteration() + " (" + surah.getArabicName() + ")");
            if (darsToPlay != null) {
                intent.putExtra("extra_dars_id", darsToPlay.getId());
                intent.putExtra("extra_dars_title", darsToPlay.getTitle());
                intent.putExtra("extra_audio_url", darsToPlay.getAudioUrl());
            }
            startActivity(intent);
        }
    }
}
