package com.sharon.planitall.fregments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TimePicker;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textview.MaterialTextView;
import com.sharon.planitall.Objects.Events;
import com.sharon.planitall.Objects.Schedule;
import com.sharon.planitall.R;
import com.sharon.planitall.adapters.ScheduleAdapter;
import com.sharon.planitall.callbacks.Fragment_callback;
import com.sharon.planitall.callbacks.Observable;
import com.sharon.planitall.tools.DataManager;
import com.sharon.planitall.tools.MyDB;

import java.util.Comparator;


public class ScheduleFragment extends Fragment implements Observable {

    private AppCompatTextView                schedule_TXT_event_name;
    private AppCompatTextView                schedule_TXT_event_date;
    private RecyclerView                     schedule_RCV_schedule;



    private View view;
    private Events theEvent;
    private ScheduleAdapter scheduleAdapter;

    private ExtendedFloatingActionButton     schedule_BTN_add;
    private RelativeLayout                   schedule_RTL_focus;
    private LinearLayoutCompat               schedule_LNL_new;
    private EditText                         schedule_ETXT_title;
    private MaterialButton                   schedule_BTN_ok;
    private MaterialButton                   schedule_BTN_cancel;
    private String startTimeHour;
    private String startTimeMin;
    private String endTimeHour;
    private String endTimeMin;
    private RelativeLayout      schedule_RLT_timePickerStart;
    private TimePicker          schedule_TPK_timepickerStart;
    private RelativeLayout      schedule_RLT_timePickerEnd;
    private TimePicker          schedule_TPK_timepickerEnd;
    private MaterialButton      schedule_BTN_setTimeStart;
    private MaterialTextView    schedule_TXT_StartTime;
    private MaterialTextView      schedule_TXT_endTime;
    private MaterialButton    schedule_BTN_setTimeEnd;
    private MaterialTextView schedule_TXT_startCancel;
    private MaterialTextView schedule_TXT_startOK;
    private MaterialTextView schedule_TXT_endCancel;
    private MaterialTextView schedule_TXT_endOK;
    private Schedule schedule;
    private boolean isItNewItem=false;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_schedule, container, false);
        theEvent= DataManager.get_instance().getCurrentEvent();
        findViews();
        refreshUI();
        initViews();

        return view;
    }

    private void initViews() {
        schedule_BTN_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                schedule_RTL_focus.setVisibility(View.VISIBLE);
                schedule_LNL_new.setVisibility(View.VISIBLE);
                schedule_BTN_add.setVisibility(View.INVISIBLE);
                schedule = new Schedule();
                isItNewItem=true;
            }
        });
        schedule_BTN_setTimeStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                schedule_RLT_timePickerStart.setVisibility(View.VISIBLE);
            }
        });
        schedule_TXT_startOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                schedule_RLT_timePickerStart.setVisibility(View.INVISIBLE);
                if(schedule_TPK_timepickerStart.getHour()<10){
                    startTimeHour="0"+schedule_TPK_timepickerStart.getHour();
                }
                else startTimeHour=""+schedule_TPK_timepickerStart.getHour();
                if(schedule_TPK_timepickerStart.getMinute()<10)
                    startTimeMin="0"+schedule_TPK_timepickerStart.getMinute();
                else startTimeMin=""+schedule_TPK_timepickerStart.getMinute();

                schedule_TXT_StartTime.setText(startTimeHour+":"+startTimeMin);
            }
        });
        schedule_TXT_startCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                schedule_RLT_timePickerStart.setVisibility(View.INVISIBLE);
            }
        });

        schedule_BTN_setTimeEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                schedule_RLT_timePickerEnd.setVisibility(View.VISIBLE);
            }
        });
        schedule_TXT_endOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                schedule_RLT_timePickerEnd.setVisibility(View.INVISIBLE);
                if(schedule_TPK_timepickerEnd.getHour()<10){
                    endTimeHour="0"+schedule_TPK_timepickerEnd.getHour();
                }
                else endTimeHour=""+schedule_TPK_timepickerEnd.getHour();
                if(schedule_TPK_timepickerEnd.getMinute()<10)
                    endTimeMin="0"+schedule_TPK_timepickerEnd.getMinute();
                else endTimeMin=""+schedule_TPK_timepickerEnd.getMinute();
                schedule_TXT_endTime.setText(endTimeHour+":"+endTimeMin);
            }
        });
        schedule_TXT_endCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                schedule_RLT_timePickerEnd.setVisibility(View.INVISIBLE);
            }
        });


        schedule_BTN_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                schedule_RTL_focus.setVisibility(View.INVISIBLE);
                schedule_LNL_new.setVisibility(View.INVISIBLE);
                schedule_BTN_add.setVisibility(View.VISIBLE);
            }
        });




        schedule_BTN_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                schedule_BTN_add.setVisibility(View.VISIBLE);
                if(DataManager.get_instance().getCurrentEvent().getSchedules()==null){
                    DataManager.get_instance().getCurrentEvent().newSchduleList();
                }
                schedule_RTL_focus.setVisibility(View.INVISIBLE);
                schedule_LNL_new.setVisibility(View.INVISIBLE);
                schedule.setTitle(""+schedule_ETXT_title.getText())
                        .setStartHour(startTimeHour).setStartMinute(startTimeMin).setEndHour(endTimeHour).setEndMinute(endTimeMin);

                if(isItNewItem==true){
                    DataManager.get_instance().getCurrentEvent().addSchedule(schedule);
                }
                DataManager.get_instance().getCurrentEvent().sortSchedule();
                MyDB.getInstance().addEvent( DataManager.get_instance().getCurrentEvent().getMy_uid(), DataManager.get_instance().getCurrentEvent());
                refreshUI();


            }
        });
    }

    private void refreshUI() {
        show_rec_list();
    }

    public ScheduleFragment() {
        // Required empty public constructor
    }

    private Fragment_callback fragment_callback;
    public void setFragment_callback(Fragment_callback fragment_callback) {
        this.fragment_callback = fragment_callback;
    }
    private void findViews(){
        schedule_TXT_event_name= view.findViewById(R.id.schedule_TXT_event_name);
        schedule_TXT_event_name.setText(DataManager.get_instance().getCurrentEvent().getName());
        schedule_RCV_schedule= view.findViewById(R.id.schedule_RCV_schedule);


        schedule_RLT_timePickerStart = view.findViewById(R.id.schedule_RLT_timePickerStart);
        schedule_TPK_timepickerStart = view.findViewById(R.id.schedule_TPK_timepickerStart);
        schedule_RLT_timePickerEnd = view.findViewById(R.id.schedule_RLT_timePickerEnd);
        schedule_TPK_timepickerEnd = view.findViewById(R.id.schedule_TPK_timepickerEnd);
        schedule_BTN_setTimeStart = view.findViewById(R.id.schedule_BTN_setTimeStart);
        schedule_TXT_StartTime = view.findViewById(R.id.schedule_TXT_StartTime);
        schedule_TXT_endTime = view.findViewById(R.id.schedule_TXT_endTime);
        schedule_BTN_setTimeEnd = view.findViewById(R.id.schedule_BTN_setTimeEnd);
        schedule_BTN_add= view.findViewById(R.id.schedule_BTN_add);
        schedule_RTL_focus= view.findViewById(R.id.schedule_RTL_focus);
        schedule_LNL_new= view.findViewById(R.id.schedule_LNL_new);
        schedule_ETXT_title= view.findViewById(R.id.schedule_ETXT_title);
        schedule_BTN_ok= view.findViewById(R.id.schedule_BTN_ok);
        schedule_BTN_cancel= view.findViewById(R.id.schedule_BTN_cancel);
        schedule_TXT_startCancel= view.findViewById(R.id.schedule_TXT_startCancel);
        schedule_TXT_startOK= view.findViewById(R.id.schedule_TXT_startOK);
        schedule_TXT_endCancel= view.findViewById(R.id.schedule_TXT_endCancel);
        schedule_TXT_endOK= view.findViewById(R.id.schedule_TXT_endOK);
    }
    private void show_rec_list() {
        if(DataManager.get_instance().getCurrentEvent().getSchedules()!=null){
            scheduleAdapter= new ScheduleAdapter(this.getContext(), DataManager.get_instance().getCurrentEvent().getSchedules());
            scheduleAdapter.setScheduleListener(scheduleListener);
            schedule_RCV_schedule.setAdapter(scheduleAdapter);
        }

    }
    private void clikedSchedule(Schedule schedule, int position){
        this.schedule=schedule;
        isItNewItem=false;
        schedule_RTL_focus.setVisibility(View.VISIBLE);
        schedule_LNL_new.setVisibility(View.VISIBLE);
        schedule_BTN_add.setVisibility(View.INVISIBLE);
    }

    ScheduleAdapter.ScheduleListener scheduleListener = new ScheduleAdapter.ScheduleListener(){
        @Override
        public void clicked(Schedule schedule, int position) {
            clikedSchedule(schedule,position);
        }
        public void remove(Schedule schedule, int position) {
            DataManager.get_instance().getCurrentEvent().getSchedules().remove(position);
            MyDB.getInstance().addEvent( DataManager.get_instance().getCurrentEvent().getMy_uid(), DataManager.get_instance().getCurrentEvent());
            refreshUI();
        }
    };


}