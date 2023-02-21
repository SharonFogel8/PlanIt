package com.sharon.planitall.callbacks;

import com.sharon.planitall.Objects.Events;

import java.util.ArrayList;
import java.util.HashMap;

public interface get_events_callback {
    void getEvents(HashMap<String,Events> events);
    void getEvent(Events event);
}
