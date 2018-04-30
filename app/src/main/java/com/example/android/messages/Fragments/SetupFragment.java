package com.example.android.messages.Fragments;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.messages.Api.RestApi;
import com.example.android.messages.MessagesActivity;
import com.example.android.messages.Models.MessagesList;
import com.example.android.messages.Models.MsgModel;
import com.example.android.messages.Models.SyncModel;
import com.example.android.messages.Preferences.PreferencesManager;
import com.example.android.messages.R;
import com.example.android.messages.Receivers.SyncReceiver;
import com.philliphsu.bottomsheetpickers.time.grid.GridTimePickerDialog;
import com.shawnlin.numberpicker.NumberPicker;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by pc on 4/22/2018.
 */

public class SetupFragment extends android.support.v4.app.Fragment implements GridTimePickerDialog.OnTimeSetListener {
    private Unbinder mUnnbinder;
    @BindView(R.id.daily_sync_time_BTN)
    Button mDailySyncBtn;
    @BindView(R.id.daily_sync_TEXT)
    TextView mDailySyncText;
    @BindView(R.id.sync_frequency_BTN)
    Button mSyncFrequency;
    @BindView(R.id.sync_now)
    Button mSyncNowBtn;
    SyncModel syncModel;
    PendingIntent pendingIntent;
    RestApi api;
    MessagesList modelList;
    ArrayList<MsgModel> msgModel;

    long time;
    String SENT = "com.android.SYNCRECEIVER_ACTION";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        api = new RestApi(getContext());

        View view = inflater.inflate(R.layout.setup_fragment_layout, null);
        mUnnbinder = ButterKnife.bind(this, view);

        ((MessagesActivity) getActivity()).hideFloatingActionButton();


        mDailySyncBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setmTime();

            }
        });
        mSyncNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                api.checkInternet(new Runnable() {
                    @Override
                    public void run() {
                        Call<ArrayList<MsgModel>> call = api.getMsgs();
                        call.enqueue(new Callback<ArrayList<MsgModel>>() {
                            @Override
                            public void onResponse(Call<ArrayList<MsgModel>> call, Response<ArrayList<MsgModel>> response) {
                                if (response.isSuccessful()){
                                    msgModel = response.body();
                                    modelList = new MessagesList();
                                    modelList.setMessages(msgModel);
                                    PreferencesManager.modelList(modelList,getContext());
                                    Toast.makeText(getContext(),"SETUP", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ArrayList<MsgModel>> call, Throwable t) {

                            }
                        });
                    }
                });
            }
        });
        mSyncFrequency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.number_picker_dialog);


                final NumberPicker numberPicker = dialog.findViewById(R.id.number_picker);

                numberPicker.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        long interval = numberPicker.getValue() * 3600000 + 6;
                        Intent myIntent = new Intent(getActivity(), SyncReceiver.class);
                        myIntent.setAction(SENT);
                        pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, myIntent, 0);
                        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(getActivity().ALARM_SERVICE);
                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, interval, pendingIntent);
                        dialog.dismiss();
                        Toast.makeText(getContext(), "Sync is set", Toast.LENGTH_LONG).show();
                        Call<SyncModel> call = api.setSyncTimeAndFreq(time,interval);
                        call.enqueue(new Callback<SyncModel>() {
                            @Override
                            public void onResponse(Call<SyncModel> call, Response<SyncModel> response) {
                                if (response.isSuccessful()){
                                    Toast.makeText(getContext(), "POST SUCCESSFUL", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<SyncModel> call, Throwable t) {

                            }
                        });



                    }
                });

                dialog.show();

            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnnbinder.unbind();
        ((MessagesActivity) getActivity()).showFloatingActionButton();
    }


    public void setmTime() {
        Calendar now = Calendar.getInstance();

        GridTimePickerDialog grid = new GridTimePickerDialog.Builder(
                this,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                DateFormat.is24HourFormat(getActivity()))
                /* ... Set additional options ... */
                .build();
        grid.show(getFragmentManager(), "");
        grid.setHeaderColor(getResources().getColor(R.color.colorAccent));
        grid.setAccentColor(getResources().getColor(R.color.colorAccent));


    }

    @Override
    public void onTimeSet(ViewGroup viewGroup, int hourOfDay, int minute) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        time = calendar.getTimeInMillis();


        mDailySyncText.setText("Daily sycn time set at : " + hourOfDay + " : " + minute);
    }
}
