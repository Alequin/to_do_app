package com.example.james.todolist.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.james.todolist.R;
import com.example.james.todolist.model.Task;

import java.util.Calendar;

public class NewTaskActivity extends AppCompatActivity {

    public static final int SET_DUE_DATE_REQUEST_CODE = 2;
    public static final int SET_DUE_DATE_RESULT_CODE = 3;

    private Task taskToMake;

    private Button dueDateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        dueDateButton = (Button) findViewById(R.id.due_date_new_task_activity);

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
                Bundle bundleDueDate = data.getExtras();
                int day = bundleDueDate.getInt(DateActivity.DAY_EXTRA);
                int month = bundleDueDate.getInt(DateActivity.MONTH_EXTRA);
                int year = bundleDueDate.getInt(DateActivity.YEAR_EXTRA);
                taskToMake.setDueDate(day, month, year);

                dueDateButton.setText(
                    String.format("%s: %s",
                        getString(R.string.due_date), taskToMake.getFormattedDueDate())
                );
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
