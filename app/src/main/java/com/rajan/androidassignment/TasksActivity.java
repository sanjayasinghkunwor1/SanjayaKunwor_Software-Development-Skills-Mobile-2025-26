package com.rajan.androidassignment;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TasksActivity extends AppCompatActivity {

    private ListView listViewTasks;
    private FloatingActionButton fabAddTask;
    private ImageButton btnBack;
    private ArrayList<String> tasks;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        initViews();
        loadTasks();
        setupListeners();
    }

    private void initViews() {
        listViewTasks = findViewById(R.id.listViewTasks);
        fabAddTask = findViewById(R.id.fabAddTask);
        btnBack = findViewById(R.id.btnBack);
    }

    private void loadTasks() {
        tasks = new ArrayList<>();
        
        // Load from SharedPreferences
        Set<String> savedTasks = getSharedPreferences("FocusFlowPrefs", MODE_PRIVATE)
                .getStringSet("task_list", new HashSet<>());
        tasks.addAll(savedTasks);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);
        listViewTasks.setAdapter(adapter);
    }

    private void setupListeners() {
        btnBack.setOnClickListener(v -> finish());

        fabAddTask.setOnClickListener(v -> showAddTaskDialog());

        listViewTasks.setOnItemLongClickListener((parent, view, position, id) -> {
            showDeleteTaskDialog(position);
            return true;
        });
    }

    private void showAddTaskDialog() {
        EditText input = new EditText(this);
        input.setHint("Enter task");
        input.setPadding(50, 30, 50, 30);

        new AlertDialog.Builder(this)
                .setTitle("Add New Task")
                .setView(input)
                .setPositiveButton("Add", (dialog, which) -> {
                    String task = input.getText().toString().trim();
                    if (!task.isEmpty()) {
                        tasks.add(task);
                        adapter.notifyDataSetChanged();
                        saveTasks();
                        Toast.makeText(this, "Task added", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void showDeleteTaskDialog(int position) {
        new AlertDialog.Builder(this)
                .setTitle("Delete Task")
                .setMessage("Remove this task from your list?")
                .setPositiveButton("Delete", (dialog, which) -> {
                    tasks.remove(position);
                    adapter.notifyDataSetChanged();
                    saveTasks();
                    Toast.makeText(this, "Task deleted", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void saveTasks() {
        Set<String> taskSet = new HashSet<>(tasks);
        getSharedPreferences("FocusFlowPrefs", MODE_PRIVATE)
                .edit()
                .putStringSet("task_list", taskSet)
                .apply();
    }
}
