package com.example.james.todolist;

import com.example.james.todolist.helper.DateManager;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

/**
 * Created by james on 06/07/2017.
 */

public class DateManagerTest {

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
}
