package com.example.james.todolist.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by james on 07/07/2017.
 */

public class TaskSqlDatabase extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String NAME = "TaskDb";

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public TaskSqlDatabase(Context context){
        super(context, NAME, null, VERSION);
    }
}
