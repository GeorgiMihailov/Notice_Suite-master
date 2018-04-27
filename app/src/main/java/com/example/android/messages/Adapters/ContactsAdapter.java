package com.example.android.messages.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.messages.R;


public class ContactsAdapter extends CursorAdapter {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private SearchView searchView;

    public ContactsAdapter(Context context, Cursor cursor, SearchView sv) {
        super(context, cursor, false);
        mContext = context;
        searchView = sv;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View v = mLayoutInflater.inflate(R.layout.list_item, parent, false);
        return v;
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {

        String name = cursor.getString(cursor.getColumnIndex(
                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));
        String phone = cursor.getString(cursor.getColumnIndex(
                ContactsContract.Contacts.HAS_PHONE_NUMBER));

        String hasPhone = "Has Phone Number";
        if(phone.equals(0)){
            hasPhone = "Without Phone Number";
        }

        TextView nameTv =  view.findViewById(R.id.text1);
        nameTv.setText(name);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView pName = (TextView) view.findViewById(R.id.text1);
                searchView.setIconified(true);
                //get contact details and display
                Toast.makeText(context, "Selected Contact "+pName.getText(),
                        Toast.LENGTH_LONG).show();

            }
        });
    }
}