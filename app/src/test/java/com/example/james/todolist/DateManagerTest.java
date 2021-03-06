package com.example.james.todolist;

import com.example.james.todolist.helper.DateManager;

import org.junit.Test;

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
    public void cannotValidateInputDate__DayWrong(){
        assertEquals(false, DateManager.isDateValid(2017, 6, 0));
        assertEquals(false, DateManager.isDateValid(2017, 6, 32));
    }

    @Test
    public void cannotValidateInputDate__MonthWrong(){
        assertEquals(false, DateManager.isDateValid(2017, -1, 1));
        assertEquals(false, DateManager.isDateValid(2017, 12, 1));
    }

    @Test
    public void cannotValidateInputDate__YearWrong(){
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
        cal.set(2017, 4, 1);
        String expected = "2017-05-01";
        assertEquals(expected, DateManager.formatDateForSQL(cal));
    }

    @Test
    public void canGetCalendarObjFromSqlFormattedDate(){
        Calendar expected = Calendar.getInstance();
        expected.set(2017, 5, 1);

        String formattedDate = DateManager.formatDateForSQL(expected);
        Calendar result = DateManager.getCalendarFromSqlDate(formattedDate);

        assertEquals(expected.get(Calendar.DAY_OF_MONTH), result.get(Calendar.DAY_OF_MONTH));
        assertEquals(expected.get(Calendar.MONTH), result.get(Calendar.MONTH));
        assertEquals(expected.get(Calendar.YEAR), result.get(Calendar.YEAR));
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

    @Test
    public void canCheckIfDateIsBeforeToday(){
        Calendar date = Calendar.getInstance();

        date.add(Calendar.DAY_OF_MONTH, -1);
        assertEquals(true, DateManager.isBeforeToday(date));

        date.add(Calendar.DAY_OF_MONTH, 2);
        assertEquals(false, DateManager.isBeforeToday(date));
    }

    @Test
    public void canGetNewCalendar(){

        int min = 0;
        int max = 11;
        for(int j=min; j<=max; j++){
            helperCanGetNewCalendar(2017, j, 1);
        }

        min = 1;
        max = 31;
        for(int j=min; j<=max; j++){
            helperCanGetNewCalendar(2017, 6, j);
        }
    }

    private void helperCanGetNewCalendar(int year, int month, int day){

        Calendar cal = DateManager.newCalendar(year, month, day);

        assertEquals(day, cal.get(Calendar.DAY_OF_MONTH));
        assertEquals(month, cal.get(Calendar.MONTH));
        assertEquals(year, cal.get(Calendar.YEAR));
    }

    @Test
    public void cannotGetNewCalendar_wrongInput(){
        helperCannotGetNewCalendar(2017, 6, 0);
        helperCannotGetNewCalendar(2017, 6, 32);

        helperCannotGetNewCalendar(2017, -1, 1);
        helperCannotGetNewCalendar(2017, 12, 1);

        helperCannotGetNewCalendar(999, 6, 1);
        helperCannotGetNewCalendar(10_000, 6, 1);
    }

    private void helperCannotGetNewCalendar(int year, int month, int day){

        boolean pass = false;
        try{
            Calendar cal = DateManager.newCalendar(year, month, day);
        }catch (IllegalArgumentException ex){
            pass = true;
        }

        assertEquals(true, pass);
    }
}
