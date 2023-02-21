package com.sharon.planitall.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.sharon.planitall.Objects.BudgetItem;
import com.sharon.planitall.Objects.Events;
import com.sharon.planitall.R;
import com.sharon.planitall.tools.DataManager;

import java.util.ArrayList;
import java.util.HashMap;

public class BudgetAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface BudgetListener {
        void clicked(BudgetItem budgetItem, int position);
        void remove(BudgetItem budgetItem, int position);
    }

    private Activity activity;
    private BudgetListener budgetItemListener;
   private ArrayList<BudgetItem> shopping= new ArrayList<>();
   private Events theEvent;

    public BudgetAdapter(Context context, ArrayList<BudgetItem> budgetItems) {
        theEvent= DataManager.get_instance().getCurrentEvent();
        //this.shopping=theEvent.getShopping();
        this.shopping=budgetItems;
    }

    public void setBudgetItemListener(BudgetListener budgetItemListener) {
        this.budgetItemListener = budgetItemListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_budget, parent, false);
        BudgetHolder budgetHolder = new BudgetHolder(view);
        return budgetHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final BudgetHolder holder = (BudgetHolder) viewHolder;
        BudgetItem budgetItem = getItem(position);
        holder.budget_TXT_type.setText(budgetItem.getType());
        holder.budget_TXT_name.setText(budgetItem.getName());
        holder.budget_TXT_cost.setText(Float.toString(budgetItem.getCost())+"$");
    }

    @Override
    public int getItemCount() {
        return shopping.size();
    }

    public BudgetItem getItem(int position) {
        return shopping.get(position);
    }


    class BudgetHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView budget_TXT_type;
        private AppCompatTextView budget_TXT_name;
        private AppCompatTextView budget_TXT_cost;
        private ShapeableImageView budget_IMG_remove;


        public BudgetHolder(View itemView) {
            super(itemView);
            budget_TXT_type= itemView.findViewById(R.id.budget_TXT_type);
            budget_TXT_name= itemView.findViewById(R.id.budget_TXT_name);
            budget_TXT_cost= itemView.findViewById(R.id.budget_TXT_cost);
            budget_IMG_remove= itemView.findViewById(R.id.budget_IMG_remove);
            budget_IMG_remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (budgetItemListener != null) {
                        budgetItemListener.remove(getItem(getAdapterPosition()), getAdapterPosition());
                    }
                }
            });
            itemView.setOnClickListener(view -> {
                if (budgetItemListener != null) {
                    budgetItemListener.clicked(getItem(getAdapterPosition()), getAdapterPosition());
                }
            });

        }


    }

}

