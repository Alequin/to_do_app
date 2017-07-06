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
        boolean pass = false;
        try{
            DateManager.isDateValid(0, 6, 2017);
        }catch(IllegalArgumentException ex){
            pass = true;
        }
        assertEquals(true, pass);

        pass = false;
        try{
            DateManager.isDateValid(32, 6, 2017);
        }catch(IllegalArgumentException ex){
            pass = true;
        }
        assertEquals(true, pass);
    }

    @Test
    public void canValidateInputDate__MonthWrong(){
        boolean pass = false;
        try{
            DateManager.isDateValid(1, 0, 2017);
        }catch(IllegalArgumentException ex){
            pass = true;
        }
        assertEquals(true, pass);

        pass = false;
        try{
            DateManager.isDateValid(1, 13, 2017);
        }catch(IllegalArgumentException ex){
            pass = true;
        }
        assertEquals(true, pass);
    }

    @Test
    public void canValidateInputDate__YearWrong(){
        boolean pass = false;
        try{
            DateManager.isDateValid(1, 6, 999);
        }catch(IllegalArgumentException ex){
            pass = true;
        }
        assertEquals(true, pass);

        pass = false;
        try{
            DateManager.isDateValid(1, 6, 10_000);
        }catch(IllegalArgumentException ex){
            pass = true;
        }
        assertEquals(true, pass);
    }
}
