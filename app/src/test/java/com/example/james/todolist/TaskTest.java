package com.example.james.todolist;

import com.example.james.todolist.model.Task;

import org.junit.Test;
import org.junit.Before;

import java.util.Calendar;
import java.util.GregorianCalendar;

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
        due.set(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH));
        due.add(Calendar.DAY_OF_MONTH, 5);

        task1 = new Task("finish project", "complete the to do list app", today, due, false);
    }

    @Test
    public void canCheckIfTaskIsDue__IsNotOverdue(){
        assertEquals(false, task1.isOverdue());
    }

    @Test
    public void canCheckIfTaskIsDue__DateMatch(){
        task1.setDueDate(Calendar.getInstance());
        assertEquals(false, task1.isOverdue());
    }

    @Test
    public void canCheckIfTaskIsDue__IsOverdue(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        //creation date set as well as due date cannot be before creation date
        task1.setCreationDate(cal);
        task1.setDueDate(cal);
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
        String expected = "13/08/2017";
        assertEquals(expected, task1.getFormattedDueDate());
        expected = "06/08/2017";
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
