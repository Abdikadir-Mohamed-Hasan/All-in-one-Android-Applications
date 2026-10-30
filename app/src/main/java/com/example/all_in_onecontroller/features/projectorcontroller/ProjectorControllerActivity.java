package com.example.all_in_onecontroller.features.projectorcontroller;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.hardware.ConsumerIrManager;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.all_in_onecontroller.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProjectorControllerActivity extends AppCompatActivity {

    private ImageButton btnBack;

    // Projector Screen Display Views
    private MaterialCardView cardProjectorScreen;
    private ImageView imgStatusLed;
    private ImageView imgScreenProjectionIcon;
    private TextView tvProjectorScreenTitle;
    private TextView tvProjectorScreenDesc;

    // IR Blaster & Status Views
    private TextView tvIrHardwareInfo;
    private TextView tvProjectorStatus;
    private MaterialCardView cardPowerRing;
    private FloatingActionButton btnProjectorPower;
    private TextView tvPowerButtonHint;

    private ConsumerIrManager irManager;
    private boolean isProjectorOn = false;

    // Standard 38kHz NEC Protocol IR Power Command Pattern (microseconds)
    private static final int IR_FREQUENCY = 38000;
    private static final int[] IR_POWER_PATTERN = {
            9000, 4500,
            560, 1690, 560, 1690, 560, 560, 560, 560, 560, 560, 560, 560, 560, 560, 560, 560,
            560, 1690, 560, 1690, 560, 1690, 560, 1690, 560, 1690, 560, 1690, 560, 1690, 560, 1690,
            560, 560, 560, 1690, 560, 560, 560, 560, 560, 560, 560, 560, 560, 560, 560, 560,
            560, 1690, 560, 560, 560, 1690, 560, 1690, 560, 1690, 560, 1690, 560, 1690, 560, 1690,
            560
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_projector_controller);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.projectorMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        setupIrHardware();
        setupListeners();
        updateProjectorPowerUi();
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);

        cardProjectorScreen = findViewById(R.id.cardProjectorScreen);
        imgStatusLed = findViewById(R.id.imgStatusLed);
        imgScreenProjectionIcon = findViewById(R.id.imgScreenProjectionIcon);
        tvProjectorScreenTitle = findViewById(R.id.tvProjectorScreenTitle);
        tvProjectorScreenDesc = findViewById(R.id.tvProjectorScreenDesc);

        tvIrHardwareInfo = findViewById(R.id.tvIrHardwareInfo);
        tvProjectorStatus = findViewById(R.id.tvProjectorStatus);
        cardPowerRing = findViewById(R.id.cardPowerRing);
        btnProjectorPower = findViewById(R.id.btnProjectorPower);
        tvPowerButtonHint = findViewById(R.id.tvPowerButtonHint);
    }

    private void setupIrHardware() {
        irManager = (ConsumerIrManager) getSystemService(Context.CONSUMER_IR_SERVICE);
        if (irManager != null && irManager.hasIrEmitter()) {
            tvIrHardwareInfo.setText(R.string.ir_hardware_ready);
        } else {
            tvIrHardwareInfo.setText(R.string.ir_hardware_simulated);
        }
    }

    private void setupListeners() {
        btnBack.setOnClickListener(v -> finish());

        btnProjectorPower.setOnClickListener(v -> {
            isProjectorOn = !isProjectorOn;
            transmitIrSignal();
            updateProjectorPowerUi();

            if (isProjectorOn) {
                Toast.makeText(this, "IR Signal Sent: Powering ON Projector...", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "IR Signal Sent: Turning OFF / Standby...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void transmitIrSignal() {
        if (irManager != null && irManager.hasIrEmitter()) {
            try {
                irManager.transmit(IR_FREQUENCY, IR_POWER_PATTERN);
            } catch (Exception e) {
                Toast.makeText(this, "IR Signal Transmission Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void updateProjectorPowerUi() {
        if (isProjectorOn) {
            // Screen Projection Card - Active
            cardProjectorScreen.setCardBackgroundColor(Color.parseColor("#1E1B4B")); // Deep Blue/Indigo Screen Glow
            cardProjectorScreen.setStrokeColor(Color.parseColor("#4F46E5"));
            imgStatusLed.setColorFilter(ContextCompat.getColor(this, R.color.power_on));
            imgScreenProjectionIcon.setColorFilter(Color.parseColor("#38BDF8")); // Bright Cyan Icon
            tvProjectorScreenTitle.setText(R.string.projector_screen_on_title);
            tvProjectorScreenDesc.setText(R.string.projector_screen_on_desc);

            // Status Badge & Power Button
            tvProjectorStatus.setText(R.string.projector_status_on);
            tvProjectorStatus.setTextColor(ContextCompat.getColor(this, R.color.power_on));

            int greenColor = ContextCompat.getColor(this, R.color.power_on);
            int greenBg = ContextCompat.getColor(this, R.color.color_active_green_bg);

            cardPowerRing.setCardBackgroundColor(greenBg);
            cardPowerRing.setStrokeColor(greenColor);
            btnProjectorPower.setBackgroundTintList(ColorStateList.valueOf(greenColor));

            tvPowerButtonHint.setText(R.string.hint_power_button_on);
        } else {
            // Screen Projection Card - Standby
            cardProjectorScreen.setCardBackgroundColor(Color.parseColor("#1E293B")); // Dark Slate Screen
            cardProjectorScreen.setStrokeColor(Color.parseColor("#334155"));
            imgStatusLed.setColorFilter(ContextCompat.getColor(this, R.color.power_off));
            imgScreenProjectionIcon.setColorFilter(Color.parseColor("#64748B")); // Inactive Icon
            tvProjectorScreenTitle.setText(R.string.projector_screen_off_title);
            tvProjectorScreenDesc.setText(R.string.projector_screen_off_desc);

            // Status Badge & Power Button
            tvProjectorStatus.setText(R.string.projector_status_off);
            tvProjectorStatus.setTextColor(ContextCompat.getColor(this, R.color.power_off));

            int redColor = ContextCompat.getColor(this, R.color.power_off);
            int redBg = ContextCompat.getColor(this, R.color.power_off_bg);

            cardPowerRing.setCardBackgroundColor(redBg);
            cardPowerRing.setStrokeColor(redColor);
            btnProjectorPower.setBackgroundTintList(ColorStateList.valueOf(redColor));

            tvPowerButtonHint.setText(R.string.hint_power_button_off);
        }
    }
}