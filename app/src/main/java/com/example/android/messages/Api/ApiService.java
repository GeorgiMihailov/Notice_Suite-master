package com.example.android.messages.Api;

import com.example.android.messages.Models.MsgModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by pc on 4/6/2018.
 */

public interface ApiService {
   @GET("getsmsevents?notice_id=1")
  Call<ArrayList<MsgModel>> getMsgs();

}
