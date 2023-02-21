package com.sharon.planitall.fregments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.sharon.planitall.Objects.Events;
import com.sharon.planitall.R;
import com.sharon.planitall.callbacks.Fragment_callback;
import com.sharon.planitall.callbacks.Observable;
import com.sharon.planitall.tools.DataManager;


public class EventProfileFragment extends Fragment implements Observable {
    private View   view;
    private Events      theEvent;
    private ShapeableImageView eventProfile_BTN_photo;
    private TextView        eventProfile_TXT_name;
    private TextView        eventProfile_TXT_location;
    private MaterialButton  eventProfile_BTN_edit;
    private MaterialButton  eventProfile_BTN_suppliers;
    private MaterialButton  eventProfile_BTN_guestlist;
    private MaterialButton  eventProfile_BTN_schedule;
    private MaterialButton  eventProfile_BTN_budget;
    private MaterialButton  eventProfile_BTN_tdl;
    private LinearLayoutCompat eventProfile_LLY_profileHeadline;



    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_event_profile, container, false);
        theEvent=DataManager.get_instance().getCurrentEvent();
        findViews();
        initViews();
        return view;
    }

    private void initViews() {
        eventProfile_BTN_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fragment_callback!=null){
                    EditEventProfile editEventProfile = new EditEventProfile();
                    fragment_callback.go_next(editEventProfile,editEventProfile);
                }

            }
        });
        eventProfile_BTN_tdl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataManager.get_instance().setCurrentTasks(theEvent.getMyTasks());
                if(fragment_callback!=null){
                    TDL_listFragment tdl_listFragment = new TDL_listFragment();
                    fragment_callback.go_next(tdl_listFragment,tdl_listFragment);
                }
            }
        });



        eventProfile_BTN_budget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fragment_callback!=null){
                    BudgetFragment budgetFragment= new BudgetFragment();
                    fragment_callback.go_next(budgetFragment,budgetFragment);
                }
            }
        });
        eventProfile_BTN_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fragment_callback!=null){
                    ScheduleFragment scheduleFragment= new ScheduleFragment();
                    fragment_callback.go_next(scheduleFragment,scheduleFragment);
                }
            }
        });
    }

    private void findViews() {
        eventProfile_TXT_name= view.findViewById(R.id.eventProfile_TXT_name);
        eventProfile_TXT_location= view.findViewById(R.id.eventProfile_TXT_location);
        eventProfile_BTN_edit= view.findViewById(R.id.eventProfile_BTN_edit);
        eventProfile_BTN_suppliers= view.findViewById(R.id.eventProfile_BTN_suppliers);
        eventProfile_BTN_guestlist= view.findViewById(R.id.eventProfile_BTN_guestlist);
        eventProfile_BTN_schedule= view.findViewById(R.id.eventProfile_BTN_schedule);
        eventProfile_BTN_budget= view.findViewById(R.id.eventProfile_BTN_budget);
        eventProfile_TXT_name.setText(theEvent.getName());
        eventProfile_TXT_location.setText(theEvent.getLocation());
        eventProfile_LLY_profileHeadline = view.findViewById(R.id.eventProfile_LLY_profileHeadline);
        eventProfile_BTN_photo= view.findViewById(R.id.eventProfile_BTN_photo);
        eventProfile_BTN_photo.setImageResource(theEvent.getImg());
        eventProfile_BTN_tdl= view.findViewById(R.id.eventProfile_BTN_tdl);
    }


    public EventProfileFragment() {
       
    }

    
   


    private Fragment_callback fragment_callback;
    public void setFragment_callback(Fragment_callback fragment_callback) {
        this.fragment_callback = fragment_callback;
    }
}