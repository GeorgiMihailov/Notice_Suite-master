package com.example.android.messages.Service;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.example.android.messages.Preferences.PreferencesManager;

/**
 * Created by pc on 4/9/2018.
 */

public class AlarmService extends Service {
    private boolean isRunning;
    private Context context;
    private String phone;
    private String txt;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        this.context = this;
        this.isRunning = false;


        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        SharedPreferences preferences = getSharedPreferences("MySharedPreferencesFile", Activity.MODE_PRIVATE);
        phone = PreferencesManager.getPhone(AlarmService.this);
        txt = PreferencesManager.getText(AlarmService.this);
        SmsManager.getDefault().sendTextMessage(phone, null, txt, null, null);
        Toast.makeText(AlarmService.this, "Sms sent", Toast.LENGTH_LONG).show();
        stopSelf();


        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        this.isRunning = false;
        super.onDestroy();
    }

}
