package com.rajan.androidassignment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.rajan.androidassignment.adapters.SessionAdapter;
import com.rajan.androidassignment.database.DatabaseHelper;
import com.rajan.androidassignment.models.FocusSession;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private ListView listViewSessions;
    private LinearLayout layoutEmptyState;
    private ImageButton btnBack;
    private DatabaseHelper dbHelper;
    private SessionAdapter adapter;
    private List<FocusSession> sessions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        dbHelper = new DatabaseHelper(this);

        initViews();
        setupListeners();
        loadSessions();
    }

    private void initViews() {
        listViewSessions = findViewById(R.id.listViewSessions);
        layoutEmptyState = findViewById(R.id.layoutEmptyState);
        btnBack = findViewById(R.id.btnBack);
    }

    private void setupListeners() {
        btnBack.setOnClickListener(v -> finish());
        
        // Long press to delete session
        listViewSessions.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showDeleteDialog(position);
                return true;
            }
        });
    }

    private void loadSessions() {
        sessions = dbHelper.getAllSessions();

        if (sessions.isEmpty()) {
            listViewSessions.setVisibility(View.GONE);
            layoutEmptyState.setVisibility(View.VISIBLE);
        } else {
            listViewSessions.setVisibility(View.VISIBLE);
            layoutEmptyState.setVisibility(View.GONE);

            adapter = new SessionAdapter(this, sessions);
            listViewSessions.setAdapter(adapter);
        }
    }
    
    private void showDeleteDialog(int position) {
        FocusSession session = sessions.get(position);
        
        new AlertDialog.Builder(this)
                .setTitle("Delete Session")
                .setMessage("Are you sure you want to delete this session?")
                .setPositiveButton("Delete", (dialog, which) -> {
                    dbHelper.deleteSession(session.getId());
                    sessions.remove(position);
                    adapter.notifyDataSetChanged();
                    
                    if (sessions.isEmpty()) {
                        listViewSessions.setVisibility(View.GONE);
                        layoutEmptyState.setVisibility(View.VISIBLE);
                    }
                    
                    Toast.makeText(this, "Session deleted", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}