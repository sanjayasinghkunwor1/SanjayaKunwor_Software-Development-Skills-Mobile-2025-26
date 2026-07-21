package com.rajan.androidassignment.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rajan.androidassignment.models.FocusSession;
import com.rajan.androidassignment.models.Note;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "focusflow.db";
    private static final int DATABASE_VERSION = 2;

    // Sessions table
    private static final String TABLE_SESSIONS = "sessions";
    private static final String COL_ID = "id";
    private static final String COL_START_TIME = "start_time";
    private static final String COL_END_TIME = "end_time";
    private static final String COL_FOCUS_MINUTES = "focus_minutes";
    private static final String COL_BREAK_MINUTES = "break_minutes";
    private static final String COL_ACTUAL_FOCUS = "actual_focus_minutes";
    private static final String COL_DISTRACTIONS = "distractions_count";
    private static final String COL_SESSION_TYPE = "session_type";
    private static final String COL_COMPLETED = "completed";
    
    // Notes table
    private static final String TABLE_NOTES = "notes";
    private static final String COL_NOTE_ID = "note_id";
    private static final String COL_NOTE_TITLE = "title";
    private static final String COL_NOTE_CONTENT = "content";
    private static final String COL_NOTE_TIMESTAMP = "timestamp";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_SESSIONS + " ("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_START_TIME + " INTEGER, "
                + COL_END_TIME + " INTEGER, "
                + COL_FOCUS_MINUTES + " INTEGER, "
                + COL_BREAK_MINUTES + " INTEGER, "
                + COL_ACTUAL_FOCUS + " INTEGER, "
                + COL_DISTRACTIONS + " INTEGER, "
                + COL_SESSION_TYPE + " TEXT, "
                + COL_COMPLETED + " INTEGER)";
        db.execSQL(createTable);
        
        String createNotesTable = "CREATE TABLE " + TABLE_NOTES + " ("
                + COL_NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NOTE_TITLE + " TEXT, "
                + COL_NOTE_CONTENT + " TEXT, "
                + COL_NOTE_TIMESTAMP + " INTEGER)";
        db.execSQL(createNotesTable);
        
        // Seed dummy notes
        seedDummyNotes(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SESSIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        onCreate(db);
    }
    
    private void seedDummyNotes(SQLiteDatabase db) {
        String[][] dummyNotes = {
            {"Java Study Notes", "Remember: Classes are blueprints for objects. Inheritance allows code reuse. Polymorphism enables flexibility."},
            {"Math Formulas", "Quadratic formula: x = (-b ± √(b²-4ac)) / 2a\nPythagorean theorem: a² + b² = c²"},
            {"English Essay Ideas", "Thesis: Technology has both positive and negative impacts on society. Need 3 supporting arguments and counter-argument."},
            {"Chemistry Revision", "Periodic table groups: Noble gases (Group 18), Halogens (Group 17), Alkali metals (Group 1)"},
            {"History Key Dates", "1776 - American Independence\n1789 - French Revolution\n1947 - Indian Independence"}
        };
        
        long baseTime = System.currentTimeMillis() - (5 * 24 * 60 * 60 * 1000L); // 5 days ago
        
        for (int i = 0; i < dummyNotes.length; i++) {
            ContentValues values = new ContentValues();
            values.put(COL_NOTE_TITLE, dummyNotes[i][0]);
            values.put(COL_NOTE_CONTENT, dummyNotes[i][1]);
            values.put(COL_NOTE_TIMESTAMP, baseTime + (i * 24 * 60 * 60 * 1000L));
            db.insert(TABLE_NOTES, null, values);
        }
    }

    // Insert a new session
    public long insertSession(FocusSession session) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_START_TIME, session.getStartTime());
        values.put(COL_END_TIME, session.getEndTime());
        values.put(COL_FOCUS_MINUTES, session.getFocusMinutes());
        values.put(COL_BREAK_MINUTES, session.getBreakMinutes());
        values.put(COL_ACTUAL_FOCUS, session.getActualFocusMinutes());
        values.put(COL_DISTRACTIONS, session.getDistractionsCount());
        values.put(COL_SESSION_TYPE, session.getSessionType());
        values.put(COL_COMPLETED, session.isCompleted() ? 1 : 0);
        
        long id = db.insert(TABLE_SESSIONS, null, values);
        db.close();
        return id;
    }

    // Update session
    public int updateSession(FocusSession session) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_END_TIME, session.getEndTime());
        values.put(COL_ACTUAL_FOCUS, session.getActualFocusMinutes());
        values.put(COL_DISTRACTIONS, session.getDistractionsCount());
        values.put(COL_COMPLETED, session.isCompleted() ? 1 : 0);
        
        int rows = db.update(TABLE_SESSIONS, values, COL_ID + "=?",
                new String[]{String.valueOf(session.getId())});
        db.close();
        return rows;
    }

    // Get all sessions
    public List<FocusSession> getAllSessions() {
        List<FocusSession> sessions = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_SESSIONS + 
                " ORDER BY " + COL_START_TIME + " DESC", null);

        if (cursor.moveToFirst()) {
            do {
                FocusSession session = cursorToSession(cursor);
                sessions.add(session);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return sessions;
    }

    // Get sessions for today
    public List<FocusSession> getTodaySessions() {
        List<FocusSession> sessions = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        long startOfDay = calendar.getTimeInMillis();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_SESSIONS +
                " WHERE " + COL_START_TIME + " >= ? ORDER BY " + COL_START_TIME + " DESC",
                new String[]{String.valueOf(startOfDay)});

        if (cursor.moveToFirst()) {
            do {
                FocusSession session = cursorToSession(cursor);
                sessions.add(session);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return sessions;
    }

    // Get total focus time (in minutes)
    public int getTotalFocusTime() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(" + COL_ACTUAL_FOCUS + ") FROM " + 
                TABLE_SESSIONS + " WHERE " + COL_COMPLETED + " = 1", null);
        int total = 0;
        if (cursor.moveToFirst()) {
            total = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return total;
    }

    // Get total completed sessions count
    public int getTotalSessionsCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_SESSIONS +
                " WHERE " + COL_COMPLETED + " = 1", null);
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return count;
    }

    // Get total distractions count
    public int getTotalDistractionsCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(" + COL_DISTRACTIONS + ") FROM " + 
                TABLE_SESSIONS, null);
        int total = 0;
        if (cursor.moveToFirst()) {
            total = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return total;
    }

    // Check if session exists after specific time (for night owl/early bird achievements)
    public boolean hasSessionAfterTime(int hourOfDay) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_SESSIONS + 
                " WHERE " + COL_COMPLETED + " = 1", null);
        
        boolean found = false;
        if (cursor.moveToFirst()) {
            do {
                long startTime = cursor.getLong(cursor.getColumnIndexOrThrow(COL_START_TIME));
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(startTime);
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                if (hour >= hourOfDay) {
                    found = true;
                    break;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return found;
    }

    // Check if session exists before specific time
    public boolean hasSessionBeforeTime(int hourOfDay) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_SESSIONS + 
                " WHERE " + COL_COMPLETED + " = 1", null);
        
        boolean found = false;
        if (cursor.moveToFirst()) {
            do {
                long startTime = cursor.getLong(cursor.getColumnIndexOrThrow(COL_START_TIME));
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(startTime);
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                if (hour < hourOfDay) {
                    found = true;
                    break;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return found;
    }

    // Helper method to convert cursor to FocusSession object
    private FocusSession cursorToSession(Cursor cursor) {
        FocusSession session = new FocusSession();
        session.setId(cursor.getLong(cursor.getColumnIndexOrThrow(COL_ID)));
        session.setStartTime(cursor.getLong(cursor.getColumnIndexOrThrow(COL_START_TIME)));
        session.setEndTime(cursor.getLong(cursor.getColumnIndexOrThrow(COL_END_TIME)));
        session.setFocusMinutes(cursor.getInt(cursor.getColumnIndexOrThrow(COL_FOCUS_MINUTES)));
        session.setBreakMinutes(cursor.getInt(cursor.getColumnIndexOrThrow(COL_BREAK_MINUTES)));
        session.setActualFocusMinutes(cursor.getInt(cursor.getColumnIndexOrThrow(COL_ACTUAL_FOCUS)));
        session.setDistractionsCount(cursor.getInt(cursor.getColumnIndexOrThrow(COL_DISTRACTIONS)));
        session.setSessionType(cursor.getString(cursor.getColumnIndexOrThrow(COL_SESSION_TYPE)));
        session.setCompleted(cursor.getInt(cursor.getColumnIndexOrThrow(COL_COMPLETED)) == 1);
        return session;
    }
    
    // Delete a session
    public int deleteSession(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete(TABLE_SESSIONS, COL_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
        return rows;
    }
    
    // ===== NOTES METHODS =====
    
    // Insert a new note
    public long insertNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NOTE_TITLE, note.getTitle());
        values.put(COL_NOTE_CONTENT, note.getContent());
        values.put(COL_NOTE_TIMESTAMP, note.getTimestamp());
        
        long id = db.insert(TABLE_NOTES, null, values);
        db.close();
        return id;
    }
    
    // Update note
    public int updateNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NOTE_TITLE, note.getTitle());
        values.put(COL_NOTE_CONTENT, note.getContent());
        
        int rows = db.update(TABLE_NOTES, values, COL_NOTE_ID + "=?",
                new String[]{String.valueOf(note.getId())});
        db.close();
        return rows;
    }
    
    // Get all notes
    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NOTES + 
                " ORDER BY " + COL_NOTE_TIMESTAMP + " DESC", null);

        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId(cursor.getLong(cursor.getColumnIndexOrThrow(COL_NOTE_ID)));
                note.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(COL_NOTE_TITLE)));
                note.setContent(cursor.getString(cursor.getColumnIndexOrThrow(COL_NOTE_CONTENT)));
                note.setTimestamp(cursor.getLong(cursor.getColumnIndexOrThrow(COL_NOTE_TIMESTAMP)));
                notes.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return notes;
    }
    
    // Delete a note
    public int deleteNote(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete(TABLE_NOTES, COL_NOTE_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
        return rows;
    }
}
