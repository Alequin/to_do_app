package com.example.james.todolist.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.james.todolist.R;
import com.example.james.todolist.activities.adapter.TaskListArrayAdapter;
import com.example.james.todolist.model.FakeDatabase;

public class TaskList extends AppCompatActivity {

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
}
