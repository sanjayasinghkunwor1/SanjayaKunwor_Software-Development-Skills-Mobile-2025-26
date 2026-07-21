package com.rajan.androidassignment.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FocusSession {
    private long id;
    private long startTime;
    private long endTime;
    private int focusMinutes;
    private int breakMinutes;
    private int actualFocusMinutes;
    private int distractionsCount;
    private String sessionType;
    private boolean completed;

    public FocusSession() {
    }

    public FocusSession(long startTime, int focusMinutes, int breakMinutes, String sessionType) {
        this.startTime = startTime;
        this.focusMinutes = focusMinutes;
        this.breakMinutes = breakMinutes;
        this.sessionType = sessionType;
        this.distractionsCount = 0;
        this.completed = false;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getFocusMinutes() {
        return focusMinutes;
    }

    public void setFocusMinutes(int focusMinutes) {
        this.focusMinutes = focusMinutes;
    }

    public int getBreakMinutes() {
        return breakMinutes;
    }

    public void setBreakMinutes(int breakMinutes) {
        this.breakMinutes = breakMinutes;
    }

    public int getActualFocusMinutes() {
        return actualFocusMinutes;
    }

    public void setActualFocusMinutes(int actualFocusMinutes) {
        this.actualFocusMinutes = actualFocusMinutes;
    }

    public int getDistractionsCount() {
        return distractionsCount;
    }

    public void setDistractionsCount(int distractionsCount) {
        this.distractionsCount = distractionsCount;
    }

    public String getSessionType() {
        return sessionType;
    }

    public void setSessionType(String sessionType) {
        this.sessionType = sessionType;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    // Helper methods
    public String getFormattedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
        return sdf.format(new Date(startTime));
    }

    public String getFormattedTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        return sdf.format(new Date(startTime));
    }

    public String getFormattedDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy 'at' hh:mm a", Locale.getDefault());
        return sdf.format(new Date(startTime));
    }
}
