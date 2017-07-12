package com.example.james.todolist.activities;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.james.todolist.R;
import com.example.james.todolist.application.TaskListApplication;
import com.example.james.todolist.helper.DateManager;

import java.util.Calendar;

/* This activity uses one of two layouts depending on version because...
    - On versions 21 and below the DatePicker defaults to a spinner selector
    Which looks wrong.
    - On versions 22 and above the Calendar does not return the selected date
    and instead returns the current date.
*/

public class DateActivity extends AppCompatActivity {

    public static final String DAY_EXTRA = "day_extra";
    public static final String MONTH_EXTRA = "month_extra";
    public static final String YEAR_EXTRA = "year_extra";

    private DatePicker datePicker;
    private CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean screenTooSmall = TaskListApplication.screenSizeCode() < 360;
        if(screenTooSmall || Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP){
            setContentView(R.layout.activity_calendar_view);
        }else{
            setContentView(R.layout.activity_date);
        }

        hideActionBar();

        datePicker = (DatePicker) findViewById(R.id.date_picker_date_activity);
        calendar = (CalendarView) findViewById(R.id.calendar_date_activity);

        prepareView();
    }

    private void prepareView(){
        Calendar today = Calendar.getInstance();
        Bundle extras = getIntent().getExtras();
        int day = extras.getInt(NewTaskActivity.DAY_EXTRA, today.get(Calendar.DAY_OF_MONTH));
        int month = extras.getInt(NewTaskActivity.MONTH_EXTRA, today.get(Calendar.MONTH));
        int year = extras.getInt(NewTaskActivity.YEAR_EXTRA, today.get(Calendar.YEAR));

        setDateChangeListener();
        setSelectedDate(year, month, day);

        if(datePicker != null){
            datePicker.setMinDate(today.getTimeInMillis());
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

    public void onClickSelectDate(View view){
        finish();
    }

    private void setDateChangeListener(){
        if(datePicker != null){
            datePicker.init(1990, 6, 6, new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    onDateChange(year, monthOfYear, dayOfMonth);
                }
            });
        }

        if(calendar != null){
            calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView view, int year, int monthOfYear, int dayOfMonth) {
                    onDateChange(year, monthOfYear, dayOfMonth);
                }
            });
        }
    }

    private void onDateChange(int year, int month, int day){
        Calendar selected = DateManager.newCalendar(year, month, day);

        if(DateManager.isBeforeToday(selected)){
            String message = getString(R.string.warning_invalid_date_message);
            Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
            toast.show();
            setSelectedDate(Calendar.getInstance());
        }else{
            Intent dateDetails = bundleDateValues(selected);
            setResult(NewTaskActivity.SET_DUE_DATE_RESULT_CODE, dateDetails);
        }
    }

    private Intent bundleDateValues(Calendar cal){
        Intent intent = new Intent();
        intent.putExtra(DAY_EXTRA, cal.get(Calendar.DAY_OF_MONTH));
        intent.putExtra(MONTH_EXTRA, cal.get(Calendar.MONTH));
        intent.putExtra(YEAR_EXTRA, cal.get(Calendar.YEAR));
        return intent;
    }

    private void setSelectedDate(int year, int month, int day){
        if(datePicker != null){
            datePicker.updateDate(year, month, day);
        }else{
            Calendar cal = DateManager.newCalendar(year, month, day);
            calendar.setDate(cal.getTimeInMillis());
        }
    }

    private void setSelectedDate(Calendar cal){

        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);

        setSelectedDate(year, month, day);
    }
}
