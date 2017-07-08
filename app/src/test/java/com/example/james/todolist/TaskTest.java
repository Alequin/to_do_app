package com.example.james.todolist;

import com.example.james.todolist.model.Task;

import org.junit.Test;
import org.junit.Before;

import java.util.Calendar;

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
        task1.setCreationDate(2017, 1, 1);
        task1.setDueDate(2017, 2, 1);
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
    public void canSetCorrectDate(){
        task1.setCreationDate(2017, 1, 1);
        task1.setDueDate(2018,5,5);
        String expectedCreationDate = "01/01/2017";
        String expectedDueDate = "05/05/2018";

        assertEquals(expectedCreationDate, task1.getFormattedCreationDate());
        assertEquals(expectedDueDate, task1.getFormattedDueDate());
    }

    @Test
    public void cannotSetOutOfRangeDay(){
        boolean pass = false;
        try{
            task1.setCreationDate(2017, 6, 0);
        }catch(IllegalArgumentException ex){
            pass = true;
        }
        assertEquals(true, pass);

        pass = false;
        try{
            task1.setDueDate(2017, 6, 32);
        }catch(IllegalArgumentException ex){
            pass = true;
        }
        assertEquals(true, pass);
    }

    @Test
    public void cannotSetOutOfRangeMonth(){
        boolean pass = false;
        try{
            task1.setCreationDate(2017, 0, 1);
        }catch(IllegalArgumentException ex){
            pass = true;
        }
        assertEquals(true, pass);

        pass = false;
        try{
            task1.setDueDate(2017, 32, 1);
        }catch(IllegalArgumentException ex){
            pass = true;
        }
        assertEquals(true, pass);
    }

    @Test
    public void cannotSetOutOfRangeYear(){
        boolean pass = false;
        try{
            task1.setCreationDate(999, 6, 1);
        }catch(IllegalArgumentException ex){
            pass = true;
        }
        assertEquals(true, pass);

        pass = false;
        try{
            task1.setDueDate(10_000, 6, 1);
        }catch(IllegalArgumentException ex){
            pass = true;
        }
        assertEquals(true, pass);
    }

    @Test
    public void cannotSetDueDateThatIsBeforeCreationDate(){
        boolean pass = false;
        try{
            task1.setDueDate(2017, 7, 5);
        }catch(IllegalArgumentException ex){
           pass = true;
        }
        assertEquals(true, pass);
    }
}
