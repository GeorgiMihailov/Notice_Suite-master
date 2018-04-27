package com.example.android.messages.Service;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.android.messages.Adapters.CustomAdapter;
import com.example.android.messages.Api.RestApi;
import com.example.android.messages.Models.MessagesList;
import com.example.android.messages.Models.MsgModel;
import com.example.android.messages.Preferences.PreferencesManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by pc on 4/27/2018.
 */

public class SyncService extends Service {

    RestApi api;
    private boolean isRunning;
    private Context context;
    MessagesList modelList;
    ArrayList<MsgModel> msgModel;
    CustomAdapter customAdapter;




    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        this.isRunning = false;
        super.onDestroy();
    }


    @Override
    public void onCreate() {

        this.context = this;
        this.isRunning = false;
        api = new RestApi(context);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        api.checkInternet(new Runnable() {
            @Override
            public void run() {
                Call<ArrayList<MsgModel>> call = api.getMsgs();
                call.enqueue(new Callback<ArrayList<MsgModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<MsgModel>> call, Response<ArrayList<MsgModel>> response) {
                        if (response.isSuccessful()){
                            msgModel = response.body();
                            SharedPreferences preferences = getSharedPreferences("MySharedPreferencesFile", Activity.MODE_PRIVATE);
                            modelList = new MessagesList();
                            modelList.setMessages(msgModel);
                            PreferencesManager.modelList(modelList,context);
                            Toast.makeText(context,"SETUP", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<MsgModel>> call, Throwable t) {

                    }
                });
            }
        });



        return super.onStartCommand(intent, flags, startId);
    }
}

