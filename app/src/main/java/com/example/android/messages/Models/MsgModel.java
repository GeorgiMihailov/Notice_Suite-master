package com.example.android.messages.Models;

import java.io.Serializable;

/**
 * Created by pc on 4/6/2018.
 */

public class MsgModel implements Serializable {
    int notice_ID;
    long datetime;
    String Phone_ID;
    String notice_text;



    public MsgModel() {
    }


    public int getNotice_ID() {
        return notice_ID;
    }

    public void setNotice_ID(int notice_ID) {
        this.notice_ID = notice_ID;
    }

    public MsgModel(String Phone_ID, String notice_text, long datetime) {
        this.Phone_ID = Phone_ID;
        this.notice_text = notice_text;
        this.datetime = datetime;
    }

    public String getPhone_ID() {
        return Phone_ID;
    }

    public String getNotice_text() {
        return notice_text;
    }

    public long getDatetime() {
        return datetime;
    }

    public void setPhone_ID(String phone_ID) {
        this.Phone_ID = phone_ID;
    }

    public void setNotice_text(String notice_text) {
        this.notice_text = notice_text;
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }
}
