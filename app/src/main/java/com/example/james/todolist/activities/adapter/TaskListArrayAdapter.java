package com.example.james.todolist.activities.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.james.todolist.R;
import com.example.james.todolist.database.TaskSqlDatabase;
import com.example.james.todolist.helper.DateManager;
import com.example.james.todolist.model.Task;

import java.util.Calendar;

/**
 * Created by james on 07/07/2017.
 */

public class TaskListArrayAdapter extends SimpleCursorAdapter{

    private Context currentContext;

    public TaskListArrayAdapter(Context context, Cursor taskList){
        super(context, 0, taskList, null, null, FLAG_REGISTER_CONTENT_OBSERVER);
        currentContext = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if(view == null){
            view = LayoutInflater.from(currentContext).inflate(R.layout.item_of_task_list, parent, false);
        }

        final Task task = (Task) getItem(position);

        CheckBox checkBox = (CheckBox) view.findViewById(R.id.check_box_task_list_item);
        TextView outline = (TextView) view.findViewById(R.id.task_outline_task_list_item);
        TextView dueDate = (TextView) view.findViewById(R.id.task_due_date_task_list_item);

        checkBox.setChecked(task.isComplete());
        outline.setText(task.getOutline());
        String dueDateText = String.format("%s: %s",
                currentContext.getString(R.string.due_date), task.getFormattedDueDate());
        dueDate.setText(dueDateText);

        checkBox.setTag(task);
        view.setTag(task);

        return view;
    }

    private Task getTaskObjFromCursor(Cursor cursor){
        long id = cursor.getLong(cursor.getColumnIndex(TaskSqlDatabase.ID));
        String outline = cursor.getString(cursor.getColumnIndex(TaskSqlDatabase.OUTLINE));
        String extraDetails = cursor.getString(cursor.getColumnIndex(TaskSqlDatabase.EXTRA_DETAILS));

        String creationDateText = cursor.getString(cursor.getColumnIndex(TaskSqlDatabase.CREATION_DATE));
        Calendar creationDate = DateManager.getCalendarFromSqlDate(creationDateText);
        String dueDateText = cursor.getString(cursor.getColumnIndex(TaskSqlDatabase.DUE_DATE));
        Calendar dueDate = DateManager.getCalendarFromSqlDate(creationDateText);

        int completeState = cursor.getInt(cursor.getColumnIndex(TaskSqlDatabase.COMPLETE_STATE));

        return new Task(id, outline, extraDetails, creationDate, dueDate, false);
    }
}
