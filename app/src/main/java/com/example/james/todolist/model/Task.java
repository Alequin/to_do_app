package com.example.james.todolist.model;

import java.util.Calendar;

/**
 * Created by james on 06/07/2017.
 */


public class Task {

    private String outline;
    private String extraDetails;
    private Calendar creationDate;
    private Calendar dueDate;
    private boolean status;

    public Task(String outline, String extraDetails, Calendar creationDate, Calendar dueDate, boolean status){
        this.outline = outline;
        this.extraDetails = extraDetails;
        this.creationDate = creationDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    public String getOutline() {
        return outline;
    }

    public void setOutline(String outline) {
        this.outline = outline;
    }

    public boolean isComplete() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getExtraDetails() {
        return extraDetails;
    }

    public void setExtraDetails(String extraDetails) {
        this.extraDetails = extraDetails;
    }

    public Calendar getCreationDate() {
        return this.copyCalendarObj(creationDate);
    }

    public String getFormattedCreationDate() {
        return this.formatCalendarDate(creationDate);
    }

    public void setCreationDate(int day, int month, int year) {
        this.creationDate.set(year, month, day);
    }

    public Calendar getDueDate() {
        return this.copyCalendarObj(this.dueDate);
    }

    public String getFormattedDueDate() {
        return this.formatCalendarDate(dueDate);
    }

    public void setDueDate(int day, int month, int year) {
        this.dueDate.set(year, month, day);
    }

    public boolean isOverdue() {
        Calendar today = Calendar.getInstance();
        return dueDate.before(today);
    }

    private String formatCalendarDate(Calendar cal){
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        return String.format("%02d/%02d/%s", day, month, year);
    }

    private Calendar copyCalendarObj(Calendar toCopy){
        return (Calendar) toCopy.clone();
    }
}
