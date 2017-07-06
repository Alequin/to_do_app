package com.example.james.todolist;

import com.example.james.todolist.model.Task;

import org.junit.Test;
import org.junit.Before;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by james on 06/07/2017.
 */

public class TaskTest {

    private Task task1;

    @Before
    public void setup(){
        Calendar today = Calendar.getInstance();
        Calendar due = Calendar.getInstance();
        due.set(2017, 7, 13);

        task1 = new Task("finish project", "complete the to do list app", today, due, false);
    }

    @Test
    public void canCheckIfTaskIsDue__IsOverdue(){
        assertEquals(false, task1.isOverdue());
    }

    @Test
    public void canCheckIfTaskIsDue__IsNotOverdue(){
        Calendar creationDate = Calendar.getInstance();
        Calendar dueDate = Calendar.getInstance();
        creationDate.set(2017, 1, 1);
        dueDate.set(2017, 2, 1);
        task1.setCreationDate(creationDate);
        task1.setDueDate(dueDate);
        assertEquals(true, task1.isOverdue());
    }
}
