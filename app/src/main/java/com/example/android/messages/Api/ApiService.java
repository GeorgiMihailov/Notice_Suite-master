package com.example.android.messages.Api;

import com.example.android.messages.Models.MsgModel;
import com.example.android.messages.Models.SyncModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by pc on 4/6/2018.
 */

public interface ApiService {
   @GET("getsmsevents?notice_id=1")
  Call<ArrayList<MsgModel>> getMsgs();

   @POST("messages")
   Call<MsgModel> addNewMessage (@Body MsgModel msgModel);
    @FormUrlEncoded
   @POST("sync_time")
    Call<SyncModel> setSyncTimeAndFreq(@Field("daily_sync_time") long daily_sync_time,@Field("sync_frequency") long sync_frequency);

}
