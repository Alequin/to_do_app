package com.example.james.todolist.helper;

import android.util.DisplayMetrics;

/**
 * Created by Alequin on 12/10/2016.
 * updated on 18/12/16
 */

public class UnitConverter {

    public static float dpToPixel(DisplayMetrics displayMetrics, float dp){
        return  dp * displayMetrics.density;
    }

    public static float pixelToDp(DisplayMetrics displayMetrics, float pixels){
        return pixels / displayMetrics.density;
    }
}
