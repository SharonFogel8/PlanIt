package com.sharon.planitall.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.sharon.planitall.Objects.Events;
import com.sharon.planitall.R;

import java.util.ArrayList;

public class EventsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface EventListener {
        void clicked(Events event, int position);
    }

    private Activity activity;
    private EventListener eventListener;
    private ArrayList<Events> events = new ArrayList<>();

    public EventsAdapter(Context context, ArrayList<Events> events) {
        this.activity = activity;
        this.events = events;
    }

    public void setEventslistener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_event, parent, false);
        EventHolder eventHolder = new EventHolder(view);
        return eventHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final EventHolder holder = (EventHolder) viewHolder;
        Events event = getItem(position);

        holder.eventList_IMG_event.setBackgroundResource(event.getImg()); //
        holder.eventList_TXT_location.setText(event.getLocation());
        holder.eventList_TXT_Date.setText(""+event.getDay()+"/"+event.getMonth()+"/"+event.getYear());
        holder.eventList_TXT_name.setText(event.getName());

    }

    @Override
    public int getItemCount() {
        if(events==null)
            return 0;
        return events.size();
    }

    public Events getItem(int position) {
        return events.get(position);
    }


    class EventHolder extends RecyclerView.ViewHolder {

        private AppCompatImageView eventList_IMG_event;
        private AppCompatTextView eventList_TXT_location;
        private AppCompatTextView eventList_TXT_Date;
        private AppCompatTextView eventList_TXT_name;


        public EventHolder(View itemView) {
            super(itemView);
            eventList_IMG_event = itemView.findViewById(R.id.eventList_IMG_event);
            eventList_TXT_location = itemView.findViewById(R.id.eventList_TXT_location);
            eventList_TXT_Date = itemView.findViewById(R.id.eventList_TXT_Date);
            eventList_TXT_name= itemView.findViewById(R.id.eventList_TXT_name);

            itemView.setOnClickListener(view -> {
                if (eventListener != null) {
                    eventListener.clicked(getItem(getAdapterPosition()), getAdapterPosition());
                }
            });
        }

    }
}

