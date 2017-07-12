package com.example.james.todolist.helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
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
        return !(month < 0 || month > 11);
    }

    private static boolean isYearValid(int year){
        return !(year < 1000 || year > 9999);
    }

    public static String formatDateForSQL(Calendar cal) {
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH)+1;
        int year = cal.get(Calendar.YEAR);
        return String.format("%s-%02d-%02d", year, month, day);
    }

    public static Calendar getCalendarFromSqlDate(String formattedDate) {

        if(!isDateInSqlFormat(formattedDate)){
           throw new IllegalArgumentException("Date format is not yyyy-mm-dd: " + formattedDate);
        }

        String[] splitDate = formattedDate.split("-");
        int day = Integer.parseInt(splitDate[2]);
        int month = Integer.parseInt(splitDate[1])-1;
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

        Calendar cal = Calendar.getInstance();
        cal.set(year, month-1, day);
        return cal;
    }

    private static boolean isDateInSqlFormat(String date){
        Pattern ptrn = Pattern.compile("^\\d\\d\\d\\d-\\d\\d-\\d\\d$");
        Matcher match = ptrn.matcher(date);
        return match.find();
    }

    public static boolean isBeforeToday(Calendar date){
        Calendar today = Calendar.getInstance();
        return date.before(today);
    }

    public static Calendar newCalendar(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);
        return cal;
    }
}
