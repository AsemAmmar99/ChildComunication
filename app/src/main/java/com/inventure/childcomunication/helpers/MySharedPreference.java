package com.inventure.childcomunication.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by aswany on 2/7/19.
 */

public class MySharedPreference {
    //variables
    private static Context mAppContext = null;
    private final static String mySharedPreferenceName = "data";
    private final static String mySharedPreference_userOBJ = "genderInfo";
    private final static String mySharedPreference_userColor = "colorInfo";


    private MySharedPreference() {
    }
    //methods

    public static void init(Context appContext) {
        mAppContext = appContext;
    }

    private static SharedPreferences getSharedPreferences() {
        return mAppContext.getSharedPreferences(mySharedPreferenceName, Context.MODE_PRIVATE);
    }


    public static void setGender(String userOBJSTR) {

        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(mySharedPreference_userOBJ, userOBJSTR).apply();
    }

    public static String getGender() {
        return getSharedPreferences().getString(mySharedPreference_userOBJ, "");
    }

    public static void setColor(String color) {

        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(mySharedPreference_userColor, color).apply();
    }

    public static String getColor() {
        return getSharedPreferences().getString(mySharedPreference_userColor, "");
    }




}
