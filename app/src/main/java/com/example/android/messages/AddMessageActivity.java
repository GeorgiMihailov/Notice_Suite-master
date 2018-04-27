package com.example.android.messages;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.android.messages.Fragments.AddMessageFragment;


public class AddMessageActivity extends AppCompatActivity {
    Boolean mShouldFinish = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.placeholder, new AddMessageFragment());
        transaction.disallowAddToBackStack();
        transaction.commit();


}


}

