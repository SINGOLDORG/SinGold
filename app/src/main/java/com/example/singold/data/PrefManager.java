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

    // Shared preferences file name
    public static final String PREF_NAME = "myfile";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public static void setFirstTimeLaunch(Context context, boolean isFirstTime,String userid) {
        PrefManager._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.putString("id",userid);
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
        return pref.getString("id", null);
    }
    public static boolean isStayLogIn(Context context) {

        PrefManager._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return pref.getBoolean("stayLogin", false);
    }
    public static void clearAll(Context context) {
        PrefManager._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
        editor.clear();
        editor.commit();
    }

    public static void setUserLogin(Context context,MyUser myUser,boolean stayLogin) {
        SharedPreferences preferences = context.getSharedPreferences("myfile", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("stayLogin",stayLogin);
        editor.putString("username", myUser.getUsername());
        editor.putString("EnterId", myUser.getEnterId());
        editor.putString("id",myUser.getId());
        editor.commit();
    }
}