package com.example.james.todolist.application;

import android.app.Application;

import com.example.james.todolist.database.DatabaseHandler;

/**
 * Created by james on 07/07/2017.
 */

public class TaskListApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseHandler.init(this);
    }
}
