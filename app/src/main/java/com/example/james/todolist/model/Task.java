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

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    public Calendar getDueDate() {
        return this.copyCalendarObj(this.dueDate);
    }

    public void setDueDate(Calendar dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isOverdue() {
        Calendar today = Calendar.getInstance();
        return dueDate.before(today);
    }

    private Calendar copyCalendarObj(Calendar toCopy){
        return (Calendar) toCopy.clone();
    }
}
