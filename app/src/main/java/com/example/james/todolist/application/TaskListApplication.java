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
    private static int swTiny;
    private static int sw360;
    private static int sw460;
    private static int sw600;

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseHandler.init(this);
        setScreenCodes();
    }

    private void setScreenCodes(){
        Resources r = getResources();
        currentScreenSize = r.getInteger(R.integer.screen_code);
        swTiny = r.getInteger(R.integer.screen_code_tiny);
        sw360 = r.getInteger(R.integer.screen_code_360);
        sw460 = r.getInteger(R.integer.screen_code_460);
        sw600 = r.getInteger(R.integer.screen_code_600);
    }

    public static int screenSizeCode(){
        return currentScreenSize;
    }

    public static int swTiny(){
        return swTiny;
    }

    public static int sw360(){
        return sw360;
    }

    public static int sw460(){
        return sw460;
    }

    public static int sw600(){
        return sw600;
    }
}
