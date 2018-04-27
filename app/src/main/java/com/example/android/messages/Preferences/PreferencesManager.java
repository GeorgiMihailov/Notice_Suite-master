package com.example.android.messages.Preferences;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.android.messages.Models.MessagesList;
import com.example.android.messages.Models.MsgModel;
import com.example.android.messages.Models.TimeInfo;
import com.google.gson.Gson;

/**
 * Created by pc on 4/8/2018.
 */

public class PreferencesManager {


    private static SharedPreferences getPreferences(Context context) {
        return context.getApplicationContext().getSharedPreferences("MySharedPreferencesFile", Activity.MODE_PRIVATE);
    }

    public static void addTimeInfo(TimeInfo timeInfo, Context c) {
        Gson gson = new Gson();
        String mapStrnig = gson.toJson(timeInfo);
        getPreferences(c).edit().putString("timeInfo", mapStrnig).apply();
    }

    public static void addPhoneNumber(String phoneNumber, Context context) {
        getPreferences(context).edit().putString("phoneNumber", phoneNumber).apply();
    }

    public static void addTxtMsg(String txt, Context c) {
        getPreferences(c).edit().putString("txt", txt).apply();
    }
    public static void addDate (long calendar, Context context){
        getPreferences(context).edit().putLong("date", calendar).apply();
    }
    public static long getDate (Context context){
        return getPreferences(context).getLong("date", 0);
    }

    public static String getText(Context c) {
        return getPreferences(c).getString("txt", "");
    }

    public static String getPhone(Context c) {
        return getPreferences(c).getString("phoneNumber", "");
    }

    public static TimeInfo getTimeInfo(Context context) {
        return new Gson().fromJson(getPreferences(context).getString("timeInfo", ""), TimeInfo.class);
    }

    public static void userInfo(MsgModel userInfo, Context c) {
        Gson gson = new Gson();
        String mapStrnig = gson.toJson(userInfo);
        getPreferences(c).edit().putString("userinfo", mapStrnig).apply();
    }

    public static MsgModel getUserInfo(Context context) {
        return new Gson().fromJson(getPreferences(context).getString("userInfo", ""), MsgModel.class);
    }
    public static void modelList(MessagesList models, Context c){
        Gson gson = new Gson();
        String mapStrnig = gson.toJson(models);
        getPreferences(c).edit().putString("modelsList", mapStrnig).apply();
    }
    public static MessagesList getModelList(Context context) {
        return new Gson().fromJson(getPreferences(context).getString("modelList", ""), MessagesList.class);
    }
    public static void addSyncFreqTime (int val, Context c) {

        getPreferences(c).edit().putInt("value", val).apply();
    }
    public static int getSyncFreqTime (Context c){
        return getPreferences(c).getInt("value", 0);

    }
}
