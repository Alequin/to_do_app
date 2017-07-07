package com.example.james.todolist.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;

import com.example.james.todolist.R;
import com.example.james.todolist.activities.adapter.TaskListArrayAdapter;
import com.example.james.todolist.model.FakeDatabase;
import com.example.james.todolist.model.Task;

import java.io.Serializable;

public class TaskList extends AppCompatActivity {

    public static final String TASK_EXTRA = "task_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        FakeDatabase.buildDatabase();
        
        prepareListView();
    }

    private void prepareListView(){
        ListView list = (ListView) findViewById(R.id.main_list_task_list_activity);
        TaskListArrayAdapter taskAdapter = new TaskListArrayAdapter(this, FakeDatabase.queryDatabase());
        list.setAdapter(taskAdapter);
    }

    public void onClickCheckBox(View view){
        CheckBox checkBox = (CheckBox) view;
        Task task = (Task) view.getTag();
        task.setStatus(checkBox.isChecked());
        task.update();
    }

    public void onClickListView(View view){
        Serializable task = (Serializable) view.getTag();
        Intent intent = new Intent(this, TaskViewer.class);
        intent.putExtra(TASK_EXTRA, task);
        startActivity(intent);
    }
}
