package com.example.james.todolist.activities;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.james.todolist.R;
import com.example.james.todolist.helper.UnitConverter;
import com.example.james.todolist.model.Task;

public class TaskViewerActivity extends AppCompatActivity {

    private CheckBox checkBox;
    private EditText extraDetails;

    private Task currentTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_viewer);

        currentTask = (Task) getIntent().getSerializableExtra(TaskListActivity.TASK_EXTRA);

        final TextView outline = (TextView) findViewById(R.id.outline_text_view_task_viewer_activity);
        outline.setText(currentTask.getOutline());

        prepareDateViews();

        checkBox = (CheckBox) findViewById(R.id.check_box_task_viewer_activity);
        checkBox.setChecked(currentTask.isComplete());

        extraDetails = (EditText) findViewById(R.id.extra_details_view_task_viewer_activity);
        extraDetails.setText(currentTask.getExtraDetails());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int height = outline.getHeight();
//                int heightInDp = UnitConverter.dpToPixel( (float) height);

                ScrollView scrollView = (ScrollView) findViewById(R.id.outline_scroll_view_task_viewer_activity);

            }
        }, 30);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.task_viewer_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){

            case android.R.id.home:
                finish();
                return true;

            case R.id.bin_icon_task_viewer_activity:
                currentTask.delete();
                currentTask = null;
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
            currentTask.setCompleteStatus(checkBox.isChecked());
            currentTask.setExtraDetails(extraDetails.getText().toString());
            currentTask.update();
        }
        super.onPause();
    }
}
