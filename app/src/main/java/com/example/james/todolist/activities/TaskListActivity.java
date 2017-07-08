package com.example.james.todolist.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;

import com.example.james.todolist.R;
import com.example.james.todolist.activities.adapter.TaskListArrayAdapter;
import com.example.james.todolist.database.DatabaseHandler;
import com.example.james.todolist.model.Task;

import java.io.Serializable;

public class TaskListActivity extends AppCompatActivity {

    public static final String TASK_EXTRA = "task_extra";
    public static final int UPDATE_LIST_REQUEST_CODE = 0;
    public static final int UPDATE_LIST_RESULT_CODE = 1;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        DatabaseHandler.getDatabase().seed();

        prepareListView();
    }

    private void prepareListView(){
        DatabaseHandler dbHandler = DatabaseHandler.getDatabase();
        TaskListArrayAdapter taskAdapter = new TaskListArrayAdapter(this, dbHandler.getAllTasks());

        listView = (ListView) findViewById(R.id.main_list_task_list_activity);
        listView.setAdapter(taskAdapter);
    }

    public void onClickCheckBox(View view){
        CheckBox checkBox = (CheckBox) view;
        Task task = (Task) view.getTag();
        task.setStatus(checkBox.isChecked());
        task.update();
    }

    public void onClickListView(View view){
        Serializable task = (Serializable) view.getTag();
        Intent intent = new Intent(this, TaskViewerActivity.class);
        intent.putExtra(TASK_EXTRA, task);
        startActivityForResult(intent, UPDATE_LIST_REQUEST_CODE);
    }

    public void onClickAddButton(View view){

    }

    private void updateListView(){
        DatabaseHandler dbHandler = DatabaseHandler.getDatabase();
        TaskListArrayAdapter taskAdapter = new TaskListArrayAdapter(this, dbHandler.getAllTasks());
        listView.setAdapter(taskAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == UPDATE_LIST_REQUEST_CODE){
            if(resultCode == UPDATE_LIST_RESULT_CODE){
                updateListView();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
