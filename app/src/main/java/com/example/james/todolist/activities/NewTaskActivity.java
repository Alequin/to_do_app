package com.example.james.todolist.activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.james.todolist.R;
import com.example.james.todolist.helper.DateManager;
import com.example.james.todolist.model.Task;

import java.util.Calendar;

public class NewTaskActivity extends AppCompatActivity {

    public static final int SET_DUE_DATE_REQUEST_CODE = 2;
    public static final int SET_DUE_DATE_RESULT_CODE = 3;

    public static final String DAY_EXTRA = "day_extra";
    public static final String MONTH_EXTRA = "month_extra";
    public static final String YEAR_EXTRA = "year_extra";

    private static final String TASK_SAVE = "task_outline_save";

    private Task taskToMake;

    private TextView outlineView;
    private TextView extraDetailsView;
    private Button dueDateButton;
    private CalendarView calendar;

    @Override
    protected void onCreate(Bundle inState) {
        super.onCreate(inState);
        setContentView(R.layout.activity_new_task);

        outlineView = (TextView) findViewById(R.id.enter_outline_new_task_activity);
        extraDetailsView = (TextView) findViewById(R.id.extra_details_new_task_activity);
        dueDateButton = (Button) findViewById(R.id.due_date_new_task_activity);
        calendar = (CalendarView) findViewById(R.id.calendar_view_new_task_activity);

        setAllEditTextOptionButton(EditorInfo.IME_ACTION_DONE);

        if(calendar != null){
            setListenerOnCalendarView(calendar);
        }

        if(inState == null){
            taskToMake = new Task("", "", false);
        }else{
            buildLayoutFromSavedState(inState);
        }
    }

    private void setAllEditTextOptionButton(int option){
        //https://stackoverflow.com/questions/6265366/android-soft-keyboard-custom-done-button-text
        outlineView.setImeOptions(option);
        extraDetailsView.setImeOptions(option);
    }

    private void setListenerOnCalendarView(CalendarView view){
        final Context currentContext = this;
        view.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar selected = DateManager.newCalendar(year, month, dayOfMonth);

                if(!DateManager.isBeforeToday(selected)){
                    taskToMake.setDueDate(selected);
                }else{
                    Toast.makeText(currentContext, getString(R.string.warning_invalid_date_message), Toast.LENGTH_SHORT).show();
                    calendar.setDate(Calendar.getInstance().getTimeInMillis());
                }
            }
        });
    }

    private void buildLayoutFromSavedState(Bundle inState){

        taskToMake = (Task) inState.getSerializable(TASK_SAVE);

        outlineView.setText(taskToMake.getOutline());
        extraDetailsView.setText(taskToMake.getExtraDetails());

        if(dueDateButton != null){
            setDueDateButtonText(taskToMake);
        }

        if(calendar != null){
            setDateSelectedOnCalendar(taskToMake);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.new_task_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){

            case android.R.id.home:
                finish();
                return true;

            case R.id.tick_new_task_menu_icon:
                boolean success = onClickMakeTask();
                if(success){
                    finish();
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickDueDateButton(View view){
        Intent intent = new Intent(this, DateActivity.class);
        Calendar cal = taskToMake.getDueDate();
        intent.putExtra(DAY_EXTRA, cal.get(Calendar.DAY_OF_MONTH));
        intent.putExtra(MONTH_EXTRA, cal.get(Calendar.MONTH));
        intent.putExtra(YEAR_EXTRA, cal.get(Calendar.YEAR));
        startActivityForResult(intent, SET_DUE_DATE_REQUEST_CODE);
    }

    public boolean onClickMakeTask(){

        String outlineText = outlineView.getText().toString();
        if(outlineText == null || outlineText.isEmpty()){
            String message = getString(R.string.warning_must_provide_outline_text);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            return false;
        }

        String extraDetailsText = extraDetailsView.getText().toString();
        if(extraDetailsText == null){
            extraDetailsText = "";
        }

        taskToMake.setOutline(outlineText);
        taskToMake.setExtraDetails(extraDetailsText);

        taskToMake.save();
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == SET_DUE_DATE_REQUEST_CODE) {
            if (resultCode == SET_DUE_DATE_RESULT_CODE) {

                Bundle bundleDueDate = data.getExtras();
                int day = bundleDueDate.getInt(DateActivity.DAY_EXTRA);
                int month = bundleDueDate.getInt(DateActivity.MONTH_EXTRA);
                int year = bundleDueDate.getInt(DateActivity.YEAR_EXTRA);
                taskToMake.setDueDate(year, month, day);

                if(dueDateButton != null){
                    setDueDateButtonText(taskToMake);
                }
                if(calendar != null){
                    setDateSelectedOnCalendar(taskToMake);
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setDueDateButtonText(Task task){
        String dueDateLabel = getString(R.string.due_date);
        String dateText = task.getFormattedDueDate();
        String output = String.format("%s: %s", dueDateLabel, dateText);
        dueDateButton.setText(output);
    }

    private void setDateSelectedOnCalendar(Task task){
        Calendar dueDate = task.getDueDate();
        calendar.setDate(dueDate.getTimeInMillis());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(TASK_SAVE, taskToMake);
        super.onSaveInstanceState(outState);
    }
}
