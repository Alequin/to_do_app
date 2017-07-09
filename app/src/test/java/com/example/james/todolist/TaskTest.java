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
        Calendar due = task1.getDueDate();
        String expected = String.format("%02d/%02d/%s",
                due.get(Calendar.DAY_OF_MONTH), due.get(Calendar.MONTH)+1, due.get(Calendar.YEAR)
        );
        assertEquals(expected, task1.getFormattedDueDate());

        Calendar creation = task1.getCreationDate();
        expected = String.format("%02d/%02d/%s",
                creation.get(Calendar.DAY_OF_MONTH), creation.get(Calendar.MONTH)+1, creation.get(Calendar.YEAR)
        );
        assertEquals(expected, task1.getFormattedCreationDate());
    }

    @Test
    public void canSetCorrectDate(){

        for(int j=0; j<12; j++){
            helperCanSetCorrectDate(2017, j, 1);
        }

        for(int j=1; j<=31; j++){
            helperCanSetCorrectDate(2017, 0, j);
        }
    }

    private void helperCanSetCorrectDate(int year, int month, int day){
        task1.setCreationDate(year, month, day);
        task1.setDueDate(year+1,month, day);

        Calendar creation = task1.getCreationDate();
        Calendar due = task1.getDueDate();

        assertEquals(year, creation.get(Calendar.YEAR));
        assertEquals(year+1, due.get(Calendar.YEAR));

        assertEquals(month, creation.get(Calendar.MONTH));
        assertEquals(month, due.get(Calendar.MONTH));

        assertEquals(day, creation.get(Calendar.DAY_OF_MONTH));
        assertEquals(day, due.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void cannotSetOutOfRangeDay(){
        helperCannotSetWrongDate(2017, 6, 0);
        helperCannotSetWrongDate(2017, 6, 32);
    }

    @Test
    public void cannotSetOutOfRangeMonth(){
        helperCannotSetWrongDate(2017, -1, 1);
        helperCannotSetWrongDate(2017, 12, 1);
    }

    @Test
    public void cannotSetOutOfRangeYear(){
        helperCannotSetWrongDate(999, 6, 1);
        helperCannotSetWrongDate(10_000, 6, 1);
    }

    private void helperCannotSetWrongDate(int year, int month, int day){
        boolean pass = false;
        try{
            task1.setCreationDate(year, month, day);
        }catch(IllegalArgumentException ex){
            pass = true;
        }
        assertEquals(true, pass);

        pass = false;
        try{
            task1.setDueDate(year, month, day);
        }catch(IllegalArgumentException ex){
            pass = true;
        }
        assertEquals(true, pass);
    }

    @Test
    public void cannotSetDueDateThatIsBeforeCreationDate(){
        boolean pass = false;

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1);

        try{
            task1.setDueDate(cal);
        }catch(IllegalArgumentException ex){
           pass = true;
        }
        assertEquals(true, pass);

        pass = false;
        try{
            task1.setDueDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        }catch(IllegalArgumentException ex){
           pass = true;
        }
        assertEquals(true, pass);
    }
}
