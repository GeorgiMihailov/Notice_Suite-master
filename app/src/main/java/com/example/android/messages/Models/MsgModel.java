package com.example.android.messages.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by pc on 4/6/2018.
 */

public class MsgModel implements Serializable {

    @SerializedName("notice_ID")
    @Expose
    int notice_ID;
    @SerializedName("datetime")
    @Expose
    long datetime;
    @SerializedName("Phone_ID")
    @Expose
    String Phone_ID;
    @SerializedName("notice_text")
    @Expose
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

    @Override
    public String toString() {
        return "MsgModel{" +
                "notice_ID=" + notice_ID +
                ", datetime=" + datetime +
                ", Phone_ID='" + Phone_ID + '\'' +
                ", notice_text='" + notice_text + '\'' +
                '}';
    }
}
