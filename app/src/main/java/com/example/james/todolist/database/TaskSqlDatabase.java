package com.example.james.todolist.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.james.todolist.helper.DateManager;
import com.example.james.todolist.model.Task;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by james on 07/07/2017.
 */

public class TaskSqlDatabase extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String NAME = "TaskDb";

    private static final String TASK_TABLE_NAME = "task_table_name";

    // Columns
    public static final String ID = "id";
    public static final String OUTLINE = "outline";
    public static final String EXTRA_DETAILS = "extra_details";
    public static final String CREATION_DATE = "creation_date";
    public static final String DUE_DATE = "due_date";
    public static final String COMPLETE_STATE = "complete_state";

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

    public ArrayList<Task> getAllTasks(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s", TASK_TABLE_NAME), null);

        final int length = cursor.getCount();
        ArrayList<Task> taskList = new ArrayList<>(length);
        for(int j=0; j<length; j++){
            cursor.moveToPosition(j);
            long id = cursor.getLong(cursor.getColumnIndex(TaskSqlDatabase.ID));
            String outline = cursor.getString(cursor.getColumnIndex(TaskSqlDatabase.OUTLINE));
            String extraDetails = cursor.getString(cursor.getColumnIndex(TaskSqlDatabase.EXTRA_DETAILS));

            String creationDateText = cursor.getString(cursor.getColumnIndex(TaskSqlDatabase.CREATION_DATE));
            Calendar creationDate = DateManager.getCalendarFromSqlDate(creationDateText);
            String dueDateText = cursor.getString(cursor.getColumnIndex(TaskSqlDatabase.DUE_DATE));
            Calendar dueDate = DateManager.getCalendarFromSqlDate(dueDateText);

            int completeState = cursor.getInt(cursor.getColumnIndex(TaskSqlDatabase.COMPLETE_STATE));

            taskList.add(new Task(id, outline, extraDetails, creationDate, dueDate, false));
        }

        return taskList;
    }

    public long addTask(Task task){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(OUTLINE, task.getOutline());
        values.put(EXTRA_DETAILS, task.getExtraDetails());
        values.put(CREATION_DATE, DateManager.formatDateForSQL(task.getCreationDate()));
        values.put(DUE_DATE, DateManager.formatDateForSQL(task.getDueDate()));
        values.put(COMPLETE_STATE, task.isComplete());

        long taskId = db.insert(TASK_TABLE_NAME, null, values);

        db.close();
        return taskId;
    }
}
