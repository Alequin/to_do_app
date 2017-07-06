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

}
