package com.example.james.todolist.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;

import com.example.james.todolist.R;
import com.example.james.todolist.activities.adapter.TaskListArrayAdapter;
import com.example.james.todolist.database.DatabaseHandler;
import com.example.james.todolist.helper.DateManager;
import com.example.james.todolist.model.Task;

import java.io.Serializable;

public class TaskListActivity extends AppCompatActivity {

    public static final String TASK_EXTRA = "task_extra";

    private static int lastScrollPosition = 0;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        //DatabaseHandler.getDatabase().seed(100);

        listView = (ListView) findViewById(R.id.main_list_task_list_activity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.task_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.delete_all_menu_task_list_activity){
            DatabaseHandler db = DatabaseHandler.getDatabase();
            db.deleteAllTasks();
            updateListView();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickCheckBox(View view){
        CheckBox checkBox = (CheckBox) view;
        Task task = (Task) view.getTag();
        task.setCompleteStatus(checkBox.isChecked());
        task.update();
    }

    public void onClickListView(View view){
        Serializable task = (Serializable) view.getTag();
        Intent intent = new Intent(this, TaskViewerActivity.class);
        intent.putExtra(TASK_EXTRA, task);
        startActivity(intent);
    }

    public void onClickAddButton(View view){
        Intent intent = new Intent(this, NewTaskActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        lastScrollPosition = listView.getFirstVisiblePosition();
        super.onPause();
    }

    @Override
    protected void onResume() {
        DatabaseHandler db = DatabaseHandler.getDatabase();
        if(db.isOpen()){
            DatabaseHandler.init(this);
        }
        updateListView();
        listView.setSelection(lastScrollPosition);
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        DatabaseHandler.getDatabase().close();
        super.onDestroy();
    }

    private void updateListView(){
        DatabaseHandler dbHandler = DatabaseHandler.getDatabase();
        TaskListArrayAdapter taskAdapter = new TaskListArrayAdapter(this, dbHandler.getAllTasks());
        listView.setAdapter(taskAdapter);
    }
}
