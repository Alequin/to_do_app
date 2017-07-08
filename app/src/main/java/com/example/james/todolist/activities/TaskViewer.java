package com.example.james.todolist.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.james.todolist.R;
import com.example.james.todolist.model.Task;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TaskViewer extends AppCompatActivity {

    private CheckBox checkBox;
    private EditText extraDetails;

    private Task currentTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_viewer);

        currentTask = (Task) getIntent().getSerializableExtra(TaskList.TASK_EXTRA);

        TextView outline = (TextView) findViewById(R.id.outline_text_view_task_viewer_activity);
        outline.setText(currentTask.getOutline());

        prepareDateViews();

        checkBox = (CheckBox) findViewById(R.id.check_box_task_viewer_activity);
        checkBox.setChecked(currentTask.isComplete());

        extraDetails = (EditText) findViewById(R.id.extra_details_view_task_viewer_activity);
        extraDetails.setText(currentTask.getExtraDetails());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.task_viewer_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.bin_icon_task_viewer_activity){
            currentTask.delete();
            currentTask = null;
            setResult(TaskList.UPDATE_LIST_RESULT_CODE);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void prepareDateViews(){
        TextView creationDate = (TextView) findViewById(R.id.creation_date_info_view_task_viewer_activity);
        TextView dueDate = (TextView) findViewById(R.id.due_date_info_view_task_viewer_activity);

        String dateLayout = "%s: %s";
        String creationDateText = String.format(dateLayout, getString(R.string.creation_date), currentTask.getFormattedCreationDate());
        creationDate.setText(creationDateText);
        String dueDateText = String.format(dateLayout, getString(R.string.due_date), currentTask.getFormattedDueDate());
        dueDate.setText(dueDateText);
    }
    
    @Override
    public void onPause() {
        if(currentTask != null){
            currentTask.setStatus(checkBox.isChecked());
            currentTask.setExtraDetails(extraDetails.getText().toString());
            currentTask.update();
        }
        super.onPause();
    }
}
