package com.example.james.todolist;

import com.example.james.todolist.helper.DateManager;

import org.junit.Test;
import org.junit.Before;

import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Created by james on 06/07/2017.
 */

public class DateManagerTest {

    @Test
    public void canValidateInputDate(){
        assertEquals(true, DateManager.isDateValid(1, 1, 2017));
    }
    @Test
    public void canValidateInputDate__DayWrong(){
        assertEquals(false, DateManager.isDateValid(0, 6, 2017));
        assertEquals(false, DateManager.isDateValid(32, 6, 2017));
    }

    @Test
    public void canValidateInputDate__MonthWrong(){
        assertEquals(false, DateManager.isDateValid(1, 0, 2017));
        assertEquals(false, DateManager.isDateValid(1, 13, 2017));
    }

    @Test
    public void canValidateInputDate__YearWrong(){
        assertEquals(false, DateManager.isDateValid(1, 6, 999));
        assertEquals(false, DateManager.isDateValid(1, 6, 10_000));
    }

    @Test
    public void canFormatDate(){
        Calendar cal = Calendar.getInstance();
        cal.set(2017, 1, 1);
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
}
