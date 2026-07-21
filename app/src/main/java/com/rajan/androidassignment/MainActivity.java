package com.rajan.androidassignment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rajan.androidassignment.database.DatabaseHelper;
import com.rajan.androidassignment.models.FocusSession;

public class MainActivity extends AppCompatActivity {

    private TextView tvTimer, tvMode, tvSessionCount;
    private Button btnPomodoro, btnShortBreak, btnLongBreak;
    private FloatingActionButton fabStartPause, fabReset;
    private BottomNavigationView bottomNavigation;
    private LinearLayout btnQuickTasks, btnQuickNotes, btnQuickTips;
    
    private DatabaseHelper dbHelper;
    private SharedPreferences prefs;
    
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 25 * 60 * 1000; // 25 minutes default
    private boolean isTimerRunning = false;
    private boolean isBreakMode = false;
    private int sessionCount = 0;
    private int maxSessions = 4;
    
    private static final long POMODORO_TIME = 25 * 60 * 1000;
    private static final long SHORT_BREAK_TIME = 5 * 60 * 1000;
    private static final long LONG_BREAK_TIME = 15 * 60 * 1000;
    
    private long currentSessionTime = POMODORO_TIME;
    private FocusSession currentSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        prefs = getSharedPreferences("FocusFlowPrefs", MODE_PRIVATE);

        // Seed sample data on first launch
        seedSampleDataIfNeeded();

        initViews();
        setupListeners();
        updateTimerDisplay();
    }

    private void initViews() {
        tvTimer = findViewById(R.id.tvTimer);
        tvMode = findViewById(R.id.tvMode);
        tvSessionCount = findViewById(R.id.tvSessionCount);
        btnPomodoro = findViewById(R.id.btnPomodoro);
        btnShortBreak = findViewById(R.id.btnShortBreak);
        btnLongBreak = findViewById(R.id.btnLongBreak);
        fabStartPause = findViewById(R.id.fabStartPause);
        fabReset = findViewById(R.id.fabReset);
        bottomNavigation = findViewById(R.id.bottomNavigation);
        btnQuickTasks = findViewById(R.id.btnQuickTasks);
        btnQuickNotes = findViewById(R.id.btnQuickNotes);
        btnQuickTips = findViewById(R.id.btnQuickTips);
        
        bottomNavigation.setSelectedItemId(R.id.nav_timer);
    }

    private void setupListeners() {
        btnPomodoro.setOnClickListener(v -> selectMode(POMODORO_TIME, "Pomodoro"));
        btnShortBreak.setOnClickListener(v -> selectMode(SHORT_BREAK_TIME, "Short Break"));
        btnLongBreak.setOnClickListener(v -> selectMode(LONG_BREAK_TIME, "Long Break"));
        
        fabStartPause.setOnClickListener(v -> {
            if (isTimerRunning) {
                pauseTimer();
            } else {
                startTimer();
            }
        });
        
        fabReset.setOnClickListener(v -> resetTimer());
        
        btnQuickTasks.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, TasksActivity.class)));
        btnQuickNotes.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, NotesActivity.class)));
        btnQuickTips.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, TipsActivity.class)));
        
        bottomNavigation.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_timer) {
                    return true;
                } else if (itemId == R.id.nav_history) {
                    startActivity(new Intent(MainActivity.this, HistoryActivity.class));
                    return true;
                } else if (itemId == R.id.nav_stats) {
                    startActivity(new Intent(MainActivity.this, StatisticsActivity.class));
                    return true;
                } else if (itemId == R.id.nav_settings) {
                    startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                    return true;
                }
                return false;
            }
        });
    }
    
    private void selectMode(long time, String mode) {
        if (isTimerRunning) {
            pauseTimer();
        }
        
        // Show custom session type dialog
        showSessionTypeDialog(time, mode);
    }
    
    private void showSessionTypeDialog(long time, String mode) {
        String[] sessionTypes = {"Study", "Reading", "Music Practice", "Exercise", "Break", "Meditation", "Custom"};
        
        new androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Select Activity Type")
                .setItems(sessionTypes, (dialog, which) -> {
                    String selectedType = sessionTypes[which];
                    currentSessionTime = time;
                    timeLeftInMillis = time;
                    isBreakMode = mode.contains("Break");
                    tvMode.setText(selectedType);
                    updateTimerDisplay();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
    
    private void startTimer() {
        if (currentSession == null) {
            currentSession = new FocusSession();
            currentSession.setStartTime(System.currentTimeMillis());
            currentSession.setFocusMinutes((int)(currentSessionTime / 60000));
            currentSession.setSessionType(tvMode.getText().toString());
        }
        
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimerDisplay();
            }

            @Override
            public void onFinish() {
                onTimerComplete();
            }
        }.start();

        isTimerRunning = true;
        fabStartPause.setImageResource(android.R.drawable.ic_media_pause);
    }

    private void pauseTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        isTimerRunning = false;
        fabStartPause.setImageResource(android.R.drawable.ic_media_play);
    }

    private void resetTimer() {
        pauseTimer();
        timeLeftInMillis = currentSessionTime;
        currentSession = null;
        updateTimerDisplay();
    }
    
    private void onTimerComplete() {
        // Save session
        if (currentSession != null && !isBreakMode) {
            currentSession.setEndTime(System.currentTimeMillis());
            currentSession.setActualFocusMinutes((int)(currentSessionTime / 60000));
            currentSession.setCompleted(true);
            dbHelper.insertSession(currentSession);
            
            sessionCount++;
            tvSessionCount.setText(sessionCount + "/" + maxSessions);
        }
        
        // Auto switch to break or focus
        if (!isBreakMode) {
            // Switch to break
            if (sessionCount % 4 == 0) {
                selectMode(LONG_BREAK_TIME, "Long Break");
            } else {
                selectMode(SHORT_BREAK_TIME, "Short Break");
            }
        } else {
            // Switch back to focus
            selectMode(POMODORO_TIME, "Pomodoro");
        }
        
        currentSession = null;
        isTimerRunning = false;
        fabStartPause.setImageResource(android.R.drawable.ic_media_play);
    }

    private void updateTimerDisplay() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        tvTimer.setText(String.format("%02d:%02d", minutes, seconds));
        tvSessionCount.setText(sessionCount + "/" + maxSessions);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    private void seedSampleDataIfNeeded() {
        boolean isSeeded = prefs.getBoolean("data_seeded", false);
        if (isSeeded) return;

        long currentTime = System.currentTimeMillis();
        long oneDay = 24 * 60 * 60 * 1000L;
        long oneHour = 60 * 60 * 1000L;

        // Create 12 sample sessions
        for (int i = 0; i < 12; i++) {
            FocusSession session = new FocusSession();
            session.setStartTime(currentTime - (i * oneDay / 2) - (i * oneHour));
            session.setEndTime(session.getStartTime() + (25 * 60 * 1000));
            session.setFocusMinutes(25);
            session.setBreakMinutes(5);
            session.setActualFocusMinutes(25);
            session.setDistractionsCount(i % 3);
            session.setSessionType("Pomodoro");
            session.setCompleted(true);
            dbHelper.insertSession(session);
        }

        prefs.edit().putInt("current_streak", 5).apply();
        prefs.edit().putBoolean("data_seeded", true).apply();
    }
}