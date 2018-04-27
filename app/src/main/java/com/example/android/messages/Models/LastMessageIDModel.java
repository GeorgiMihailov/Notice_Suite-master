package com.example.android.messages.Models;

import java.io.Serializable;

/**
 * Created by pc on 4/24/2018.
 */

public class LastMessageIDModel implements Serializable {
    int notice_ID;

    public int getNotice_ID() {
        return notice_ID;
    }

    public void setNotice_ID(int notice_ID) {
        this.notice_ID = notice_ID;
    }
}
