package com.example.james.todolist.model;

import java.util.Date;

/**
 * Created by james on 06/07/2017.
 */


public class Task {

    private String outline;
    private String extraDetails;
    private Date creationDate;
    private Date dueDate;

    public Task(String outline, String extraDetails, Date creationDate, Date dueDate){
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
