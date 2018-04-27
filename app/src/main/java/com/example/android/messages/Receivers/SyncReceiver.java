package com.example.android.messages.Receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.android.messages.Service.SyncService;

/**
 * Created by pc on 4/27/2018.
 */

public class SyncReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent ii = new Intent(context, SyncService.class);
            context.startService(ii);

    }
}
