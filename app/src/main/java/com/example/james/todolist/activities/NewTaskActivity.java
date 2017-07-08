package com.example.james.todolist.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.james.todolist.R;
import com.example.james.todolist.model.Task;

import java.util.Calendar;

public class NewTaskActivity extends AppCompatActivity {

    public static final int SET_DUE_DATE_REQUEST_CODE = 2;
    public static final int SET_DUE_DATE_RESULT_CODE = 3;

    private Task taskToMake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        Calendar date1 = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();
        taskToMake = new Task("", "", date1, date2, false);

    }

    public void onClickDueDateButton(View view){
        Intent intent = new Intent(this, DateActivity.class);
        startActivityForResult(intent, SET_DUE_DATE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == SET_DUE_DATE_REQUEST_CODE){
            if(resultCode == SET_DUE_DATE_RESULT_CODE){
                Bundle dueDateBundle = data.getExtras();
                int day = dueDateBundle.getInt(DateActivity.DAY_EXTRA);
                int month = dueDateBundle.getInt(DateActivity.MONTH_EXTRA);
                int year = dueDateBundle.getInt(DateActivity.YEAR_EXTRA);
                taskToMake.setDueDate(day, month, year);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
