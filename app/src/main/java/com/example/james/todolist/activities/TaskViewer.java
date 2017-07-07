package com.example.james.todolist.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

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

        checkBox = (CheckBox) findViewById(R.id.check_box_task_viewer_activity);
        TextView outline = (TextView) findViewById(R.id.outline_text_view_task_viewer_activity);
        TextView creationDate = (TextView) findViewById(R.id.creation_date_info_view_task_viewer_activity);
        TextView dueDate = (TextView) findViewById(R.id.due_date_info_view_task_viewer_activity);
        extraDetails = (EditText) findViewById(R.id.extra_details_view_task_viewer_activity);

        checkBox.setChecked(currentTask.isComplete());
        outline.setText(currentTask.getOutline());

        String dateLayout = "%s: %s";
        String creationDateText = String.format(dateLayout, getString(R.string.creation_date), currentTask.getFormattedCreationDate());
        creationDate.setText(creationDateText);
        String dueDateText = String.format(dateLayout, getString(R.string.due_date), currentTask.getFormattedDueDate());
        dueDate.setText(dueDateText);
    }

    public void onClickUpdateButton(View view){
        currentTask.setExtraDetails(extraDetails.getText().toString());
        currentTask.update();
    }
}
