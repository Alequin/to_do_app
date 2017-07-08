package com.example.james.todolist.helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

/**
 * Created by james on 06/07/2017.
 */

public class DateManager {

    public static String formatDate(Calendar cal){
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        return String.format("%02d/%02d/%s", day, month+1, year);
    }

    public static boolean isDateValid(int year, int month, int day) {
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

    public static String formatDateForSQL(Calendar cal) {
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        return String.format("%s-%02d-%02d", year, month, day);
    }

    public static Calendar getCalendarFromSqlDate(String formattedDate) {

        String[] splitDate = formattedDate.split("-");
        int day = Integer.parseInt(splitDate[2]);
        int month = Integer.parseInt(splitDate[1]);
        int year = Integer.parseInt(splitDate[0]);
        if(!isDateValid(year, month, day)){
            throw new IllegalArgumentException(
                    String.format("Invalid date entered: day - %s, month - %s, year - %s",
                    day, month, year)
            );
        }

        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);
        return cal;
    }

    public static Calendar getCalendarFromLong(long dateAsLong){
        Date date = new Date(dateAsLong);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String[] splitDate = sdf.format(date).split("-");
        int day = Integer.parseInt(splitDate[2]);
        int month = Integer.parseInt(splitDate[1]);
        int year = Integer.parseInt(splitDate[0]);

        return new GregorianCalendar(year, month-1, day);
    }
}
