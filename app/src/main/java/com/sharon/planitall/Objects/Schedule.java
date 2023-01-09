package com.sharon.planitall.Objects;

import java.util.HashMap;

public class Schedule {
    int year;
    int month;
    int day;
    private HashMap<Long,String> scheduleMap=new HashMap<>();

    public int getYear() {
        return year;
    }

    public Schedule setYear(int year) {
        this.year = year;
        return this;
    }

    public int getMonth() {
        return month;
    }

    public Schedule setMonth(int month) {
        this.month = month;
        return this;
    }

    public int getDay() {
        return day;
    }

    public Schedule setDay(int day) {
        this.day = day;
        return this;
    }

    public HashMap<Long, String> getScheduleMap() {
        return scheduleMap;
    }

    public Schedule setScheduleMap(HashMap<Long, String> scheduleMap) {
        this.scheduleMap = scheduleMap;
        return this;
    }

    public Schedule() {
    }

    public void addTask( long time,String task){
        scheduleMap.put(time,task);
    }
    public void setDate(int year, int month, int day){
        this.year=year;
        this.month=month;
        this.day=day;
    }

}
