package com.example.android.messages.Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by pc on 4/23/2018.
 */

public class MessagesList implements Serializable {
    ArrayList<MsgModel> messages;

    public ArrayList<MsgModel> getMessages() {
        return messages;
    }


    public void setMessages(ArrayList<MsgModel> messages) {
        this.messages = messages;
    }
}
