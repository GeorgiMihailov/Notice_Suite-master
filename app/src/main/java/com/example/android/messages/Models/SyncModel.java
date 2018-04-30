package com.example.android.messages.Models;

import java.io.Serializable;

/**
 * Created by pc on 4/30/2018.
 */

public class SyncModel implements Serializable {
    long daily_sync_time ;
   long sync_frequency ;

    public long getDaily_sync_time() {
        return daily_sync_time;
    }

    public void setDaily_sync_time(long daily_sync_time) {
        this.daily_sync_time = daily_sync_time;
    }

    public long getSync_frequency() {
        return sync_frequency;
    }

    public void setSync_frequency(long sync_frequency) {
        this.sync_frequency = sync_frequency;
    }
}
