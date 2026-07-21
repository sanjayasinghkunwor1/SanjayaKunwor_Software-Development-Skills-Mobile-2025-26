package com.rajan.androidassignment;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rajan.androidassignment.database.DatabaseHelper;

import java.text.DecimalFormat;

public class StatisticsActivity extends AppCompatActivity {

    private TextView tvTotalTime, tvSessionsCount, tvAverageSession, tvTotalDistractions, tvInsights;
    private ImageButton btnBack;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        dbHelper = new DatabaseHelper(this);

        initViews();
        setupListeners();
        loadStatistics();
    }

    private void initViews() {
        tvTotalTime = findViewById(R.id.tvTotalTime);
        tvSessionsCount = findViewById(R.id.tvSessionsCount);
        tvAverageSession = findViewById(R.id.tvAverageSession);
        tvTotalDistractions = findViewById(R.id.tvTotalDistractions);
        tvInsights = findViewById(R.id.tvInsights);
        btnBack = findViewById(R.id.btnBack);
    }

    private void setupListeners() {
        btnBack.setOnClickListener(v -> finish());
    }

    private void loadStatistics() {
        // Total focus time
        int totalMinutes = dbHelper.getTotalFocusTime();
        double totalHours = totalMinutes / 60.0;
        DecimalFormat df = new DecimalFormat("0.0");
        tvTotalTime.setText(df.format(totalHours) + " hrs");

        // Total sessions
        int totalSessions = dbHelper.getTotalSessionsCount();
        tvSessionsCount.setText(String.valueOf(totalSessions));

        // Average session
        int averageMinutes = 0;
        if (totalSessions > 0) {
            averageMinutes = totalMinutes / totalSessions;
        }
        tvAverageSession.setText(averageMinutes + " min");

        // Total distractions
        int totalDistractions = dbHelper.getTotalDistractionsCount();
        tvTotalDistractions.setText(String.valueOf(totalDistractions));

        // Generate insights
        generateInsights(totalSessions, totalMinutes, totalDistractions);
    }

    private void generateInsights(int sessions, int minutes, int distractions) {
        StringBuilder insights = new StringBuilder();

        if (sessions == 0) {
            insights.append("Complete your first session to see insights!");
        } else {
            insights.append("You've completed ").append(sessions).append(" focus session");
            if (sessions > 1) insights.append("s");
            insights.append("!\n\n");

            double hours = minutes / 60.0;
            DecimalFormat df = new DecimalFormat("0.0");
            insights.append("Total focus time: ").append(df.format(hours)).append(" hours\n\n");

            if (sessions > 0) {
                double avgDistractions = (double) distractions / sessions;
                DecimalFormat df2 = new DecimalFormat("0.0");
                insights.append("Average distractions per session: ").append(df2.format(avgDistractions)).append("\n\n");
            }

            // Motivational message based on performance
            if (distractions == 0) {
                insights.append("Perfect focus! You had zero distractions!");
            } else if (sessions >= 10) {
                insights.append("Great consistency! Keep up the excellent work!");
            } else if (sessions >= 5) {
                insights.append("You're building momentum! Keep going!");
            } else {
                insights.append("Great start! Every session makes you stronger!");
            }
        }

        tvInsights.setText(insights.toString());
    }
}