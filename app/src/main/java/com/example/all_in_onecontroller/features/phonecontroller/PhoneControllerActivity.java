package com.example.all_in_onecontroller.features.phonecontroller;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.all_in_onecontroller.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.materialswitch.MaterialSwitch;

import java.util.Objects;

public class PhoneControllerActivity extends AppCompatActivity {

    private ImageButton btnBack;

    // Camera Controller
    private MaterialButton btnOpenCamera;

    // Flashlight Controller
    private MaterialSwitch switchFlashlight;
    private TextView tvFlashlightStatus;
    private ImageView imgFlashlightIcon;
    private CameraManager cameraManager;
    private String cameraId;
    private boolean isFlashlightOn = false;
    private CameraManager.TorchCallback torchCallback;

    // Wi-Fi Controller
    private MaterialSwitch switchWifi;
    private TextView tvWifiStatus;
    private ImageView imgWifiIcon;
    private WifiManager wifiManager;
    private BroadcastReceiver wifiReceiver;

    // Bluetooth Controller
    private MaterialSwitch switchBluetooth;
    private TextView tvBluetoothStatus;
    private ImageView imgBluetoothIcon;
    private BluetoothAdapter bluetoothAdapter;
    private BroadcastReceiver bluetoothReceiver;
    private boolean isUpdatingBluetoothUi = false;

