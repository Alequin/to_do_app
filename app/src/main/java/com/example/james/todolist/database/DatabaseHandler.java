package com.example.james.todolist.database;

import android.content.Context;

import com.example.james.todolist.model.Task;

import java.util.ArrayList;

/**
 * Created by james on 07/07/2017.
 */

public class DatabaseHandler {

    private static DatabaseHandler dbHandler;

    private TaskSqlDatabase database;

    private DatabaseHandler(TaskSqlDatabase database){
        this.database = database;
    }

    public static void init(Context context){
        dbHandler = new DatabaseHandler(new TaskSqlDatabase(context));
    }

    public void close(){
        database.close();
    }

    public static DatabaseHandler getDatabase(){
        return dbHandler;
    }

    public ArrayList<Task> getAllTasks(){
        return database.getAllTasks();
    }

    public long addTask(Task task){
        return database.addTask(task);
    }

    public void updateTask(Task task){
        database.updateTask(task);
    }

    public void deleteTask(Task task){
        database.deleteTask(task);
    }

    public void seed(){
        database.deleteAllTasks();
        FakeDatabase.buildDatabase();
        for(Task task : FakeDatabase.queryDatabase()){
            addTask(task);
        }
    }
    
}
