package com.example.all_in_onecontroller.features.quran.ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.widget.NestedScrollView;

import com.example.all_in_onecontroller.R;
import com.example.all_in_onecontroller.features.quran.data.QuranDatabase;
import com.example.all_in_onecontroller.features.quran.data.QuranTranslationProvider;
import com.example.all_in_onecontroller.features.quran.model.Dars;
import com.example.all_in_onecontroller.features.quran.model.QuranVerse;
import com.example.all_in_onecontroller.features.quran.model.Surah;
import com.example.all_in_onecontroller.features.quran.service.QuranAudioPlayerManager;
import com.example.all_in_onecontroller.features.quran.ui.span.AyahCircleSpan;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class QuranPlayerActivity extends AppCompatActivity implements QuranAudioPlayerManager.OnMediaStateChangeListener {

    // Top Header Views
    private ImageButton btnBack;
    private TextView tvHeaderSurahTitle;
    private TextView tvHeaderSheikhName;

    // Player Card Views
    private TextView tvPlayerDarsTitle;
    private TextView tvPlayerSheikhName;
    private TextView tvPlaybackStatus;
    private SeekBar seekBarPlayback;
    private TextView tvCurrentTime;
    private TextView tvTotalDuration;

    private ImageButton btnLoop;
    private ImageButton btnRewind10;
    private ImageButton btnPrevious;
    private FloatingActionButton btnPlayPause;
    private ImageButton btnNext;
    private ImageButton btnForward10;
    private TextView btnSpeed;

    // Script Zoom Views
    private ImageButton btnFontSizeMinus;
    private ImageButton btnFontSizePlus;

    // Mushaf Views
    private NestedScrollView scrollQuranText;
    private TextView tvSurahHeaderArabic;
    private TextView tvBismillah;
    private TextView tvFullQuranText;

    // Interactive Ayah Action Card
    private MaterialCardView cardAyahDetail;
    private TextView tvSelectedAyahBadge;
    private ImageButton btnCloseAyahDetail;
    private TextView tvSelectedAyahArabic;
    private TextView tvSelectedAyahSomali;
    private MaterialButton btnCopyAyah;
    private MaterialButton btnShareAyah;
    private MaterialButton btnBookmarkAyah;

    // Audio & State
    private QuranAudioPlayerManager audioPlayerManager;
    private Typeface quranFont;
    private BackgroundColorSpan activeHighlightSpan;
    private QuranVerse currentlySelectedVerse = null;

    private String selectedSheikhId = "dirir";
    private String selectedSheikhName = "Sheikh Dirir";
    private int surahNumber = 1;
    private String surahTitle = "Surah Al-Fatiha";
    private String darsTitle = "Dars 1: Surah Al-Fatiha (Ayah 1-7)";
    private String audioUrl = "https://archive.org/download/TafsiirQuraankaKariimka-AfSoomali/001alfaatixa.mp3";

    private float currentFontSizeSp = 25f;
    private final float[] speeds = {1.0f, 1.25f, 1.5f, 2.0f, 0.75f};
    private int speedIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quran_player);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.playerMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Load authentic Quran font
        try {
            quranFont = ResourcesCompat.getFont(this, R.font.quran_font);
        } catch (Exception e) {
            quranFont = Typeface.DEFAULT_BOLD;
        }

        if (getIntent() != null) {
            String id = getIntent().getStringExtra(SheikhSelectionActivity.EXTRA_SHEIKH_ID);
            String name = getIntent().getStringExtra(SheikhSelectionActivity.EXTRA_SHEIKH_NAME);
            int sNum = getIntent().getIntExtra("extra_surah_number", 1);
            String sTitle = getIntent().getStringExtra("extra_surah_title");
            String dTitle = getIntent().getStringExtra("extra_dars_title");
            String url = getIntent().getStringExtra("extra_audio_url");

            if (id != null) selectedSheikhId = id;
            if (name != null) selectedSheikhName = name;
            surahNumber = sNum;
            if (sTitle != null) surahTitle = sTitle;
            if (dTitle != null) darsTitle = dTitle;
            if (url != null) audioUrl = url;
        }

        initViews();
        setupPlayerControls();
        setupScriptControls();
        loadFullQuranPage();

        audioPlayerManager = QuranAudioPlayerManager.getInstance();
        audioPlayerManager.setContext(this);
        audioPlayerManager.setListener(this);

        startAudioPlayback();
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        tvHeaderSurahTitle = findViewById(R.id.tvHeaderSurahTitle);
        tvHeaderSheikhName = findViewById(R.id.tvHeaderSheikhName);

        tvPlayerDarsTitle = findViewById(R.id.tvPlayerDarsTitle);
        tvPlayerSheikhName = findViewById(R.id.tvPlayerSheikhName);
        tvPlaybackStatus = findViewById(R.id.tvPlaybackStatus);
        seekBarPlayback = findViewById(R.id.seekBarPlayback);
        tvCurrentTime = findViewById(R.id.tvCurrentTime);
        tvTotalDuration = findViewById(R.id.tvTotalDuration);

        btnLoop = findViewById(R.id.btnLoop);
        btnRewind10 = findViewById(R.id.btnRewind10);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnPlayPause = findViewById(R.id.btnPlayPause);
        btnNext = findViewById(R.id.btnNext);
        btnForward10 = findViewById(R.id.btnForward10);
        btnSpeed = findViewById(R.id.btnSpeed);

        btnFontSizeMinus = findViewById(R.id.btnFontSizeMinus);
        btnFontSizePlus = findViewById(R.id.btnFontSizePlus);

        scrollQuranText = findViewById(R.id.scrollQuranText);
        tvSurahHeaderArabic = findViewById(R.id.tvSurahHeaderArabic);
        tvBismillah = findViewById(R.id.tvBismillah);
        tvFullQuranText = findViewById(R.id.tvFullQuranText);

        // Interactive Ayah Detail Card
        cardAyahDetail = findViewById(R.id.cardAyahDetail);
        tvSelectedAyahBadge = findViewById(R.id.tvSelectedAyahBadge);
        btnCloseAyahDetail = findViewById(R.id.btnCloseAyahDetail);
        tvSelectedAyahArabic = findViewById(R.id.tvSelectedAyahArabic);
        tvSelectedAyahSomali = findViewById(R.id.tvSelectedAyahSomali);
        btnCopyAyah = findViewById(R.id.btnCopyAyah);
        btnShareAyah = findViewById(R.id.btnShareAyah);
        btnBookmarkAyah = findViewById(R.id.btnBookmarkAyah);

        btnBack.setOnClickListener(v -> finish());

        tvHeaderSurahTitle.setText(surahTitle);
        tvHeaderSheikhName.setText("Reciter: " + selectedSheikhName);
        if (darsTitle == null || darsTitle.isEmpty() || darsTitle.contains("Tilawah")) {
            darsTitle = surahTitle.startsWith("Surah") ? surahTitle : "Surah " + surahTitle;
        }
        tvPlayerDarsTitle.setText(darsTitle);
        tvPlayerSheikhName.setText(selectedSheikhName);

        if (quranFont != null) {
            tvSurahHeaderArabic.setTypeface(quranFont);
            tvBismillah.setTypeface(quranFont);
            tvFullQuranText.setTypeface(quranFont);
            tvSelectedAyahArabic.setTypeface(quranFont);
        }

        tvFullQuranText.setMovementMethod(LinkMovementMethod.getInstance());
        tvFullQuranText.setHighlightColor(Color.TRANSPARENT);
    }

    private void setupPlayerControls() {
        btnPlayPause.setOnClickListener(v -> audioPlayerManager.togglePlayPause());

        btnLoop.setOnClickListener(v -> {
            audioPlayerManager.toggleLooping();
            boolean isLoop = audioPlayerManager.isLooping();
            btnLoop.setColorFilter(isLoop ? 0xFF0D9488 : 0xFF94A3B8);
            Toast.makeText(this, isLoop ? "Repeat Audio Enabled" : "Repeat Disabled", Toast.LENGTH_SHORT).show();
        });

        // 10s Rewind
        btnRewind10.setOnClickListener(v -> {
            audioPlayerManager.seekBy(-10000);
            Toast.makeText(this, "⏪ -10s", Toast.LENGTH_SHORT).show();
        });

        // 10s Fast-Forward
        btnForward10.setOnClickListener(v -> {
            audioPlayerManager.seekBy(10000);
            Toast.makeText(this, "⏩ +10s", Toast.LENGTH_SHORT).show();
        });

        // Audio Playback Speed
        btnSpeed.setOnClickListener(v -> {
            speedIndex = (speedIndex + 1) % speeds.length;
            float speed = speeds[speedIndex];
            audioPlayerManager.setPlaybackSpeed(speed);
            btnSpeed.setText(String.format(Locale.US, "%.2gx", speed));
            Toast.makeText(this, "Speed: " + speed + "x", Toast.LENGTH_SHORT).show();
        });

        btnPrevious.setOnClickListener(v -> {
            if (surahNumber > 1) {
                surahNumber--;
                Surah s = getSurahByNumber(surahNumber);
                if (s != null) {
                    surahTitle = s.getTransliteration() + " (" + s.getArabicName() + ")";
                } else {
                    surahTitle = "Surah " + surahNumber;
                }
                darsTitle = "Surah " + surahNumber;
                updatePlayerMetadataAndPlay();
            } else {
                Toast.makeText(this, "First surah reached", Toast.LENGTH_SHORT).show();
            }
        });

        btnNext.setOnClickListener(v -> {
            if (surahNumber < 114) {
                surahNumber++;
                Surah s = getSurahByNumber(surahNumber);
                if (s != null) {
                    surahTitle = s.getTransliteration() + " (" + s.getArabicName() + ")";
                } else {
                    surahTitle = "Surah " + surahNumber;
                }
                darsTitle = "Surah " + surahNumber;
                updatePlayerMetadataAndPlay();
            } else {
                Toast.makeText(this, "Last surah reached", Toast.LENGTH_SHORT).show();
            }
        });

        seekBarPlayback.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    int durationMs = audioPlayerManager.getDuration();
                    if (durationMs > 0) {
                        int targetMs = (progress * durationMs) / 100;
                        audioPlayerManager.seekTo(targetMs);
                        tvCurrentTime.setText(formatTimeMs(targetMs));
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    private void setupScriptControls() {
        // Zoom Font Size
        btnFontSizePlus.setOnClickListener(v -> {
            if (currentFontSizeSp < 36f) {
                currentFontSizeSp += 2f;
                tvFullQuranText.setTextSize(currentFontSizeSp);
                loadFullQuranPage();
            }
        });

        btnFontSizeMinus.setOnClickListener(v -> {
            if (currentFontSizeSp > 18f) {
                currentFontSizeSp -= 2f;
                tvFullQuranText.setTextSize(currentFontSizeSp);
                loadFullQuranPage();
            }
        });

        // Ayah Detail Interactive Buttons
        btnCloseAyahDetail.setOnClickListener(v -> {
            cardAyahDetail.setVisibility(View.GONE);
            clearAyahHighlight();
        });

        btnCopyAyah.setOnClickListener(v -> {
            if (currentlySelectedVerse != null) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Quran Ayah", currentlySelectedVerse.getUthmaniText());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(this, "Ayah " + currentlySelectedVerse.getVerseNumber() + " copied to clipboard", Toast.LENGTH_SHORT).show();
            }
        });

        btnShareAyah.setOnClickListener(v -> {
            if (currentlySelectedVerse != null) {
                String somali = QuranTranslationProvider.getSomaliTranslation(
                        currentlySelectedVerse.getSurahNumber(), currentlySelectedVerse.getVerseNumber());
                String shareText = currentlySelectedVerse.getUthmaniText() + "\n\n" +
                        "Tafsiirka Af-Soomaali:\n" + somali + "\n\n" +
                        "[Surah " + surahTitle + " - Aayadda " + currentlySelectedVerse.getVerseNumber() + "]";

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, shareText);
                startActivity(Intent.createChooser(intent, "Share Quran Verse"));
            }
        });

        btnBookmarkAyah.setOnClickListener(v -> {
            if (currentlySelectedVerse != null) {
                Toast.makeText(this, "Ayah " + currentlySelectedVerse.getVerseNumber() + " bookmarked!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updatePlayerMetadataAndPlay() {
        tvHeaderSurahTitle.setText(surahTitle);
        List<Dars> darsList = QuranDatabase.getDarsListForSurah(selectedSheikhId, surahNumber);
        if (darsList != null && !darsList.isEmpty()) {
            audioUrl = darsList.get(0).getAudioUrl();
            darsTitle = darsList.get(0).getTitle();
        } else {
            audioUrl = null;
            darsTitle = surahTitle.startsWith("Surah") ? surahTitle : "Surah " + surahTitle;
        }
        if (darsTitle.contains("Tilawah")) {
            darsTitle = surahTitle.startsWith("Surah") ? surahTitle : "Surah " + surahTitle;
        }
        tvPlayerDarsTitle.setText(darsTitle);
        loadFullQuranPage();
        startAudioPlayback();
    }

    private void startAudioPlayback() {
        if (audioUrl == null || audioUrl.isEmpty()) {
            List<Dars> darsList = QuranDatabase.getDarsListForSurah(selectedSheikhId, surahNumber);
            if (darsList != null && !darsList.isEmpty()) {
                audioUrl = darsList.get(0).getAudioUrl();
                if (darsTitle == null || darsTitle.isEmpty()) {
                    darsTitle = darsList.get(0).getTitle();
                }
            }
        }
        if (darsTitle == null || darsTitle.isEmpty()) {
            darsTitle = surahTitle;
        }
        tvPlayerDarsTitle.setText(darsTitle);
        Dars dars = new Dars("current_dars", surahNumber, 1, darsTitle, "All Verses", audioUrl, null, null, true);
        audioPlayerManager.playDars(dars, selectedSheikhName, surahTitle);
    }

    /**
     * Renders the authentic Light Quran page:
     * - Surah name inside the header frame
     * - Bismillah centered after the Surah header (not treated as a verse)
     * - Continuous Madinah Mushaf typography
     * - Circular Ayah markers with uniform, beautiful spacing
     */
    private void loadFullQuranPage() {
        String arabicSurahName = "سُورَةُ " + getArabicSurahNameByNumber(surahNumber);
        tvSurahHeaderArabic.setText(arabicSurahName);

        // Bismillah is placed centered after the surah name (except Surah 9 At-Tawbah)
        if (surahNumber == 9) {
            tvBismillah.setVisibility(View.GONE);
        } else {
            tvBismillah.setVisibility(View.VISIBLE);
            tvBismillah.setText("بِسْمِ ٱللَّهِ ٱلرَّحْمَٰنِ ٱلرَّحِيمِ");
        }

        List<QuranVerse> displayVerses = getDisplayVersesForSurah(surahNumber);
        if (displayVerses.isEmpty()) {
            tvFullQuranText.setText("No verses found for this Surah.");
            return;
        }

        SpannableStringBuilder ssb = new SpannableStringBuilder();
        float density = getResources().getDisplayMetrics().density;

        for (int i = 0; i < displayVerses.size(); i++) {
            QuranVerse verse = displayVerses.get(i);
            int verseStart = ssb.length();
            ssb.append(verse.getUthmaniText());

            // Append circular Ayah disc badge with clean, uniform padding
            int badgeStart = ssb.length();
            ssb.append("\u00A0"); // Single non-breaking token replaced by AyahCircleSpan
            int badgeEnd = ssb.length();

            ssb.setSpan(new AyahCircleSpan(verse.getVerseNumber(), density, quranFont),
                    badgeStart, badgeEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            int verseEnd = ssb.length();

            // Interactive Clickable Span for each Ayah
            ClickableSpan clickSpan = new ClickableSpan() {
                @Override
                public void onClick(@NonNull View widget) {
                    onAyahTapped(verse, verseStart, verseEnd, ssb);
                }

                @Override
                public void updateDrawState(@NonNull TextPaint ds) {
                    ds.setUnderlineText(false);
                }
            };

            ssb.setSpan(clickSpan, verseStart, verseEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            if (i < displayVerses.size() - 1) {
                ssb.append(" ");
            }
        }

        tvFullQuranText.setText(ssb);
    }

    /**
     * Prepares verses without treating Bismillah as an inline verse:
     * - For Surah 1 (Al-Fatiha): returns verses 1 to 7 starting with Al-Hamdu lillah
     * - For other Surahs: strips any prefixed Bismillah from verse 1 text
     */
    private List<QuranVerse> getDisplayVersesForSurah(int sNum) {
        if (sNum == 1) {
            List<QuranVerse> list = new ArrayList<>();
            list.add(new QuranVerse(1, 1, "ٱلْحَمْدُ لِلَّهِ رَبِّ ٱلْعَالَمِينَ", "الحمد لله رب العالمين"));
            list.add(new QuranVerse(1, 2, "ٱلرَّحْمَٰنِ ٱلرَّحِيمِ", "الرحمن الرحيم"));
            list.add(new QuranVerse(1, 3, "مَـٰلِكِ يَوْمِ ٱلدِّينِ", "مالك يوم الدين"));
            list.add(new QuranVerse(1, 4, "إِيَّاكَ نَعْبُدُ وَإِيَّاكَ نَسْتَعِينُ", "إياك نعبد وإياك نستعين"));
            list.add(new QuranVerse(1, 5, "ٱهْدِنَا ٱلصِّرَاطَ ٱلْمُسْتَقِيمَ", "اهدنا الصراط المستقيم"));
            list.add(new QuranVerse(1, 6, "صِرَاطَ ٱلَّذِينَ أَنْعَمْتَ عَلَيْهِمْ", "صراط الذين أنعمت عليهم"));
            list.add(new QuranVerse(1, 7, "غَيْرِ ٱلْمَغْضُوبِ عَلَيْهِمْ وَلَا ٱلضَّالِّينَ", "غير المغضوب عليهم ولا الضالين"));
            return list;
        }

        List<QuranVerse> raw = QuranDatabase.getVersesForSurah(this, sNum);
        List<QuranVerse> list = new ArrayList<>();
        for (int i = 0; i < raw.size(); i++) {
            QuranVerse v = raw.get(i);
            if (i == 0) {
                String cleanText = stripBismillahPrefix(v.getUthmaniText());
                list.add(new QuranVerse(v.getSurahNumber(), v.getVerseNumber(), cleanText, cleanText));
            } else {
                list.add(v);
            }
        }
        return list;
    }

    private String stripBismillahPrefix(String text) {
        if (text == null) return "";
        String[] bismillahForms = {
            "بِسمِ اللَّهِ الرَّحمـٰنِ الرَّحيمِ",
            "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ",
            "بِسْمِ ٱللَّهِ ٱلرَّحْمَٰنِ ٱلرَّحِيمِ",
            "بسم الله الرحمن الرحيم"
        };
        for (String b : bismillahForms) {
            if (text.startsWith(b)) {
                return text.substring(b.length()).trim();
            }
        }
        return text;
    }

    private void onAyahTapped(QuranVerse verse, int start, int end, SpannableStringBuilder ssb) {
        currentlySelectedVerse = verse;

        // Apply glowing soft mint highlight in light mode
        if (activeHighlightSpan != null) {
            ssb.removeSpan(activeHighlightSpan);
        }
        activeHighlightSpan = new BackgroundColorSpan(0x2E0D9488);
        ssb.setSpan(activeHighlightSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvFullQuranText.setText(ssb);

        // Populate Interactive Bottom Sheet Card
        tvSelectedAyahBadge.setText("Ayah " + verse.getVerseNumber() + " Selected");
        tvSelectedAyahArabic.setText(verse.getUthmaniText());
        String somaliMeaning = QuranTranslationProvider.getSomaliTranslation(verse.getSurahNumber(), verse.getVerseNumber());
        tvSelectedAyahSomali.setText(somaliMeaning);

        cardAyahDetail.setVisibility(View.VISIBLE);
    }

    private void clearAyahHighlight() {
        if (tvFullQuranText.getText() instanceof SpannableStringBuilder && activeHighlightSpan != null) {
            SpannableStringBuilder ssb = (SpannableStringBuilder) tvFullQuranText.getText();
            ssb.removeSpan(activeHighlightSpan);
            activeHighlightSpan = null;
            tvFullQuranText.setText(ssb);
        }
        currentlySelectedVerse = null;
    }

    private String getArabicSurahNameByNumber(int num) {
        if (num == 1) return "الفَاتِحَةِ";
        if (num == 2) return "البَقَرَةِ";
        if (num == 3) return "آلِ عِمْرَانَ";
        if (num == 36) return "يس";
        if (num == 55) return "الرَّحْمَٰنِ";
        if (num == 67) return "المُلْكِ";
        if (num == 112) return "الإِخْلَاصِ";
        if (num == 113) return "الفَلَقِ";
        if (num == 114) return "النَّاسِ";
        Surah s = getSurahByNumber(num);
        return s != null ? s.getArabicName() : surahTitle;
    }

    private Surah getSurahByNumber(int num) {
        for (Surah s : QuranDatabase.getSurahs()) {
            if (s.getNumber() == num) return s;
        }
        return null;
    }

    // Media State Listener Callbacks
    @Override
    public void onPreparing() {
        runOnUiThread(() -> {
            tvPlaybackStatus.setText("Loading Audio...");
            tvPlaybackStatus.setTextColor(0xFFD97706);
            btnPlayPause.setImageResource(R.drawable.ic_play);
        });
    }

    @Override
    public void onReady(int durationMs) {
        runOnUiThread(() -> {
            tvPlaybackStatus.setText("Now Playing");
            tvPlaybackStatus.setTextColor(0xFF059669);
            tvTotalDuration.setText(formatTimeMs(durationMs));
            btnPlayPause.setImageResource(R.drawable.ic_pause);
        });
    }

    @Override
    public void onPlaybackStarted() {
        runOnUiThread(() -> {
            tvPlaybackStatus.setText("Now Playing");
            tvPlaybackStatus.setTextColor(0xFF059669);
            btnPlayPause.setImageResource(R.drawable.ic_pause);
        });
    }

    @Override
    public void onPlaybackPaused() {
        runOnUiThread(() -> {
            tvPlaybackStatus.setText("Playback Paused");
            tvPlaybackStatus.setTextColor(0xFF64748B);
            btnPlayPause.setImageResource(R.drawable.ic_play);
        });
    }

    @Override
    public void onPlaybackProgress(int currentPositionMs, int durationMs) {
        runOnUiThread(() -> {
            tvCurrentTime.setText(formatTimeMs(currentPositionMs));
            if (durationMs > 0) {
                tvTotalDuration.setText(formatTimeMs(durationMs));
                int progressPercent = (currentPositionMs * 100) / durationMs;
                seekBarPlayback.setProgress(progressPercent);
            }
        });
    }

    @Override
    public void onError(String errorMessage) {
        runOnUiThread(() -> {
            tvPlaybackStatus.setText("Stream Error");
            tvPlaybackStatus.setTextColor(0xFFEF4444);
            btnPlayPause.setImageResource(R.drawable.ic_play);
            Toast.makeText(QuranPlayerActivity.this, errorMessage, Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public void onCompleted() {
        runOnUiThread(() -> {
            tvPlaybackStatus.setText("Dars Completed");
            tvPlaybackStatus.setTextColor(0xFF059669);
            btnPlayPause.setImageResource(R.drawable.ic_play);
            seekBarPlayback.setProgress(100);
        });
    }

    private String formatTimeMs(int totalMs) {
        int totalSeconds = totalMs / 1000;
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (audioPlayerManager != null) {
            audioPlayerManager.stopAndRelease();
        }
    }
}
