package com.example.james.todolist.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.james.todolist.R;
import com.example.james.todolist.model.Task;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TaskViewer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_viewer);

        Task task = (Task) getIntent().getSerializableExtra(TaskList.TASK_EXTRA);

        CheckBox checkBox = (CheckBox) findViewById(R.id.check_box_task_viewer_activity);
        TextView outline = (TextView) findViewById(R.id.outline_text_view_task_viewer_activity);
        TextView creationDate = (TextView) findViewById(R.id.creation_date_info_view_task_viewer_activity);
        TextView dueDate = (TextView) findViewById(R.id.due_date_info_view_task_viewer_activity);

        checkBox.setChecked(task.isComplete());
        outline.setText(task.getOutline());

        String dateLayout = "%s: %s";
        String creationDateText = String.format(dateLayout, getString(R.string.creation_date), task.getFormattedCreationDate());
        creationDate.setText(creationDateText);
        String dueDateText = String.format(dateLayout, getString(R.string.due_date), task.getFormattedDueDate());
        dueDate.setText(dueDateText);
    }
}
