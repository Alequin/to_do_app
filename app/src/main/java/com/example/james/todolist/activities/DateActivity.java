package com.example.james.todolist.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.james.todolist.R;
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
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
            setContentView(R.layout.activity_date);
        }else{
            setContentView(R.layout.activity_calendar_view);
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
        if(datePicker != null){
            datePicker.init(year, month, day, null);
        }else{
            Calendar cal = Calendar.getInstance();
            cal.set(year, month, day);
            calendar.setDate(cal.getTimeInMillis());
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

        Calendar today = Calendar.getInstance();
        Calendar selected = null;
        if(datePicker != null){
            selected = getCalendarFromDatePicker();
        }else{
            selected = DateManager.getCalendarFromLong(calendar.getDate());
        }

        if(selected.before(today)){
            Toast toast = Toast.makeText(this, getString(R.string.warning_invalid_date_message), Toast.LENGTH_SHORT);
            toast.show();
            return;
        }else{
            int resultCode = NewTaskActivity.SET_DUE_DATE_RESULT_CODE;
            Intent dateDetails = bundleDateValues(selected);
            setResult(resultCode, dateDetails);
            finish();
        }
    }

    private Calendar getCalendarFromDatePicker(){

        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth()+1;
        int year = datePicker.getYear();

        Calendar input = Calendar.getInstance();
        input.set(year, month, day);
        return input;
    }

    private Intent bundleDateValues(Calendar cal){
        Intent intent = new Intent();
        intent.putExtra(DAY_EXTRA, cal.get(Calendar.DAY_OF_MONTH));
        intent.putExtra(MONTH_EXTRA, cal.get(Calendar.MONTH));
        intent.putExtra(YEAR_EXTRA, cal.get(Calendar.YEAR));
        return intent;
    }
}
