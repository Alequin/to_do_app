package com.example.james.todolist.activities;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.james.todolist.R;

import java.util.Calendar;

public class DateActivity extends AppCompatActivity {

    private DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        hideActionBar();

        datePicker = (DatePicker) findViewById(R.id.date_picker_date_activity);
    }

    public void onClickSelectDate(View view){

        Calendar today = Calendar.getInstance();
        Calendar selected = getCalendarFromDatePicker();

        if(selected.before(today)){
            Toast toast = Toast.makeText(this, getString(R.string.invalid_date_message), Toast.LENGTH_SHORT);
            toast.show();
            return;
        }else{

        }
    }

    private void hideActionBar(){
        ActionBar bar = getActionBar();
        if(bar != null){
            bar.hide();
        }else{
            getSupportActionBar().hide();
        }
    }

    private Calendar getCalendarFromDatePicker(){

        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        Calendar input = Calendar.getInstance();
        input.set(year, month, day);
        return input;
    }
}
