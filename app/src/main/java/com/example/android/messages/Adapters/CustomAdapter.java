package com.example.android.messages.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.messages.Api.RestApi;
import com.example.android.messages.Models.MsgModel;
import com.example.android.messages.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pc on 4/6/2018.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

    Context context;
   ArrayList<MsgModel> msgModel;

    RestApi api;

    public CustomAdapter (Context context, ArrayList<MsgModel> msgModels){
        this.context=context;
        this.msgModel=msgModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        api = new RestApi(context);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        MsgModel model = msgModel.get(position);
        holder.accNum.setText(model.getPhone_ID());

        String text = model.getNotice_text();
        text = text.replace("\n", "").replace("\r", "");
        holder.msgTxt.setText(text);
        holder.msgTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.msgTxt.getMaxLines() == 1) {
                    holder.msgTxt.setMaxLines(10);
                }else  if (holder.msgTxt.getMaxLines() == 10){
                    holder.msgTxt.setMaxLines(1);
                }
            }
        });


        long sendAt = (model.getDatetime());
        String strLong = Long.toString(sendAt);
        holder.img.setImageDrawable(context.getResources().getDrawable(R.drawable.contact));
        }

    @Override
    public int getItemCount() {
        return msgModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.acc_img)
        ImageView img;
        @BindView(R.id.account_number)
        TextView accNum;
        @BindView(R.id.time_of_msg)
        TextView timeOfMsg;
        @BindView(R.id.layout)
        LinearLayout layout;
        @BindView(R.id.message_txt)
        TextView msgTxt;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
    public static String getDate(long milliSeconds, String dateFormat)
    {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }
}

