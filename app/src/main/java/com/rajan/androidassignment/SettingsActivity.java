package com.rajan.androidassignment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.google.android.material.button.MaterialButton;
import com.rajan.androidassignment.database.DatabaseHelper;

public class SettingsActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private SwitchCompat switchKeepScreenOn, switchSound, switchVibration, switchAutoBreak;
    private TextView tvDailyGoal;
    private MaterialButton btnDecreaseGoal, btnIncreaseGoal, btnResetStats;
    
    private SharedPreferences prefs;
    private DatabaseHelper dbHelper;
    private int dailyGoalHours = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        prefs = getSharedPreferences("FocusFlowPrefs", MODE_PRIVATE);
        dbHelper = new DatabaseHelper(this);

        initViews();
        loadSettings();
        setupListeners();
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        switchKeepScreenOn = findViewById(R.id.switchKeepScreenOn);
        switchSound = findViewById(R.id.switchSound);
        switchVibration = findViewById(R.id.switchVibration);
        switchAutoBreak = findViewById(R.id.switchAutoBreak);
        tvDailyGoal = findViewById(R.id.tvDailyGoal);
        btnDecreaseGoal = findViewById(R.id.btnDecreaseGoal);
        btnIncreaseGoal = findViewById(R.id.btnIncreaseGoal);
        btnResetStats = findViewById(R.id.btnResetStats);
    }

    private void loadSettings() {
        // Load saved settings
        switchKeepScreenOn.setChecked(prefs.getBoolean("keep_screen_on", true));
        switchSound.setChecked(prefs.getBoolean("sound_enabled", true));
        switchVibration.setChecked(prefs.getBoolean("vibration_enabled", true));
        switchAutoBreak.setChecked(prefs.getBoolean("auto_break", false));
        dailyGoalHours = prefs.getInt("daily_goal_hours", 2);
        updateGoalDisplay();
    }

    private void setupListeners() {
        btnBack.setOnClickListener(v -> finish());

        switchKeepScreenOn.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prefs.edit().putBoolean("keep_screen_on", isChecked).apply();
            Toast.makeText(this, "Setting saved", Toast.LENGTH_SHORT).show();
        });

        switchSound.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prefs.edit().putBoolean("sound_enabled", isChecked).apply();
            Toast.makeText(this, "Setting saved", Toast.LENGTH_SHORT).show();
        });

        switchVibration.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prefs.edit().putBoolean("vibration_enabled", isChecked).apply();
            Toast.makeText(this, "Setting saved", Toast.LENGTH_SHORT).show();
        });

        switchAutoBreak.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prefs.edit().putBoolean("auto_break", isChecked).apply();
            Toast.makeText(this, "Setting saved", Toast.LENGTH_SHORT).show();
        });

        btnDecreaseGoal.setOnClickListener(v -> {
            if (dailyGoalHours > 1) {
                dailyGoalHours--;
                updateGoalDisplay();
                saveGoal();
            }
        });

        btnIncreaseGoal.setOnClickListener(v -> {
            if (dailyGoalHours < 12) {
                dailyGoalHours++;
                updateGoalDisplay();
                saveGoal();
            }
        });

        btnResetStats.setOnClickListener(v -> showResetConfirmation());
    }

    private void updateGoalDisplay() {
        tvDailyGoal.setText(dailyGoalHours + " hours");
    }

    private void saveGoal() {
        prefs.edit().putInt("daily_goal_hours", dailyGoalHours).apply();
        Toast.makeText(this, "Daily goal updated", Toast.LENGTH_SHORT).show();
    }

    private void showResetConfirmation() {
        new AlertDialog.Builder(this)
                .setTitle("Reset Statistics?")
                .setMessage("This will permanently delete all your session history and statistics. This action cannot be undone.")
                .setPositiveButton("Reset", (dialog, which) -> resetAllData())
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void resetAllData() {
        // Clear database
        getApplicationContext().deleteDatabase("focusflow.db");
        
        // Clear preferences
        prefs.edit()
                .putBoolean("data_seeded", false)
                .putInt("current_streak", 0)
                .apply();

        Toast.makeText(this, "All statistics reset", Toast.LENGTH_LONG).show();
        finish();
    }
}
