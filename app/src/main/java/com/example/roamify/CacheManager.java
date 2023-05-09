package com.example.roamify;

import android.content.Context;
import android.content.SharedPreferences;

public class CacheManager {
    private static final String PREFS_NAME = "MyPrefsFile";

    public static void saveData(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS_NAME, 0).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getData(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getString(key, null);
    }

    public static void clearData(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS_NAME, 0).edit();
        editor.clear();
        editor.apply();
    }
}
