package com.sharon.planitall.Objects;

import java.util.ArrayList;

public class MyUser {
    private String name;
    private String my_uid;
    private ArrayList<String> events= null;
    private ArrayList<String> invitedEvents= new ArrayList<>();
    private String img;
    private ArrayList<TDL> myTasks=new ArrayList<>();
    private ArrayList<TDL> importantTasks=new ArrayList<>();
    private String phoneNum;
    public ArrayList<TDL> getMyTasks() {
        return myTasks;
    }

    public MyUser setMyTasks(ArrayList<TDL> myTasks) {
        this.myTasks = myTasks;
        return this;
    }

    public MyUser() {

    }
    public void newTasksList(){
        myTasks= new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    public void addNewEvent(String newEvent){
        if(events==null){
            events= new ArrayList<>();
        }
        events.add(newEvent);
    }

    public MyUser setName(String name) {
        this.name = name;
        return this;
    }

    public String getMy_uid() {
        return my_uid;
    }

    public MyUser setMy_uid(String my_uid) {
        this.my_uid = my_uid;
        return this;
    }

    public ArrayList<String> getEvents() {
        return events;
    }

    public MyUser setEvents(ArrayList<String> events) {
        this.events = events;
        return this;
    }

    public ArrayList<String> getInvitedEvents() {
        return invitedEvents;
    }

    public MyUser setInvitedEvents(ArrayList<String> invitedEvents) {
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

    public String getPhoneNum() {
        return phoneNum;
    }

    public MyUser setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
        return this;
    }

    @Override
    public String toString() {
        return "MyUser{" +
                "name='" + name + '\'' +
                ", UID='" + my_uid + '\'' +
                ", events=" + events +
                ", invitedEvents=" + invitedEvents +
                ", img='" + img + '\'' +
                ", myTasks=" + myTasks +
                ", phoneNum='" + phoneNum + '\'' +
                '}';
    }
}
