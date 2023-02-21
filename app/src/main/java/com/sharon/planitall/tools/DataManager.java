package com.sharon.planitall.tools;


import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.sharon.planitall.Objects.Events;
import com.sharon.planitall.Objects.MyUser;
import com.sharon.planitall.Objects.TDL;
import com.sharon.planitall.callbacks.get_events_callback;

import java.util.ArrayList;
import java.util.HashMap;

public class DataManager {
    private static DataManager _instance= new DataManager();

    private MyUser user;
    private Events currentEvent;
    private ArrayList<Events> myEvents =null;
    private Fragment current_frage;
    private ArrayList<TDL> currentTasks=new ArrayList<>();
    private boolean isEventTasks=false;


    public DataManager() {

    }
    public void eventTasks(boolean isEventTasks){
        this.isEventTasks=isEventTasks;
    }
    public boolean isEventTasks(){
        return isEventTasks;
    }
    public ArrayList<Events> getEvents() {
        return myEvents;
    }

    public DataManager setEvents(ArrayList<Events> events) {
        this.myEvents = events;
        return this;
    }

    public Events getCurrentEvent() {
        return currentEvent;
    }

    public DataManager setCurrentEvent(Events currentEvent) {
        this.currentEvent = currentEvent;
        return this;
    }


    public static DataManager get_instance() {
        return _instance;
    }


    public MyUser getUser() {
        return user;
    }

    public DataManager setUser(MyUser user) {
        this.user = user;
        return this;
    }
    public ArrayList<TDL> getCurrentTasks() {
        return currentTasks;
    }

    public DataManager setCurrentTasks(ArrayList<TDL> currentTasks) {
        this.currentTasks = currentTasks;
        return this;
    }


    public void set_current_frage(Fragment next) {
        this.current_frage = next;
    }

    public Fragment get_next_frag() {
        return this.current_frage;
    }

    public void addEvent(Events theEvent) {
        if(myEvents == null){
            myEvents = new ArrayList<>();
        }
        user.addNewEvent(theEvent.getMy_uid());
        myEvents.add(theEvent);
    }
}
