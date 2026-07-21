package com.rajan.androidassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rajan.androidassignment.database.DatabaseHelper;
import com.rajan.androidassignment.models.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesActivity extends AppCompatActivity {

    private ListView listViewNotes;
    private FloatingActionButton fabAddNote;
    private ImageButton btnBack;
    private LinearLayout layoutEmptyState;
    private DatabaseHelper dbHelper;
    private List<Note> notes;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        dbHelper = new DatabaseHelper(this);

        initViews();
        loadNotes();
        setupListeners();
    }

    private void initViews() {
        listViewNotes = findViewById(R.id.listViewNotes);
        fabAddNote = findViewById(R.id.fabAddNote);
        btnBack = findViewById(R.id.btnBack);
        layoutEmptyState = findViewById(R.id.layoutEmptyState);
    }

    private void loadNotes() {
        notes = dbHelper.getAllNotes();
        
        if (notes.isEmpty()) {
            listViewNotes.setVisibility(View.GONE);
            layoutEmptyState.setVisibility(View.VISIBLE);
        } else {
            listViewNotes.setVisibility(View.VISIBLE);
            layoutEmptyState.setVisibility(View.GONE);
            
            ArrayList<String> noteDisplayList = new ArrayList<>();
            for (Note note : notes) {
                noteDisplayList.add(note.getTitle() + "\n" + note.getFormattedDate());
            }
            
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_2, 
                    android.R.id.text1, noteDisplayList);
            listViewNotes.setAdapter(adapter);
        }
    }

    private void setupListeners() {
        btnBack.setOnClickListener(v -> finish());

        fabAddNote.setOnClickListener(v -> showAddNoteDialog());

        listViewNotes.setOnItemClickListener((parent, view, position, id) -> {
            Note note = notes.get(position);
            showEditNoteDialog(note, position);
        });

        listViewNotes.setOnItemLongClickListener((parent, view, position, id) -> {
            showDeleteNoteDialog(position);
            return true;
        });
    }

    private void showAddNoteDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_note, null);
        EditText etTitle = dialogView.findViewById(R.id.etNoteTitle);
        EditText etContent = dialogView.findViewById(R.id.etNoteContent);

        new AlertDialog.Builder(this)
                .setTitle("New Note")
                .setView(dialogView)
                .setPositiveButton("Save", (dialog, which) -> {
                    String title = etTitle.getText().toString().trim();
                    String content = etContent.getText().toString().trim();
                    
                    if (!title.isEmpty()) {
                        Note note = new Note(title, content);
                        dbHelper.insertNote(note);
                        loadNotes();
                        Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void showEditNoteDialog(Note note, int position) {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_note, null);
        EditText etTitle = dialogView.findViewById(R.id.etNoteTitle);
        EditText etContent = dialogView.findViewById(R.id.etNoteContent);
        
        etTitle.setText(note.getTitle());
        etContent.setText(note.getContent());

        new AlertDialog.Builder(this)
                .setTitle("Edit Note")
                .setView(dialogView)
                .setPositiveButton("Update", (dialog, which) -> {
                    String title = etTitle.getText().toString().trim();
                    String content = etContent.getText().toString().trim();
                    
                    if (!title.isEmpty()) {
                        note.setTitle(title);
                        note.setContent(content);
                        dbHelper.updateNote(note);
                        loadNotes();
                        Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void showDeleteNoteDialog(int position) {
        Note note = notes.get(position);
        
        new AlertDialog.Builder(this)
                .setTitle("Delete Note")
                .setMessage("Are you sure you want to delete this note?")
                .setPositiveButton("Delete", (dialog, which) -> {
                    dbHelper.deleteNote(note.getId());
                    loadNotes();
                    Toast.makeText(this, "Note deleted", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
