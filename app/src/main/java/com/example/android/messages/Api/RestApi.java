package com.example.android.messages.Api;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.android.messages.BuildConfig;
import com.example.android.messages.CheckConnection;
import com.example.android.messages.LoggingInterceptor;
import com.example.android.messages.Models.MsgModel;
import com.example.android.messages.Models.SyncModel;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pc on 4/6/2018.
 */

public class RestApi {
    public static final int request_max_time_in_seconds = 20;
    private Context activity;

    public RestApi(Context activity) {
        this.activity = activity;
    }

    public Retrofit getRetrofitInstance() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .readTimeout(request_max_time_in_seconds, TimeUnit.SECONDS)
                .connectTimeout(request_max_time_in_seconds, TimeUnit.SECONDS)
                .build();
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public ApiService request() {
        return getRetrofitInstance().create(ApiService.class);
    }

    public Call<ArrayList<MsgModel>> getMsgs() {
        return request().getMsgs();
    }
    public Call<SyncModel>  setSyncTimeAndFreq (long daily_sync_time, long sync_frequency){
        return request().setSyncTimeAndFreq(daily_sync_time, sync_frequency);
    }
    public Call<MsgModel> addNewMessage (MsgModel msgModel){
        return request().addNewMessage(msgModel);
    }
    public void checkInternet(Runnable callback){
        if (CheckConnection.CheckInternetConnectivity(activity, true, callback )){
            callback.run();
        }
    }
    public void checkInternet (Runnable callback, boolean showConnectionDialog){
        if (CheckConnection.CheckInternetConnectivity(activity,showConnectionDialog,callback))
            callback.run();
        else {
            Toast.makeText(activity, "Connection failed, please check your connection in settings", Toast.LENGTH_LONG).show();
        }
    }

    public  void  checkInternet (Runnable callback, boolean showConnetionDialog, String message){
        if (CheckConnection.CheckInternetConnectivity(activity,showConnetionDialog,callback))
            callback.run();
        else {
            if (showConnetionDialog)
                Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
            else
                Log.d("Connection failed", "" + message);
        }
    }
}