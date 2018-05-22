package com.luosu.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.luosu.entity.Users;

/**
 * Created by User on 2017/3/7.
 */

public class PrefUtils {
    public static final String userName = "userName";
    public static final String user_messages = "user_messages";
    public static final String GOLD_NUM = "GOLD_NUM";
    public static final String AGE = "age";
    public static final String USERID = "USERID";
    public static final String USERPASSWORD = "USERPASSWORD";
    public static final String TOKEN = "TOKEN";

    // 备用数据1
    private static final String RESEVER1 = "RESEVER1";
    // 备用数据2
    private static final String RESEVER2 = "RESEVER2";

    SharedPreferences sp;

    public PrefUtils(Context context, String name) {
        sp = context.getSharedPreferences(name, Activity.MODE_PRIVATE);


    }

    public void setUser(Users user) {
        setString(userName,user.getUserName());
        setString(USERPASSWORD,user.getUserPassword());
        setString(USERID,user.getUserId()+"");

        setString(TOKEN,user.getToken()+"");
    }

    public void setString(String key, String value) {

        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
        editor.clear();
    }

    public String getString(String key) {
        return
                sp.getString(key, "");

    }

    public void setint(String key, int value) {

        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.commit();
        editor.clear();
    }

    public int getint(String key) {
        return
                sp.getInt(key, 0);

    }
}
