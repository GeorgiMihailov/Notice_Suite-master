package com.example.android.messages.Receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.android.messages.Service.AlarmService;

/**
 * Created by pc on 4/9/2018.
 */

public class Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent ii = new Intent(context, AlarmService.class);
        context.startService(ii);
    }
}
