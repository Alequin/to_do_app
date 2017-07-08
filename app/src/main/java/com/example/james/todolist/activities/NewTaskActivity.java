package com.example.james.todolist.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.james.todolist.R;

public class NewTaskActivity extends AppCompatActivity {

    public static final int SET_DUE_DATE_REQUEST_CODE = 2;
    public static final int SET_DUE_DATE_RESULT_CODE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
    }

    public void onClickDueDateButton(View view){
        Intent intent = new Intent(this, DateActivity.class);
        startActivityForResult(intent, SET_DUE_DATE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == SET_DUE_DATE_REQUEST_CODE){
            if(resultCode == SET_DUE_DATE_RESULT_CODE){

            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
