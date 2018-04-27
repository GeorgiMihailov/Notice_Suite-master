package com.example.android.messages.Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by pc on 4/24/2018.
 */

public class NewMessagesModel implements Serializable {
    ArrayList<Integer> new_message_notice_ID;

    public ArrayList<Integer> getNew_message_notice_ID() {
        return new_message_notice_ID;
    }

    public void setNew_message_notice_ID(ArrayList<Integer> new_message_notice_ID) {
        this.new_message_notice_ID = new_message_notice_ID;
    }
}
