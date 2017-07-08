package com.example.james.todolist.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.james.todolist.R;

import java.util.Calendar;

public class DateActivity extends AppCompatActivity {

    public static final String DAY_EXTRA = "day_extra";
    public static final String MONTH_EXTRA = "month_extra";
    public static final String YEAR_EXTRA = "year_extra";

    private DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        hideActionBar();

        datePicker = (DatePicker) findViewById(R.id.date_picker_date_activity);
    }

    private void hideActionBar(){
        ActionBar bar = getActionBar();
        if(bar != null){
            bar.hide();
        }else{
            getSupportActionBar().hide();
        }
    }


    public void onClickSelectDate(View view){

        Calendar today = Calendar.getInstance();
        Calendar selected = getCalendarFromDatePicker();

        if(selected.before(today)){
            Toast toast = Toast.makeText(this, getString(R.string.invalid_date_message), Toast.LENGTH_SHORT);
            toast.show();
            return;
        }else{
            bundleDateValues(selected);
            setResult(NewTaskActivity.SET_DUE_DATE_RESULT_CODE);
            finish();
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

    private void bundleDateValues(Calendar cal){
        Intent intent = getIntent();
        intent.putExtra(DAY_EXTRA, cal.get(Calendar.DAY_OF_MONTH));
        intent.putExtra(MONTH_EXTRA, cal.get(Calendar.MONTH));
        intent.putExtra(DAY_EXTRA, cal.get(Calendar.YEAR));
    }
}