    // Permission Launchers
    private ActivityResultLauncher<String> bluetoothPermissionLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_phone_controller);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.phoneMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initPermissionLaunchers();
        initViews();
        setupBackButton();
        setupCameraController();
        setupFlashlightController();
        setupWifiController();
        setupBluetoothController();
    }

    private void initPermissionLaunchers() {
        bluetoothPermissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (isGranted) {
                        toggleBluetooth(true);
                    } else {
                        Toast.makeText(this, R.string.bluetooth_permission_needed, Toast.LENGTH_SHORT).show();
                        updateBluetoothUi(false);
                    }
                }
        );
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);

        btnOpenCamera = findViewById(R.id.btnOpenCamera);

        switchFlashlight = findViewById(R.id.switchFlashlight);
        tvFlashlightStatus = findViewById(R.id.tvFlashlightStatus);
        imgFlashlightIcon = findViewById(R.id.imgFlashlightIcon);

        switchWifi = findViewById(R.id.switchWifi);
        tvWifiStatus = findViewById(R.id.tvWifiStatus);
        imgWifiIcon = findViewById(R.id.imgWifiIcon);

        switchBluetooth = findViewById(R.id.switchBluetooth);
        tvBluetoothStatus = findViewById(R.id.tvBluetoothStatus);
        imgBluetoothIcon = findViewById(R.id.imgBluetoothIcon);
    }

    private void setupBackButton() {
        btnBack.setOnClickListener(v -> finish());
    }

    // ==========================================
    // 1. CAMERA CONTROLLER
    // ==========================================
    private void setupCameraController() {
        btnOpenCamera.setOnClickListener(v -> {
            try {
                Intent cameraIntent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
                startActivity(cameraIntent);
            } catch (Exception e) {
                try {
                    Intent fallbackIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivity(fallbackIntent);
                } catch (Exception ex) {
                    Toast.makeText(this, "Unable to open phone camera app", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // ==========================================
    // 2. FLASHLIGHT CONTROLLER
    // ==========================================
    private void setupFlashlightController() {
        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            if (cameraManager != null && cameraManager.getCameraIdList().length > 0) {
                cameraId = cameraManager.getCameraIdList()[0];
            }
        } catch (CameraAccessException ignored) {
        }

        torchCallback = new CameraManager.TorchCallback() {
            @Override
            public void onTorchModeChanged(@NonNull String id, boolean enabled) {
                if (Objects.equals(id, cameraId)) {
                    isFlashlightOn = enabled;
                    updateFlashlightUi(enabled);
                }
            }
        };

        if (cameraManager != null) {
            cameraManager.registerTorchCallback(torchCallback, null);
        }

        switchFlashlight.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked != isFlashlightOn) {
                setFlashlightEnabled(isChecked);
            }
        });
    }

    private void setFlashlightEnabled(boolean enabled) {
        if (cameraManager != null && cameraId != null) {
            try {
                cameraManager.setTorchMode(cameraId, enabled);
                isFlashlightOn = enabled;
                updateFlashlightUi(enabled);
            } catch (CameraAccessException e) {
                Toast.makeText(this, "Flashlight unavailable: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                updateFlashlightUi(false);
            }
        } else {
            Toast.makeText(this, "No flashlight available on device", Toast.LENGTH_SHORT).show();
            updateFlashlightUi(false);
        }
    }

    private void updateFlashlightUi(boolean enabled) {
        switchFlashlight.setChecked(enabled);
        if (enabled) {
            tvFlashlightStatus.setText(R.string.flashlight_status_on);
            imgFlashlightIcon.setColorFilter(ContextCompat.getColor(this, R.color.color_active_amber));
        } else {
            tvFlashlightStatus.setText(R.string.flashlight_status_off);
            imgFlashlightIcon.setColorFilter(ContextCompat.getColor(this, R.color.color_inactive));
        }
    }

    // ==========================================
    // 3. WI-FI CONTROLLER
    // ==========================================
    private void setupWifiController() {
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        wifiReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) {
                    int state = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);
                    boolean isEnabled = (state == WifiManager.WIFI_STATE_ENABLED || state == WifiManager.WIFI_STATE_ENABLING);
                    updateWifiUi(isEnabled);
                }
            }
        };

        switchWifi.setOnClickListener(v -> {
            if (wifiManager != null) {
                updateWifiUi(wifiManager.isWifiEnabled());

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    Intent panelIntent = new Intent(Settings.Panel.ACTION_WIFI);
                    try {
                        startActivity(panelIntent);
                    } catch (Exception e) {
                        Intent settingsIntent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                        startActivity(settingsIntent);
                    }
                } else {
                    try {
                        @SuppressWarnings("deprecation")
                        boolean success = wifiManager.setWifiEnabled(!wifiManager.isWifiEnabled());
                        if (!success) {
                            Intent settingsIntent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                            startActivity(settingsIntent);
                        }
                    } catch (Exception e) {
                        Intent settingsIntent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                        startActivity(settingsIntent);
                    }
                }
            }
        });

        if (wifiManager != null) {
            updateWifiUi(wifiManager.isWifiEnabled());
        }
    }

    private void updateWifiUi(boolean isEnabled) {
        switchWifi.setChecked(isEnabled);
        if (isEnabled) {
            tvWifiStatus.setText(R.string.wifi_status_on);
            imgWifiIcon.setColorFilter(ContextCompat.getColor(this, R.color.color_active_blue));
        } else {
            tvWifiStatus.setText(R.string.wifi_status_off);
            imgWifiIcon.setColorFilter(ContextCompat.getColor(this, R.color.color_inactive));
        }
    }

    // ==========================================
    // 4. BLUETOOTH CONTROLLER
    // ==========================================
    private void setupBluetoothController() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        bluetoothReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(intent.getAction())) {
                    int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
                    boolean isEnabled = (state == BluetoothAdapter.STATE_ON || state == BluetoothAdapter.STATE_TURNING_ON);
                    updateBluetoothUi(isEnabled);
                }
            }
        };

        switchBluetooth.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isUpdatingBluetoothUi) return;

            if (bluetoothAdapter == null) {
                Toast.makeText(this, "Bluetooth not supported on this device", Toast.LENGTH_SHORT).show();
                updateBluetoothUi(false);
                return;
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT)
                        != PackageManager.PERMISSION_GRANTED) {
                    bluetoothPermissionLauncher.launch(Manifest.permission.BLUETOOTH_CONNECT);
                    return;
                }
            }

            toggleBluetooth(isChecked);
        });

        if (bluetoothAdapter != null) {
            updateBluetoothUi(bluetoothAdapter.isEnabled());
        } else {
            switchBluetooth.setEnabled(false);
            tvBluetoothStatus.setText(R.string.bluetooth_unavailable);
        }
    }

    @SuppressWarnings("MissingPermission")
    private void toggleBluetooth(boolean enable) {
        if (bluetoothAdapter == null) return;

        if (enable) {
            if (!bluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivity(enableBtIntent);
            }
        } else {
            if (bluetoothAdapter.isEnabled()) {
                bluetoothAdapter.disable();
            }
        }
    }

    private void updateBluetoothUi(boolean isEnabled) {
        isUpdatingBluetoothUi = true;
        switchBluetooth.setChecked(isEnabled);
        if (isEnabled) {
            tvBluetoothStatus.setText(R.string.bluetooth_status_on);
            imgBluetoothIcon.setColorFilter(ContextCompat.getColor(this, R.color.color_active_blue));
        } else {
            tvBluetoothStatus.setText(R.string.bluetooth_status_off);
            imgBluetoothIcon.setColorFilter(ContextCompat.getColor(this, R.color.color_inactive));
        }
        isUpdatingBluetoothUi = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (wifiManager != null) {
            updateWifiUi(wifiManager.isWifiEnabled());
        }
        if (bluetoothAdapter != null) {
            updateBluetoothUi(bluetoothAdapter.isEnabled());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (wifiReceiver != null) {
            registerReceiver(wifiReceiver, new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION));
        }
        if (bluetoothReceiver != null) {
            registerReceiver(bluetoothReceiver, new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED));
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (wifiReceiver != null) {
            try {
                unregisterReceiver(wifiReceiver);
            } catch (IllegalArgumentException ignored) {}
        }
        if (bluetoothReceiver != null) {
            try {
                unregisterReceiver(bluetoothReceiver);
            } catch (IllegalArgumentException ignored) {}
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cameraManager != null && torchCallback != null) {
            cameraManager.unregisterTorchCallback(torchCallback);
        }
    }
}