package com.example.james.todolist.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.james.todolist.model.Task;

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

    public long addTask(Task task){
        return database.addTask(task);
    }
    
}
