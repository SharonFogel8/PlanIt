package com.sharon.planitall.Objects;

import java.util.ArrayList;

public class MyUser {
    private String name;
    private String UID;
    private ArrayList<Events> events= new ArrayList<>();
    private ArrayList<Events> invitedEvents= new ArrayList<>();
    private String img;
    private ArrayList<TDL> myTasks=new ArrayList<>();

    public ArrayList<TDL> getMyTasks() {
        return myTasks;
    }

    public MyUser setMyTasks(ArrayList<TDL> myTasks) {
        this.myTasks = myTasks;
        return this;
    }

    public MyUser() {

    }

    public String getName() {
        return name;
    }
    public void addNewEvent(Events newEvent){
        events.add(newEvent);
    }

    public MyUser setName(String name) {
        this.name = name;
        return this;
    }

    public String getUID() {
        return UID;
    }

    public MyUser setUID(String UID) {
        this.UID = UID;
        return this;
    }

    public ArrayList<Events> getEvents() {
        return events;
    }

    public MyUser setEvents(ArrayList<Events> events) {
        this.events = events;
        return this;
    }

    public ArrayList<Events> getInvitedEvents() {
        return invitedEvents;
    }

    public MyUser setInvitedEvents(ArrayList<Events> invitedEvents) {
        this.invitedEvents = invitedEvents;
        return this;
    }

    public String getImg() {
        return img;
    }

    public MyUser setImg(String img) {
        this.img = img;
        return this;
    }
}
