package com.example.james.todolist.activities.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.james.todolist.R;
import com.example.james.todolist.model.Task;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by james on 07/07/2017.
 */

public class TaskListArrayAdapter extends ArrayAdapter<Task>{

    public TaskListArrayAdapter(Context context, ArrayList<Task> taskList){
        super(context, 0, taskList);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_of_task_list, parent, false);
        }

        Task task = getItem(position);

        CheckBox checkBox = (CheckBox) view.findViewById(R.id.check_box_task_list_item);
        TextView outline = (TextView) view.findViewById(R.id.task_outline_task_list_item);
        TextView dueDate = (TextView) view.findViewById(R.id.task_due_date_task_list_item);

        checkBox.setChecked(task.isComplete());
        outline.setText(task.getOutline());
        String dueDateText = String.format("%s: %s",
                getContext().getString(R.string.due_date), task.getDueDate());
        dueDate.setText(dueDateText);

        return view;
    }
}
