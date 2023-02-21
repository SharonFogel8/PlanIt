package com.sharon.planitall.fregments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sharon.planitall.Objects.Events;
import com.sharon.planitall.R;
import com.sharon.planitall.adapters.EventsAdapter;
import com.sharon.planitall.callbacks.Fragment_callback;
import com.sharon.planitall.callbacks.Observable;
import com.sharon.planitall.callbacks.get_events_callback;
import com.sharon.planitall.tools.DataManager;
import com.sharon.planitall.tools.MyDB;

import java.util.ArrayList;
import java.util.HashMap;


public class EventsListFragment extends Fragment implements Observable {
    private View   view;
    private RecyclerView eventsListFrag_LST_events;
    private ArrayList<Events> events;
    private EventsAdapter eventsAdapter;
    ArrayList<Events> userEvents= new ArrayList<>();



    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_events_list, container, false);
        events = DataManager.get_instance().getEvents();
        if(events == null){
            MyDB.getInstance().setGet_events_callback(get_events_callback);
            MyDB.getInstance().getEvents();
        }
        else{
            show_rec_list();
        }

        return view;
    }

    private void show_rec_list() {
        eventsListFrag_LST_events = view.findViewById(R.id.eventsListFrag_LST_events);
        eventsAdapter= new EventsAdapter(this.getContext(),events);
        eventsAdapter.setEventslistener(eventListener);
        eventsListFrag_LST_events.setAdapter(eventsAdapter);
    }



    public EventsListFragment() {
        // Required empty public constructor
    }
    EventsAdapter.EventListener eventListener = new EventsAdapter.EventListener() {
        @Override
        public void clicked(Events event, int position) {
            if(fragment_callback!=null){
                DataManager.get_instance().setCurrentEvent(event);
                EventProfileFragment eventProfileFragment= new EventProfileFragment();
                fragment_callback.go_next(eventProfileFragment,eventProfileFragment);

            }
        }
    };

    private Fragment_callback fragment_callback;
    public void setFragment_callback(Fragment_callback fragment_callback) {
        this.fragment_callback = fragment_callback;
    }
    private get_events_callback get_events_callback= new get_events_callback() {
        @Override
        public void getEvents(HashMap<String, Events> get_events) {
            if (get_events == null ){
                events=new ArrayList<>();
            }
            else{
                events=new ArrayList<>();
                ArrayList<Events> allEvents= new ArrayList<>(get_events.values());
                for (int i = 0; i < allEvents.size(); i++) {
                    for (int j = 0; j < allEvents.get(i).getManagers().size(); j++) {
                            if (DataManager.get_instance().getUser().getMy_uid().compareTo(allEvents.get(i).getManagers().get(j))==0){
                            DataManager.get_instance().getUser().addNewEvent(allEvents.get(i).getMy_uid());
                            events.add(allEvents.get(i));
                            Log.d("MyLog",""+allEvents.get(i).getName());
                        }
                    }
                }
                DataManager.get_instance().setEvents(events);
                }

            show_rec_list();
        }

        @Override
        public void getEvent(Events event) {

        }
    };

}