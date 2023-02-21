package com.sharon.planitall.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.sharon.planitall.Objects.Events;
import com.sharon.planitall.Objects.Schedule;
import com.sharon.planitall.R;
import com.sharon.planitall.tools.DataManager;

import java.util.ArrayList;

public class ScheduleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface ScheduleListener {
        void clicked(Schedule schedule, int position);
        void remove(Schedule schedule, int position);
    }

    private Activity activity;
    private ScheduleAdapter.ScheduleListener scheduleListener;
    private ArrayList<Schedule> schedules= new ArrayList<>();
    private Events theEvent;

    public ScheduleAdapter(Context context, ArrayList<Schedule> schedules) {
        theEvent= DataManager.get_instance().getCurrentEvent();
        this.schedules=schedules;
    }

    public void setScheduleListener(ScheduleAdapter.ScheduleListener scheduleListener) {
        this.scheduleListener = scheduleListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_schedule, parent, false);
        ScheduleAdapter.ScheduleHolder scheduleHolder = new ScheduleAdapter.ScheduleHolder(view);
        return scheduleHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final ScheduleAdapter.ScheduleHolder holder = (ScheduleAdapter.ScheduleHolder) viewHolder;
        Schedule schedule = getItem(position);
        String startHour=schedule.getStartHour();
        String startMinute=schedule.getStartMinute();
        String endHour=schedule.getEndHour();
        String endMinute=schedule.getEndMinute();

        holder.schedule_TXT_event_start.setText(startHour+":"+startMinute);
        holder.schedule_TXT_event_end.setText(endHour+":"+endMinute);
        holder.schedule_TXT_event.setText(schedule.getTitle());
    }

    @Override
    public int getItemCount() {
        return schedules.size();
    }

    public Schedule getItem(int position) {
        return schedules.get(position);
    }


    class ScheduleHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView schedule_TXT_event_start;
        private AppCompatTextView schedule_TXT_event_end;
        private AppCompatTextView schedule_TXT_event;
        private ShapeableImageView schedule_IMG_remove;


        public ScheduleHolder(View itemView) {
            super(itemView);
            schedule_TXT_event_start= itemView.findViewById(R.id.schedule_TXT_event_start);
            schedule_TXT_event_end= itemView.findViewById(R.id.schedule_TXT_event_end);
            schedule_TXT_event = itemView.findViewById(R.id.schedule_TXT_event);
            schedule_IMG_remove= itemView.findViewById(R.id.schedule_IMG_remove);
            schedule_IMG_remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (scheduleListener != null) {
                        scheduleListener.remove(getItem(getAdapterPosition()), getAdapterPosition());
                    }
                }
            });


            itemView.setOnClickListener(view -> {
                if (scheduleListener != null) {
                    scheduleListener.clicked(getItem(getAdapterPosition()), getAdapterPosition());
                }
            });
        }


    }
}
