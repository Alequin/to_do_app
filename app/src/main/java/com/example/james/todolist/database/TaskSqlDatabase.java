package com.example.james.todolist.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.james.todolist.model.Task;

/**
 * Created by james on 07/07/2017.
 */

public class TaskSqlDatabase extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String NAME = "TaskDb";

    private static final String TASK_TABLE_NAME = "task_table_name";

    // Columns
    private static final String ID = "id";
    private static final String OUTLINE = "outline";
    private static final String EXTRA_DETAILS = "extra_details";
    private static final String CREATION_DATE = "creation_date";
    private static final String DUE_DATE = "due_date";
    private static final String COMPLETE_STATE = "complete_state";

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableCommand = String.format(
                "CREATE TABLE %s(" +
                    "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "%s TEXT, %s TEXT, %s TEXT, %s TEXT, %s INTEGER);",
            TASK_TABLE_NAME, ID, OUTLINE, EXTRA_DETAILS, CREATION_DATE, DUE_DATE, COMPLETE_STATE
        );
        db.execSQL(createTableCommand);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public TaskSqlDatabase(Context context){
        super(context, NAME, null, VERSION);
    }

    public long addTask(Task task){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(OUTLINE, task.getOutline());
        values.put(EXTRA_DETAILS, task.getExtraDetails());
        values.put(CREATION_DATE, task.getFormattedCreationDate());
        values.put(DUE_DATE, task.getFormattedDueDate());
        values.put(COMPLETE_STATE, task.isComplete());

        long taskId = db.insert(TASK_TABLE_NAME, null, values);

        db.close();
        return taskId;
    }
}
