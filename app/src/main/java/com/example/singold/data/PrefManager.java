package com.example.singold.data;

/**
 * Created by user on 03/03/2017.
 */
import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    static SharedPreferences pref;
    static SharedPreferences.Editor editor;
    static Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "androidhive-welcome";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public static void setFirstTimeLaunch(Context context, boolean isFirstTime,String userid) {
        PrefManager._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.putString("userid",userid);
        editor.commit();
    }

    public static boolean isFirstTimeLaunch(Context context) {
        PrefManager._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, false);
    }
    public static String getUserId(Context context) {
        PrefManager._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return pref.getString("userid", null);
    }
    public static void clearAll(Context context) {
        PrefManager._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
        editor.clear();
        editor.commit();
    }
}