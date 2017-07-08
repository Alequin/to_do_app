package com.example.james.todolist.activities.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.james.todolist.R;
import com.example.james.todolist.database.TaskSqlDatabase;
import com.example.james.todolist.helper.DateManager;
import com.example.james.todolist.model.Task;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by james on 07/07/2017.
 */

public class TaskListArrayAdapter extends ArrayAdapter<Task>{

    private Task task;

    private CheckBox checkBox;
    private TextView outline;
    private TextView dueDate;

    public TaskListArrayAdapter(Context context, ArrayList<Task> taskList){
        super(context, 0, taskList);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_of_task_list, parent, false);
        }

        task = getItem(position);

        checkBox = (CheckBox) view.findViewById(R.id.check_box_task_list_item);
        outline = (TextView) view.findViewById(R.id.task_outline_task_list_item);
        dueDate = (TextView) view.findViewById(R.id.task_due_date_task_list_item);

        checkBox.setChecked(task.isComplete());
        outline.setText(task.getOutline());
        String dueDateText = String.format("%s: %s",
                getContext().getString(R.string.due_date), task.getFormattedDueDate()
        );
        dueDate.setText(dueDateText);

        checkBox.setTag(task);
        view.setTag(task);

        return view;
    }
}
