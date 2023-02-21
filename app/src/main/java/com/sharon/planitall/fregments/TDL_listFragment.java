package com.sharon.planitall.fregments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textview.MaterialTextView;
import com.sharon.planitall.Objects.Events;
import com.sharon.planitall.Objects.Schedule;
import com.sharon.planitall.Objects.TDL;
import com.sharon.planitall.R;
import com.sharon.planitall.adapters.ScheduleAdapter;
import com.sharon.planitall.adapters.TDLAdapter;
import com.sharon.planitall.callbacks.Fragment_callback;
import com.sharon.planitall.callbacks.Observable;
import com.sharon.planitall.tools.DataManager;
import com.sharon.planitall.tools.MyDB;


public class TDL_listFragment extends Fragment implements Observable {





    private View view;
    private Events theEvent;
    private TDLAdapter tdlAdapter;
    private TDL tdl;
    private boolean isItNewItem;

   private AppCompatTextView    list_tdl_TXT_tdl_name;
   private RecyclerView         list_tdl_RCV_tdl;
   private RelativeLayout       list_tdl_RTL_focus;
   private LinearLayoutCompat   list_tdl_LNL_new;
   private EditText             list_tdl_ETXT_title;
   private DatePicker           list_tdl_DP_date_picker;
   private MaterialButton       list_tdl_BTN_ok;
   private MaterialButton       list_tdl_BTN_cancel;

   private ExtendedFloatingActionButton list_tdl_BTN_add;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tdl_list, container, false);
        theEvent= DataManager.get_instance().getCurrentEvent();
        findViews();
        refreshUI();
        initViews();

        return view;
    }

    private void initViews() {
        list_tdl_BTN_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list_tdl_RTL_focus.setVisibility(View.VISIBLE);
                list_tdl_LNL_new.setVisibility(View.VISIBLE);
                list_tdl_BTN_add.setVisibility(View.INVISIBLE);
                tdl = new TDL();
                isItNewItem=true;
            }
        });






        list_tdl_BTN_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list_tdl_RTL_focus.setVisibility(View.INVISIBLE);
                list_tdl_LNL_new.setVisibility(View.INVISIBLE);
                list_tdl_BTN_add.setVisibility(View.VISIBLE);
            }
        });




        list_tdl_BTN_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list_tdl_BTN_add.setVisibility(View.VISIBLE);
                if(DataManager.get_instance().getCurrentTasks()==null){
                    DataManager.get_instance().getCurrentEvent().newTasksList();
                }
                list_tdl_RTL_focus.setVisibility(View.INVISIBLE);
                list_tdl_LNL_new.setVisibility(View.INVISIBLE);
                tdl.setTask(""+list_tdl_ETXT_title.getText())
                        .setDay(list_tdl_DP_date_picker.getDayOfMonth()).setMonth(list_tdl_DP_date_picker.getMonth()).setYear(list_tdl_DP_date_picker.getYear());

                if(isItNewItem==true){
                    theEvent.getMyTasks().add(tdl);
                }
                if(DataManager.get_instance().isEventTasks())
                    MyDB.getInstance().addEvent(DataManager.get_instance().getCurrentEvent().getMy_uid(), DataManager.get_instance().getCurrentEvent());
                refreshUI();
            }
        });
    }

    private void refreshUI() {
        show_rec_list();
    }

    public TDL_listFragment() {
        // Required empty public constructor
    }

    private Fragment_callback fragment_callback;
    public void setFragment_callback(Fragment_callback fragment_callback) {
        this.fragment_callback = fragment_callback;
    }
    private void findViews(){
        list_tdl_TXT_tdl_name=view.findViewById(R.id.list_tdl_TXT_tdl_name);
        list_tdl_RCV_tdl=view.findViewById(R.id.list_tdl_RCV_tdl);
        list_tdl_RTL_focus=view.findViewById(R.id.list_tdl_RTL_focus);
        list_tdl_LNL_new=view.findViewById(R.id.list_tdl_LNL_new);
        list_tdl_ETXT_title=view.findViewById(R.id.list_tdl_ETXT_title);
        list_tdl_DP_date_picker=view.findViewById(R.id.list_tdl_DP_date_picker);
        list_tdl_BTN_ok=view.findViewById(R.id.list_tdl_BTN_ok);
        list_tdl_BTN_cancel=view.findViewById(R.id.list_tdl_BTN_cancel);
        list_tdl_BTN_add= view.findViewById(R.id.list_tdl_BTN_add);
    }
    private void show_rec_list() {
        if(DataManager.get_instance().getCurrentTasks()!=null){
            tdlAdapter = new TDLAdapter(this.getContext(), DataManager.get_instance().getCurrentTasks());
            tdlAdapter.setTdlListener(tdlListener);
            list_tdl_RCV_tdl.setAdapter(tdlAdapter);
        }

    }
    private void clickedTDL(TDL tdl, int position){
        this.tdl=tdl;
        isItNewItem=false;
        list_tdl_RTL_focus.setVisibility(View.VISIBLE);
        list_tdl_LNL_new.setVisibility(View.VISIBLE);
        list_tdl_BTN_add.setVisibility(View.INVISIBLE);
    }
    TDLAdapter.tdlListener tdlListener= new TDLAdapter.tdlListener() {
        @Override
        public void clicked(TDL tdl, int position) {

        }

        @Override
        public void remove(TDL tdl, int position) {
            DataManager.get_instance().getCurrentTasks().remove(position);
            MyDB.getInstance().addEvent(DataManager.get_instance().getCurrentEvent().getMy_uid(), DataManager.get_instance().getCurrentEvent());
            refreshUI();
        }

        @Override
        public void complited(TDL tdl, int position) {
            tdl.taskComplited();
        }
    };
}