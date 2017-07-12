package com.example.james.todolist.application;

import android.app.Application;
import android.content.res.Resources;

import com.example.james.todolist.R;
import com.example.james.todolist.database.DatabaseHandler;

/**
 * Created by james on 07/07/2017.
 */

public class TaskListApplication extends Application {

    private static int currentScreenSize;

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseHandler.init(this);
        setScreenCode();
    }

    private void setScreenCode(){
        Resources r = getResources();
        currentScreenSize = r.getInteger(R.integer.screen_code);
    }

    public static int screenSizeCode(){
        return currentScreenSize;
    }
}
