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
        int min = 0;
        int max = 11;
        for(int j=min; j<=max; j++){
            assertEquals(true, DateManager.isDateValid(2017, j, 1));
        }

        min = 1;
        max = 31;
        for(int j=min; j<=max; j++){
            assertEquals(true, DateManager.isDateValid(2017, 6, j));
        }
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
        assertEquals(true, cal.equals(DateManager.getCalendarFromSqlDate(formattedDate)));
    }

    @Test
    public void cannotConvertTextDateToCalendarWhenInputWrong__DayWrong(){
        helperCannotConvertTextDateToCalendarWhenInputWrong("2017-05-00");
        helperCannotConvertTextDateToCalendarWhenInputWrong("2017-05-32");

        helperCannotConvertTextDateToCalendarWhenInputWrong("2017-05-");
        helperCannotConvertTextDateToCalendarWhenInputWrong("2017-05-0");
        helperCannotConvertTextDateToCalendarWhenInputWrong("2017-05-000");
    }

    @Test
    public void cannotConvertTextDateToCalendarWhenInputWrong__MonthWrong(){
        helperCannotConvertTextDateToCalendarWhenInputWrong("2017-00-01");
        helperCannotConvertTextDateToCalendarWhenInputWrong("2017-13-01");

        helperCannotConvertTextDateToCalendarWhenInputWrong("2017--01");
        helperCannotConvertTextDateToCalendarWhenInputWrong("2017-0-01");
        helperCannotConvertTextDateToCalendarWhenInputWrong("2017-000-01");
    }

    @Test
    public void cannotConvertTextDateToCalendarWhenInputWrong__YearWrong(){
        helperCannotConvertTextDateToCalendarWhenInputWrong("999-06-01");
        helperCannotConvertTextDateToCalendarWhenInputWrong("10000-06-01");
    }

    @Test
    public void cannotConvertTextDateToCalendarWhenInputWrong__FormatWrong() {
        helperCannotConvertTextDateToCalendarWhenInputWrong("01-01-2017");
        helperCannotConvertTextDateToCalendarWhenInputWrong("01-2017-01");

        helperCannotConvertTextDateToCalendarWhenInputWrong("01/01/2017");
        helperCannotConvertTextDateToCalendarWhenInputWrong("01/2017/01");
        helperCannotConvertTextDateToCalendarWhenInputWrong("2017/01/01");

        helperCannotConvertTextDateToCalendarWhenInputWrong("01.01.2017");
        helperCannotConvertTextDateToCalendarWhenInputWrong("01.201701");
        helperCannotConvertTextDateToCalendarWhenInputWrong("2017.01.01");
    }

    private void helperCannotConvertTextDateToCalendarWhenInputWrong(String input){
        boolean pass = false;
        try{
            DateManager.getCalendarFromSqlDate("input");
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
