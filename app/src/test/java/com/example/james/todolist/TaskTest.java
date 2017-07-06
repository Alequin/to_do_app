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
        Calendar creationDate = Calendar.getInstance();
        creationDate.set(2017, 7, 6);
        Calendar due = Calendar.getInstance();
        due.set(2017, 7, 13);

        task1 = new Task("finish project", "complete the to do list app", creationDate, due, false);
    }

    @Test
    public void canCheckIfTaskIsDue__IsOverdue(){
        assertEquals(false, task1.isOverdue());
    }

    @Test
    public void canCheckIfTaskIsDue__IsNotOverdue(){
        task1.setCreationDate(1, 1, 2017);
        task1.setDueDate(1, 2, 2017);
        assertEquals(true, task1.isOverdue());
    }

    @Test
    public void canGetCopiedDates(){
        Calendar creationDateCopy = task1.getDueDate();
        assertEquals(true, creationDateCopy != task1.getCreationDate());
        Calendar dueDateCopy = task1.getDueDate();
        assertEquals(true, dueDateCopy != task1.getDueDate());
    }

    @Test
    public void canGetFormattedDates(){
        String expected = "13/07/2017";
        assertEquals(expected, task1.getFormattedDueDate());
        expected = "06/07/2017";
        assertEquals(expected, task1.getFormattedCreationDate());
    }

    @Test
    public void cannotSetOutOfRangeDay(){
        boolean pass = false;
        try{
            task1.setCreationDate(0, 06, 2017);
        }catch(IllegalArgumentException ex){
            pass = true;
        }
        assertEquals(true, pass);

        pass = false;
        try{
            task1.setDueDate(32, 06, 2017);
        }catch(IllegalArgumentException ex){
            pass = true;
        }
        assertEquals(true, pass);
    }

    @Test
    public void cannotSetOutOfRangeMonth(){

    }

    @Test
    public void cannotSetOutOfRangeYear(){

    }
}
