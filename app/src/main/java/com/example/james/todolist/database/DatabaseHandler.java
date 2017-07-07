package com.example.james.todolist.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.james.todolist.model.FakeDatabase;
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

    public static DatabaseHandler getDatabase(){
        return dbHandler;
    }

    public ArrayList<Task> getAllTasks(){
        return database.getAllTasks();
    }

    public long addTask(Task task){
        return database.addTask(task);
    }

    public void seedDate(){
        database.deleteAllTasks();
        FakeDatabase.buildDatabase();
        for(Task task : FakeDatabase.queryDatabase()){
            addTask(task);
        }
    }
    
}
