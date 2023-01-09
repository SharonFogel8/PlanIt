package com.sharon.planitall.fregments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.sharon.planitall.R;
import com.sharon.planitall.activities.HomePageActivity;
import com.sharon.planitall.callbacks.DatePicker_callback;
import com.sharon.planitall.callbacks.Fragment_callback;
import com.sharon.planitall.callbacks.Observable;
import com.sharon.planitall.tools.MyServices;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NewEventFragment extends Fragment implements Observable {
    private TextView newEvent_TXT_headline;
    private MaterialButton newEvent_BTN_photo;
    private EditText newEvent_ETXT_name;
    private EditText        newEvent_ETXT_type;
    private EditText        newEvent_ETXT_amount;
    private MaterialButton  newEvent_BTN_date;
    private EditText        newEvent_ETXT_location;
    private TextView        newEvent_TXT_date;
    private HomePageActivity homePageActivity;
    private int year,month,day;
    private DatePickerFragment datePickerFragment;
   // private FrameLayout newEvent_LAY_datePicker;
    private RelativeLayout newEvent_LAY_datePicker;
    private MaterialButton newEvent_BTN_cancel;
    private MaterialButton newEvent_BTN_ok;
    private DatePicker date_picker_DP_date_picker;
    View view;
    private RelativeLayout newEvent_RLY_back;


    public NewEventFragment() {
        // Required empty public constructor
    }


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_new_event, container, false);
        homePageActivity = new HomePageActivity();
        datePickerFragment= new DatePickerFragment();
        findViews();
        initViews();

        return view;
    }

    private void initViews() {
        newEvent_BTN_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newEvent_LAY_datePicker.setVisibility(View.INVISIBLE);

            }
        });
        newEvent_BTN_date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                newEvent_LAY_datePicker.setVisibility(View.VISIBLE);

            }
        });
        newEvent_BTN_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newEvent_TXT_date.setText(""+day+"/"+month+"/"+year);
                newEvent_LAY_datePicker.setVisibility(View.INVISIBLE);
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            date_picker_DP_date_picker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Log.d("myLog","before: year"+year+" day"+day+" month"+month);
                    initDate(year,monthOfYear,dayOfMonth);
                }
            });
        }
        

    }



    private void findViews() {
        newEvent_TXT_headline= view.findViewById(R.id.newEvent_TXT_headline);
        newEvent_BTN_photo= view.findViewById(R.id.newEvent_BTN_photo);
        newEvent_ETXT_name= view.findViewById(R.id.newEvent_ETXT_name);
        newEvent_ETXT_type= view.findViewById(R.id.newEvent_ETXT_type);
        newEvent_ETXT_amount= view.findViewById(R.id.newEvent_ETXT_amount);
        newEvent_BTN_date= view.findViewById(R.id.newEvent_BTN_date);;
        newEvent_ETXT_location= view.findViewById(R.id.newEvent_ETXT_location);
        newEvent_TXT_date= view.findViewById(R.id.newEvent_TXT_date);
        newEvent_LAY_datePicker= view.findViewById(R.id. newEvent_LAY_datePicker);

        newEvent_BTN_cancel= view.findViewById(R.id.newEvent_BTN_cancel);
        newEvent_BTN_ok= view.findViewById(R.id.newEvent_BTN_ok);
        date_picker_DP_date_picker= view.findViewById(R.id.date_picker_DP_date_picker);
        newEvent_RLY_back= view.findViewById(R.id.newEvent_RLY_back);
    }


    private Fragment_callback fragment_callback;
    public void setFragment_callback(Fragment_callback fragment_callback) {
        this.fragment_callback = fragment_callback;
    }
//    DatePicker_callback datePicker_callback = new DatePicker_callback() {
//        @Override
//        public void changeDate(int year, int month, int day) {
//            initDate(year, month, day);
//
//        }
//    };

    private void initDate(int year, int monthOf, int day) {
        this.year= year;
        this.day= day;
        this.month=monthOf+1;
        Log.d("myLog","year"+year+" day"+day+" month"+month);

    }
}