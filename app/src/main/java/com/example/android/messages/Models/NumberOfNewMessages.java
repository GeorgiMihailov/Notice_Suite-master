package com.example.android.messages.Models;

import java.io.Serializable;

/**
 * Created by pc on 4/30/2018.
 */

public class NumberOfNewMessages implements Serializable {
    int new_messages;

    public int getNew_messages() {
        return new_messages;
    }

    public void setNew_messages(int new_messages) {
        this.new_messages = new_messages;
    }
}
