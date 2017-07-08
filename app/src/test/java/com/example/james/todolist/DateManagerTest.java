package com.example.james.todolist;

import com.example.james.todolist.helper.DateManager;

import org.junit.Test;
import org.junit.Before;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by james on 06/07/2017.
 */

public class DateManagerTest {

    @Test
    public void canValidateInputDate(){
        assertEquals(true, DateManager.isDateValid(2017, 1, 1));
    }
    @Test
    public void canValidateInputDate__DayWrong(){
        assertEquals(false, DateManager.isDateValid(2017, 6, 0));
        assertEquals(false, DateManager.isDateValid(2017, 6, 32));
    }

    @Test
    public void canValidateInputDate__MonthWrong(){
        assertEquals(false, DateManager.isDateValid(2017, 0, 1));
        assertEquals(false, DateManager.isDateValid(2017, 13, 1));
    }

    @Test
    public void canValidateInputDate__YearWrong(){
        assertEquals(false, DateManager.isDateValid(999, 6, 1));
        assertEquals(false, DateManager.isDateValid(10_000, 6, 1));
    }

    @Test
    public void canFormatDate(){
        Calendar cal = Calendar.getInstance();
        cal.set(2017, 0, 1);
        String expected = "01/01/2017";
        assertEquals(expected, DateManager.formatDate(cal));
    }

    @Test
    public void canFormatDateForSQL(){
        Calendar cal = Calendar.getInstance();
        cal.set(2017, 5, 1);
        String expected = "2017-05-01";
        assertEquals(expected, DateManager.formatDateForSQL(cal));
    }

    @Test
    public void canGetCalendarObjFromSqlFormattedDate(){
        Calendar cal = Calendar.getInstance();
        cal.set(2017, 5, 1);
        String formattedDate = DateManager.formatDateForSQL(cal);
        assert(cal.equals(DateManager.getCalendarFromSqlDate(formattedDate)));
    }

    @Test
    public void cannotConvertTextDateToCalendarWhenInputWrong__DayWrong(){
        boolean pass = false;
        try{
            DateManager.getCalendarFromSqlDate("2017-05-0");
        }catch(IllegalArgumentException ex){
            pass = true;
        }
        assertEquals(true, pass);

        pass = false;
        try{
            DateManager.getCalendarFromSqlDate("2017-05-32");
        }catch(IllegalArgumentException ex){
            pass = true;
        }
        assertEquals(true, pass);
    }

    @Test
    public void cannotConvertTextDateToCalendarWhenInputWrong__MonthWrong(){
        boolean pass = false;
        try{
            DateManager.getCalendarFromSqlDate("2017-0-01");
        }catch(IllegalArgumentException ex){
            pass = true;
        }
        assertEquals(true, pass);

        pass = false;
        try{
            DateManager.getCalendarFromSqlDate("2017-13-01");
        }catch(IllegalArgumentException ex){
            pass = true;
        }
        assertEquals(true, pass);
    }

    @Test
    public void cannotConvertTextDateToCalendarWhenInputWrong__YearWrong(){
        boolean pass = false;
        try{
            DateManager.getCalendarFromSqlDate("999-05-01");
        }catch(IllegalArgumentException ex){
            pass = true;
        }
        assertEquals(true, pass);

        pass = false;
        try{
            DateManager.getCalendarFromSqlDate("10000-05-01");
        }catch(IllegalArgumentException ex){
            pass = true;
        }
        assertEquals(true, pass);
    }

    @Test
    public void canGetCalendarObjFromLongValue(){
        Date date = new Date();
        Calendar expected = Calendar.getInstance();
        assertEquals(expected.get(Calendar.DAY_OF_MONTH), DateManager.getCalendarFromLong(date.getTime()).get(Calendar.DAY_OF_MONTH));
        assertEquals(expected.get(Calendar.MONTH), DateManager.getCalendarFromLong(date.getTime()).get(Calendar.MONTH));
        assertEquals(expected.get(Calendar.YEAR), DateManager.getCalendarFromLong(date.getTime()).get(Calendar.YEAR));
    }
}
