package com.example.james.todolist.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.james.todolist.R;
import com.example.james.todolist.helper.DateManager;
import com.example.james.todolist.model.Task;

import java.io.Serializable;
import java.util.Calendar;

public class NewTaskActivity extends AppCompatActivity {

    public static final int SET_DUE_DATE_REQUEST_CODE = 2;
    public static final int SET_DUE_DATE_RESULT_CODE = 3;

    public static final String DAY_EXTRA = "day_extra";
    public static final String MONTH_EXTRA = "month_extra";
    public static final String YEAR_EXTRA = "year_extra";

    private static final String TASK_SAVE = "task_outline_save";

    private Task taskToMake;

    private Button dueDateButton;

    @Override
    protected void onCreate(Bundle inState) {
        super.onCreate(inState);
        setContentView(R.layout.activity_new_task);

        dueDateButton = (Button) findViewById(R.id.due_date_new_task_activity);

        if(inState == null){
            taskToMake = new Task("", "", false);
        }else{
            taskToMake = (Task) inState.getSerializable(TASK_SAVE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            finish();
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

    public void onClickMakeTask(View view){

        TextView outlineView = (TextView) findViewById(R.id.enter_outline_new_task_activity);
        String outlineText = outlineView.getText().toString();

        if(outlineText == null || outlineText.isEmpty()){
            String message = getString(R.string.warning_must_provide_outline_text);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            return;
        }

        TextView extraDetailsView = (TextView) findViewById(R.id.extra_details_new_task_activity);
        String extraDetailsText = extraDetailsView.getText().toString();
        if(extraDetailsText == null){
            extraDetailsText = "";
        }

        taskToMake.setOutline(outlineText);
        taskToMake.setExtraDetails(extraDetailsText);

        int currentOrientation = getResources().getConfiguration().orientation;
        if(Configuration.ORIENTATION_LANDSCAPE == currentOrientation){
            Calendar selected = getSelectedDateWhenLandscape();
            if(selected != null){
                taskToMake.setDueDate(selected);
            }else{
                return;
            }
        }

        taskToMake.save();

        finish();
    }

    private Calendar getSelectedDateWhenLandscape(){
        CalendarView calendar = (CalendarView) findViewById(R.id.calendar_view_new_task_activity);

        Calendar today = Calendar.getInstance();
        Calendar selected = DateManager.getCalendarFromLong(calendar.getDate());

        if(selected.before(today)){
            Toast toast = Toast.makeText(this, getString(R.string.warning_invalid_date_message), Toast.LENGTH_SHORT);
            toast.show();
            return null;
        }else{
            return selected;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == SET_DUE_DATE_REQUEST_CODE){
            if(resultCode == SET_DUE_DATE_RESULT_CODE){
                Bundle bundleDueDate = data.getExtras();
                int day = bundleDueDate.getInt(DateActivity.DAY_EXTRA);
                int month = bundleDueDate.getInt(DateActivity.MONTH_EXTRA);
                int year = bundleDueDate.getInt(DateActivity.YEAR_EXTRA);
                taskToMake.setDueDate(year, month, day);

                dueDateButton.setText(
                    String.format("%s: %s",
                        getString(R.string.due_date), taskToMake.getFormattedDueDate())
                );
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(TASK_SAVE, taskToMake);
        super.onSaveInstanceState(outState);
    }
}
