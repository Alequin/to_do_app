package com.example.james.todolist.model;

import com.example.james.todolist.helper.DateManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by james on 06/07/2017.
 */

public class Task implements Serializable{

    private int id;
    private String outline;
    private String extraDetails;
    private Calendar creationDate;
    private Calendar dueDate;
    private boolean status;

    private static String invalidDateErrorMessage = "Invalid date entered: day - %s, month - %s, year - %s";

    public Task(int id, String outline, String extraDetails, Calendar creationDate, Calendar dueDate, boolean status){
        this.id = id;
        this.outline = outline;
        this.extraDetails = extraDetails;
        this.creationDate = creationDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    public Task(String outline, String extraDetails, Calendar creationDate, Calendar dueDate, boolean status){
        this.id = 0;
        this.outline = outline;
        this.extraDetails = extraDetails;
        this.creationDate = creationDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    public static ArrayList<Task> get_all(){
        return FakeDatabase.queryDatabase();
    }

    public void save(){
        this.id = FakeDatabase.size();
        FakeDatabase.add(this);
    }

    public void update(){
        FakeDatabase.add(id, this);
    }

    public void delete(){
        FakeDatabase.remove_by_index(id);
    }

    public int getId(){
        return id;
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
        return copyCalendarObj(creationDate);
    }

    public String getFormattedCreationDate() {
        return DateManager.formatDate(creationDate);
    }

    public void setCreationDate(int day, int month, int year) {
        if(!DateManager.isDateValid(day, month, year)){
            String message = String.format(invalidDateErrorMessage, day, month, year);
            throw new IllegalArgumentException(message);
        }
        creationDate.set(year, month, day);
    }

    public Calendar getDueDate() {
        return copyCalendarObj(dueDate);
    }

    public String getFormattedDueDate() {
        return DateManager.formatDate(dueDate);
    }

    public void setDueDate(int day, int month, int year) {
        if(!DateManager.isDateValid(day, month, year)){
            String message = String.format(invalidDateErrorMessage, day, month, year);
            throw new IllegalArgumentException(message);
        }
        this.dueDate.set(year, month, day);
    }

    public boolean isOverdue() {
        Calendar today = Calendar.getInstance();
        return dueDate.before(today);
    }

    private Calendar copyCalendarObj(Calendar toCopy){
        return (Calendar) toCopy.clone();
    }
}
