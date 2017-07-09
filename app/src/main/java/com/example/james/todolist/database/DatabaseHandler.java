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

    public void open(Context context){
        init(context);
    }

    public void close(){
        database.close();
        database = null;
    }

    public static DatabaseHandler getDatabase(){
        return dbHandler;
    }

    public Task findById(long id){
        return database.findById(id);
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

    public void deleteAllTasks() {
        database.deleteAllTasks();
    }

    public void seed(int times){
        database.deleteAllTasks();
        for(int j = 0; j<times; j++) {
            FakeDatabase.buildDatabase();
        }
    }

}
