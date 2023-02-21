package com.sharon.planitall.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.sharon.planitall.Objects.Events;
import com.sharon.planitall.Objects.Schedule;
import com.sharon.planitall.Objects.TDL;
import com.sharon.planitall.R;
import com.sharon.planitall.tools.DataManager;

import java.util.ArrayList;

public class TDLAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface tdlListener {
        void clicked(TDL tdl, int position);
        void remove(TDL tdl, int position);
        void complited(TDL tdl, int position);
    }

    private Activity activity;
    private TDLAdapter.tdlListener tdlListener;
    private ArrayList<TDL> tdls= new ArrayList<>();
    //private Events theEvent;

    public TDLAdapter(Context context, ArrayList<TDL> tdls) {
        //theEvent= DataManager.get_instance().getCurrentEvent();
        this.tdls=tdls;
    }

    public void setTdlListener(TDLAdapter.tdlListener tdlListener) {
        this.tdlListener = tdlListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_tdl, parent, false);
        TDLAdapter.TdlHolder tdlHolder = new TDLAdapter.TdlHolder(view);
        return tdlHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final TDLAdapter.TdlHolder holder = (TDLAdapter.TdlHolder) viewHolder;
        TDL tdl = getItem(position);
        holder.tdl_list_TXT_text.setText(tdl.getTask());

    }

    @Override
    public int getItemCount() {
        return tdls.size();
    }

    public TDL getItem(int position) {
        return tdls.get(position);
    }


    class TdlHolder extends RecyclerView.ViewHolder {

        private CheckBox            tdl_list_CBX_complited;
        private AppCompatTextView   tdl_list_TXT_text;
        private ShapeableImageView  tdl_list_IMG_remove;


        public TdlHolder(View itemView) {
            super(itemView);
            tdl_list_CBX_complited= itemView.findViewById(R.id.tdl_list_CBX_complited);
            tdl_list_TXT_text= itemView.findViewById(R.id.tdl_list_TXT_text);
            tdl_list_IMG_remove= itemView.findViewById(R.id.tdl_list_IMG_remove);

            tdl_list_IMG_remove.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if (tdlListener != null) {
                     tdlListener.remove(getItem(getAdapterPosition()), getAdapterPosition());
                 }
             }
         });
            tdl_list_CBX_complited.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tdlListener.complited(getItem(getAdapterPosition()), getAdapterPosition());
                }
            });


            itemView.setOnClickListener(view -> {
                if (tdlListener != null) {
                    tdlListener.clicked(getItem(getAdapterPosition()), getAdapterPosition());
                }
            });
        }


    }
}
