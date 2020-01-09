package com.techastrum.myappcreater.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

    private static Prefs prefs;
    private SharedPreferences sharedPreferences;

    public static Prefs getInstance(Context context) {
        if (prefs == null) {
            prefs = new Prefs(context);
        }
        return prefs;
    }

    public Prefs(Context context) {
        String android = "android";
        sharedPreferences = context.getSharedPreferences(android,Context.MODE_PRIVATE);
    }

    public void SetValue(String key,String value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor .putString(key, value);
        prefsEditor.apply();
    }

    public String GetValue(String key) {
        if (sharedPreferences!= null) {
            return sharedPreferences.getString(key, "");
        }
        return "";
    }
}
