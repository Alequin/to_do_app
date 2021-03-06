package com.example.james.todolist.model;

import com.example.james.todolist.database.DatabaseHandler;
import com.example.james.todolist.database.FakeDatabase;
import com.example.james.todolist.helper.DateManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by james on 06/07/2017.
 */

public class Task implements Serializable{

    private long id;
    private String outline;
    private String extraDetails;
    private Calendar creationDate;
    private Calendar dueDate;
    private boolean status;

    private static final String invalidDateErrorMessage = "Invalid date entered: day - %s, month - %s, year - %s";
    private static final String dueDateToEarlyErrorMessage = "Due date cannot be before the creation date";

    public Task(long id, String outline, String extraDetails, Calendar creationDate, Calendar dueDate, boolean status){
        this.id = id;
        this.outline = outline;
        this.extraDetails = extraDetails;
        this.creationDate = creationDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    public Task(String outline, String extraDetails, Calendar creationDate, Calendar dueDate, boolean status){
        this(0, outline, extraDetails, creationDate, dueDate, status);
    }

    public Task(String outline, String extraDetails, boolean status){
        this(outline, extraDetails, Calendar.getInstance(), Calendar.getInstance(), status);
    }

    public static ArrayList<Task> getAll(){
        DatabaseHandler dbHandler = DatabaseHandler.getDatabase();
        return dbHandler.getAllTasks();
    }

    public void save(){

        if(!isTaskStateValid()){
            throw new IllegalStateException(makeInvalidInputErrorMessage());
        }

        DatabaseHandler dbHandler = DatabaseHandler.getDatabase();
        id = dbHandler.addTask(this);
    }

    public void update(){

        if(!isTaskStateValid()){
            throw new IllegalStateException(makeInvalidInputErrorMessage());
        }

        DatabaseHandler dbHandler = DatabaseHandler.getDatabase();
        dbHandler.updateTask(this);
    }

    private boolean isTaskStateValid(){
        return isOutlineValid() && !isExtraDetailNull() && !isDateNull(creationDate) && !isDateNull(dueDate);
    }

    public void delete(){
        DatabaseHandler dbHandler = DatabaseHandler.getDatabase();
        dbHandler.deleteTask(this);
    }

    public long getId(){
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

    public void setCompleteStatus(boolean status) {
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

    public void setCreationDate(Calendar creationDate){
        this.creationDate = creationDate;
    }

    public void setCreationDate(int year, int month, int day) {
        if(!DateManager.isDateValid(year, month, day)){
            String message = String.format(invalidDateErrorMessage, day, month, year);
            throw new IllegalArgumentException(message);
        }
        //Month is reduced by one as Calendar obj months go from 0 - 11 not 1 - 12
        creationDate.set(year, month, day);
    }

    public Calendar getDueDate() {
        return copyCalendarObj(dueDate);
    }

    public String getFormattedDueDate() {
        return DateManager.formatDate(dueDate);
    }

    public void setDueDate(Calendar dueDate){
        this.dueDate = dueDate;
        if(dueDate.before(creationDate)){
            throw new IllegalArgumentException(dueDateToEarlyErrorMessage);
        }
    }

    public void setDueDate(int year, int month, int day) {
        if(!DateManager.isDateValid(year, month, day)){
            String message = String.format(invalidDateErrorMessage, day, month, year);
            throw new IllegalArgumentException(message);
        }
        //Month is reduced by one as Calendar obj months go from 0 - 11 not 1 - 12
        this.dueDate.set(year, month, day);

        if(dueDate.before(creationDate)){
            throw new IllegalArgumentException(dueDateToEarlyErrorMessage);
        }
    }

    public boolean isOverdue() {
        Calendar today = Calendar.getInstance();
        return dueDate.before(today);
    }

    private Calendar copyCalendarObj(Calendar toCopy){
        return (Calendar) toCopy.clone();
    }

    private boolean isOutlineValid(){
        return outline != null && !outline.isEmpty();
    }

    private boolean isExtraDetailNull(){
        return extraDetails == null;
    }

    private boolean isDateNull(Calendar cal){
        return cal == null;
    }

    private String makeInvalidInputErrorMessage(){

        String message = "\nError: ";

        if(!isOutlineValid()){
            message += "Outline: ";
            if(outline == null){
                message += "null";
            }else
            if(outline.isEmpty()){
                message += "is empty";
            }
            message += "\n";
        }

        if(isExtraDetailNull()){
            message += "Extra details: null";
            message += "\n";
        }

        if(isDateNull(creationDate)){
            message += "Creation Date: null";
            message += "\n";
        }

        if(isDateNull(dueDate)){
            message += "Due Date: null";
            message += "\n";
        }

        return message;
    }
}
