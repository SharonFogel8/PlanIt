package com.sharon.planitall.fregments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.google.android.material.button.MaterialButton;
import com.sharon.planitall.R;
import com.sharon.planitall.callbacks.DatePicker_callback;


public class DatePickerFragment extends Fragment {
//TODO handle with the save button

    private DatePicker date_picker;
    private DatePicker_callback datePicker_callback;
    private MaterialButton newEvent_BTN_cancel;
    private MaterialButton newEvent_BTN_ok;
    private int year,month,day;

    public void setDatePicker_callback(DatePicker_callback datePicker_callback){
        this.datePicker_callback = datePicker_callback;
    }

    public DatePickerFragment() {
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date_picker, container, false);

        findViews(view);
        initViews();


        return view;
    }

    private void initViews() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            date_picker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int month, int day) {
                    changDate(year,month,day);
                }
            });
        }
        newEvent_BTN_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(datePicker_callback!= null)
                    datePicker_callback.changeDate(year,month,day);
            }
            //TODO close the fragment!
        });
        //TODO handle with cancel

    }

    private void changDate(int year, int month, int day) {
        this.year= year;
        this.day= day;
        this.month=month;
    }

    private void findViews(View view) {
        date_picker= view.findViewById(R.id.date_picker_DP_date_picker);
        newEvent_BTN_cancel= view.findViewById(R.id.newEvent_BTN_cancel);
        newEvent_BTN_ok= view.findViewById(R.id.newEvent_BTN_ok);
    }
}