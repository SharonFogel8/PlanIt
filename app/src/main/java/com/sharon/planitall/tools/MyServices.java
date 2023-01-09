package com.sharon.planitall.tools;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Date;

public class MyServices {
    private static MyServices _instance = null;
    private Context context;
    private Toast toast;
    private View viewToast;
    private final int LEN_MSG_SHORT = 15;

    private MyServices(Context context) {
        this.context = context.getApplicationContext();
        this.toast = Toast.makeText(context, "", Toast.LENGTH_LONG);

    }
    public static void initHelper(Context context) {
        if (_instance == null) {
            _instance = new MyServices(context);
        }
    }
    public static MyServices getInstance(){
        return _instance;
    }
    public void makeToast(String msg){
        if(msg.length() < LEN_MSG_SHORT)
            toast.makeText(context, "", Toast.LENGTH_SHORT);
        else
            toast.makeText(context, "", Toast.LENGTH_LONG);
        toast.setText(msg);
        toast.show();
    }


    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        public interface Callback_date {
            void get_input_date(Date date);
        }
        private Callback_date callback_date;

        public DatePickerFragment setCallback_date(Callback_date callback_date) {
            this.callback_date = callback_date;
            return this;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            if(this.callback_date != null){
                final Calendar c = Calendar.getInstance();
                c.set(year, month, day , 0, 0 ,0);
                this.callback_date.get_input_date(c.getTime());
            }
        }
    }

}
