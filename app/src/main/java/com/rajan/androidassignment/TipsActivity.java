package com.rajan.androidassignment;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

public class TipsActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private LinearLayout layoutTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        initViews();
        setupListeners();
        loadTips();
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        layoutTips = findViewById(R.id.layoutTips);
    }

    private void setupListeners() {
        btnBack.setOnClickListener(v -> finish());
    }

    private void loadTips() {
        String[][] tips = {
            {"Pomodoro Technique", "Work for 25 minutes, then take a 5-minute break. After 4 sessions, take a longer 15-30 minute break. This keeps your mind fresh and focused."},
            {"Remove Distractions", "Put your phone on silent, close unnecessary tabs, and let others know you're studying. A distraction-free environment helps you focus better."},
            {"Use Active Recall", "Instead of re-reading notes, test yourself. Close your book and try to remember what you learned. This strengthens memory retention."},
            {"Take Smart Breaks", "During breaks, avoid screens. Walk around, stretch, drink water, or do light exercises. This helps your brain recharge effectively."},
            {"Study in Chunks", "Break large topics into smaller sections. Learn one chunk at a time rather than cramming everything. This makes learning more manageable."},
            {"Sleep Well", "Get 7-8 hours of sleep. Your brain consolidates information during sleep. All-nighters hurt more than they help."},
            {"Stay Hydrated", "Drink water regularly while studying. Even mild dehydration can affect concentration and cognitive performance."},
            {"Set Clear Goals", "Before each session, write down exactly what you want to accomplish. Clear goals help you stay focused and measure progress."},
            {"Use the 2-Minute Rule", "If a task takes less than 2 minutes, do it immediately. This prevents small tasks from piling up and overwhelming you."},
            {"Teach Someone Else", "Explain concepts to others (or even to yourself out loud). If you can teach it, you truly understand it."}
        };

        for (String[] tip : tips) {
            addTipCard(tip[0], tip[1]);
        }
    }

    private void addTipCard(String title, String content) {
        MaterialCardView card = new MaterialCardView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 0, 0, 32);
        card.setLayoutParams(params);
        card.setRadius(16);
        card.setCardElevation(4);
        card.setContentPadding(40, 40, 40, 40);

        LinearLayout cardContent = new LinearLayout(this);
        cardContent.setOrientation(LinearLayout.VERTICAL);

        TextView titleView = new TextView(this);
        titleView.setText(title);
        titleView.setTextSize(18);
        titleView.setTextColor(getResources().getColor(R.color.primary, null));
        titleView.setTypeface(null, android.graphics.Typeface.BOLD);

        TextView contentView = new TextView(this);
        contentView.setText(content);
        contentView.setTextSize(14);
        contentView.setTextColor(getResources().getColor(R.color.text_secondary, null));
        contentView.setLineSpacing(8, 1);
        LinearLayout.LayoutParams contentParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        contentParams.setMargins(0, 20, 0, 0);
        contentView.setLayoutParams(contentParams);

        cardContent.addView(titleView);
        cardContent.addView(contentView);
        card.addView(cardContent);

        layoutTips.addView(card);
    }
}
