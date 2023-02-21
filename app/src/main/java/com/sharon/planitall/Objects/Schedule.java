package com.sharon.planitall.Objects;

import java.util.Comparator;

public class Schedule{
    private String title;
    private String startHour;
    private String startMinute;
    private String endHour;
    private String endMinute;
    private String duration;

    public Schedule() {
    }

    public String getDuration() {
        return duration;
    }

    public Schedule setDuration(String duration) {
        this.duration = duration;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Schedule setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getStartHour() {
        return startHour;
    }

    public Schedule setStartHour(String startHour) {
        this.startHour = startHour;
        return this;
    }

    public String getStartMinute() {
        return startMinute;
    }

    public Schedule setStartMinute(String startMinute) {
        this.startMinute = startMinute;
        return this;
    }

    public String getEndHour() {
        return endHour;
    }

    public Schedule setEndHour(String endHour) {
        this.endHour = endHour;
        return this;
    }

    public String getEndMinute() {
        return endMinute;
    }

    public Schedule setEndMinute(String endMinute) {
        this.endMinute = endMinute;
        return this;
    }


}
