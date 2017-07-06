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

    public Task(String outline, String extraDetails, Calendar creationDate, Calendar dueDate){
        this.outline = outline;
        this.extraDetails = extraDetails;
        this.creationDate = creationDate;
        this.dueDate = dueDate;
    }

    public String getOutline() {
        return outline;
    }

    public void setOutline(String outline) {
        this.outline = outline;
    }

    public String getExtraDetails() {
        return extraDetails;
    }

    public void setExtraDetails(String extraDetails) {
        this.extraDetails = extraDetails;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    public Calendar getDueDate() {
        return dueDate;
    }

    public void setDueDate(Calendar dueDate) {
        this.dueDate = dueDate;
    }
}
