package com.sharon.planitall.fregments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.sharon.planitall.Objects.Events;
import com.sharon.planitall.R;
import com.sharon.planitall.activities.HomePageActivity;
import com.sharon.planitall.callbacks.Fragment_callback;
import com.sharon.planitall.callbacks.Observable;
import com.sharon.planitall.tools.DataManager;
import com.sharon.planitall.tools.MyDB;

import java.util.Calendar;
import java.util.Random;
import java.util.UUID;

public class NewEventFragment extends Fragment implements Observable {
    private TextView newEvent_TXT_headline;
    private MaterialButton newEvent_BTN_photo;
    private EditText newEvent_ETXT_name;
    private EditText        newEvent_ETXT_amount;
    private EditText    newEvent_ETXT_budget;
    private MaterialButton  newEvent_BTN_date;
    private EditText        newEvent_ETXT_location;
    private TextView        newEvent_TXT_date;
    private HomePageActivity homePageActivity;
    private int year,month,day;
   // private DatePickerFragment datePickerFragment;
    private RelativeLayout newEvent_LAY_datePicker;
    private MaterialButton newEvent_BTN_cancel;
    private MaterialButton newEvent_BTN_ok;
    private MaterialButton newEvent_BTN_create;
    private DatePicker date_picker_DP_date_picker;
    View view;
    private RelativeLayout newEvent_RLY_back;
    private Events theEvent;
    private AppCompatSpinner newEvent_SPN_type;
    private boolean isFieldsOk;


    private TextView newEvent_TXT_errorDate;
    private TextView newEvent_TXT_errorName;

    public NewEventFragment() {
        // Required empty public constructor
    }


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_new_event, container, false);
        theEvent= new Events();
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
                if(month<10&&day<10)
                    newEvent_TXT_date.setText("0"+day+"/0"+month+"/"+year);
                if(month<10)
                    newEvent_TXT_date.setText(""+day+"/0"+month+"/"+year);
                if(day<10)
                    newEvent_TXT_date.setText("0"+day+"/"+month+"/"+year);
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
        newEvent_BTN_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEvent();

            }
        });


    }

    private void addEvent() {
        isFieldsOk=true;
        newEvent_TXT_errorDate.setVisibility(View.INVISIBLE);
        newEvent_TXT_errorName.setVisibility(View.INVISIBLE);
        Calendar user_pick = Calendar.getInstance();
        user_pick.set(Calendar.YEAR,year);
        user_pick.set(Calendar.MONTH,month);
        user_pick.set(Calendar.DAY_OF_MONTH,day);
        Calendar today = Calendar.getInstance();
        String name=newEvent_ETXT_name.getText().toString();
        if(today.after(user_pick)){
            newEvent_TXT_errorDate.setVisibility(View.VISIBLE);
            isFieldsOk=false;
        }
        if(name.length()<=1){
            newEvent_TXT_errorName.setVisibility(View.VISIBLE);
            isFieldsOk=false;
        }
        if(theEvent.getImg()==0){
            Random random = new Random();
            int num=random.nextInt(8);
            if(num==0)
                theEvent.setImg(R.drawable.img_exemple_party);
            if(num==1)
                theEvent.setImg(R.drawable.img_exemple_party);
            if(num==2)
                theEvent.setImg(R.drawable.img_picnic);
            if(num==3)
                theEvent.setImg(R.drawable.img_beers);
            if(num==4)
                theEvent.setImg(R.drawable.img_dinner);
            if(num==5)
                theEvent.setImg(R.drawable.img_food);
            if(num==6)
                theEvent.setImg(R.drawable.img_toast);
            else
                theEvent.setImg(R.drawable.img_beach);
        }
        if(isFieldsOk){
            theEvent.setDate(year,month,day);
            theEvent.setName(name)
                    .setBudget(Float.valueOf(newEvent_ETXT_budget.getText().toString()))
                    .setNumOfInvited(Integer.valueOf(newEvent_ETXT_amount.getText().toString()))
                    .setLocation(newEvent_ETXT_location.getText().toString())
                    .setMy_uid(UUID.randomUUID().toString())
                    .addManager(DataManager.get_instance().getUser().getMy_uid());
           // DataManager.get_instance().getUser().addNewEvent(theEvent.getMy_uid());
            DataManager.get_instance().addEvent(theEvent);
            Log.d("MyLog","user name:"+DataManager.get_instance().getUser().getName()+"event name:"+theEvent.getName());
            Log.d("MyLog","user name:"+DataManager.get_instance().getUser().toString());
            MyDB.getInstance().addEventToAccount(theEvent.getMy_uid(),theEvent);
            MyDB.getInstance().addEvent(theEvent.getMy_uid(),theEvent);

            DataManager.get_instance().setCurrentEvent(theEvent);
            if(fragment_callback!=null){
                EventProfileFragment eventProfileFragment= new EventProfileFragment();
                fragment_callback.go_next(eventProfileFragment,eventProfileFragment);
            }
        }


    }


    private void findViews() {
        newEvent_TXT_headline= view.findViewById(R.id.newEvent_TXT_headline);
        newEvent_BTN_photo= view.findViewById(R.id.newEvent_BTN_photo);
        newEvent_ETXT_name= view.findViewById(R.id.newEvent_ETXT_name);
        newEvent_ETXT_amount= view.findViewById(R.id.newEvent_ETXT_amount);
        newEvent_BTN_date= view.findViewById(R.id.newEvent_BTN_date);;
        newEvent_ETXT_location= view.findViewById(R.id.newEvent_ETXT_location);
        newEvent_TXT_date= view.findViewById(R.id.newEvent_TXT_date);
        newEvent_LAY_datePicker= view.findViewById(R.id. newEvent_LAY_datePicker);

        newEvent_BTN_cancel= view.findViewById(R.id.newEvent_BTN_cancel);
        newEvent_BTN_ok= view.findViewById(R.id.newEvent_BTN_ok);
        date_picker_DP_date_picker= view.findViewById(R.id.date_picker_DP_date_picker);
        newEvent_RLY_back= view.findViewById(R.id.newEvent_RLY_back);
        newEvent_BTN_create = view.findViewById(R.id.newEvent_BTN_create);
        newEvent_SPN_type=view.findViewById(R.id.newEvent_SPN_type);
        newEvent_ETXT_budget= view.findViewById(R.id.newEvent_ETXT_budget);
        newEvent_TXT_errorDate= view.findViewById(R.id.newEvent_TXT_errorDate);
        newEvent_TXT_errorName= view.findViewById(R.id.newEvent_TXT_errorName);
        setTypes();
    }

    private void setTypes() {
        String types[]={"Birthday","Wedding","Bachelorette Party","Bachelor Party","Toast","Bar Mizva","Bat Mizva","Other"};
        ArrayAdapter<String> adapter = new ArrayAdapter(this.getContext(), R.layout.list_item, types);
        newEvent_SPN_type.setAdapter(adapter);
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