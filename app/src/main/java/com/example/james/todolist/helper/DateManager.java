package com.example.james.todolist.helper;

import java.util.Calendar;

/**
 * Created by james on 06/07/2017.
 */

public class DateManager {

    public static String formatDate(Calendar cal){
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        return String.format("%02d/%02d/%s", day, month, year);
    }

    public static boolean isDateValid(int day, int month, int year) {
        return isDayValid(day) && isMonthValid(month) && isYearValid(year);
    }

    private static boolean isDayValid(int day){
        return !(day < 1 || day > 31);
    }

    private static boolean isMonthValid(int month){
        return !(month < 1 || month > 12);
    }

    private static boolean isYearValid(int year){
        return !(year < 1000 || year > 9999);
    }
}
