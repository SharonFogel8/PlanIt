package com.sharon.planitall.Objects;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Date;

public class TDL {
    private String task;
    private int year;
    private int month;
    private int day;
    private boolean complited=false;
    private boolean important=false;
    Date currentDate = new Date();

    public TDL() {
    }
    public void taskComplited(){
        complited=!complited;
    }
    public void important(){
        important=!important;
    }
    public String getTask() {
        return task;
    }

    public TDL setTask(String task) {
        this.task = task;
        return this;
    }

    public int getYear() {
        return year;
    }

    public TDL setYear(int year) {
        this.year = year;
        return this;
    }

    public int getMonth() {
        return month;
    }

    public TDL setMonth(int month) {
        this.month = month;
        return this;
    }

    public int getDay() {
        return day;
    }

    public TDL setDay(int day) {
        this.day = day;
        return this;
    }
}
