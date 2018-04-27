package com.example.android.messages.Helper.ui;

import android.app.Activity;
import android.app.Dialog;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.example.android.messages.R;
import com.shawnlin.numberpicker.NumberPicker;

/**
 * Created by pc on 4/26/2018.
 */

public class Dialogs {



    public static void showNumberPickerDialog(Activity activity, String msg){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.number_picker_dialog);


        final NumberPicker numberPicker = dialog.findViewById(R.id.number_picker);

        numberPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("tag", String.valueOf(numberPicker.getValue()));
                dialog.dismiss();
            }
        });

        dialog.show();
    }


}
