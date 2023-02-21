package com.sharon.planitall.fregments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.sharon.planitall.Objects.BudgetItem;
import com.sharon.planitall.Objects.Events;
import com.sharon.planitall.R;
import com.sharon.planitall.adapters.BudgetAdapter;
import com.sharon.planitall.callbacks.Fragment_callback;
import com.sharon.planitall.callbacks.Observable;
import com.sharon.planitall.tools.DataManager;
import com.sharon.planitall.tools.MyDB;


public class BudgetFragment extends Fragment implements Observable {
    private View view;
    private AppCompatTextView             budget_TXT_total;
    private AppCompatTextView             budget_TXT_leftover;
    private RecyclerView                  budget_RCV_items;
    private ExtendedFloatingActionButton  budget_BTN_additem;
    private BudgetAdapter budgetAdapter;
    private Events theEvent;


    private LinearLayoutCompat  budget_LNR_newitem;
    private EditText            budget_ETXT_name;
    private EditText            budget_ETXT_type;
    private EditText            budget_ETXT_price;
    private MaterialButton      budget_BTN_ok;
    private MaterialButton      budget_BTN_cancel;
    private LinearLayoutCompat budget_LNR_all;
    private RelativeLayout budget_RTL_foucus;
    private  BudgetItem budgetItem;
    private boolean isNewIteam=false;
    public BudgetFragment() {

    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_budget, container, false);
        theEvent=DataManager.get_instance().getCurrentEvent();
        findViews();
        refreshUI();
        initViews();

        return view;
    }

    private void refreshUI() {
        show_rec_list();
        budget_TXT_total.setText(Float.toString(DataManager.get_instance().getCurrentEvent().getBudget())+"$");
        budget_TXT_leftover.setText(Float.toString(DataManager.get_instance().getCurrentEvent().getLeftOverMoney())+"$");

    }

    private void findViews() {
        budget_TXT_total= view.findViewById(R.id.budget_TXT_total);
        budget_TXT_leftover= view.findViewById(R.id.budget_TXT_leftover);
        budget_RCV_items= view.findViewById(R.id.budget_RCV_items);
        budget_BTN_additem= view.findViewById(R.id.budget_BTN_additem);

        budget_LNR_newitem = view.findViewById(R.id.budget_LNR_newitem);
        budget_ETXT_name = view.findViewById(R.id.budget_ETXT_name);
        budget_ETXT_type = view.findViewById(R.id.budget_ETXT_type);
        budget_ETXT_price = view.findViewById(R.id.budget_ETXT_price);
        budget_BTN_ok = view.findViewById(R.id.budget_BTN_ok);
        budget_BTN_cancel = view.findViewById(R.id.budget_BTN_cancel);
        budget_LNR_all = view.findViewById(R.id.budget_LNR_all);
        budget_RTL_foucus= view.findViewById(R.id.budget_RTL_foucus);

    }

    private void initViews() {

        budget_BTN_additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                budget_LNR_newitem.setVisibility(View.VISIBLE);
                budget_RTL_foucus.setVisibility(View.VISIBLE);
                budgetItem= new BudgetItem();
                isNewIteam=true;
            }
        });
        budget_BTN_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editBudget(budgetItem);
            }
        });
        budget_BTN_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                budget_LNR_newitem.setVisibility(View.INVISIBLE);
                budget_RTL_foucus.setVisibility(View.INVISIBLE);
            }
        });
    }
    private void show_rec_list() {
        budgetAdapter= new BudgetAdapter(this.getContext(), DataManager.get_instance().getCurrentEvent().getShopping());
        budgetAdapter.setBudgetItemListener(budgetListener);
        budget_RCV_items.setAdapter(budgetAdapter);
    }
    private void editBudget(BudgetItem budgetItem){
        budgetItem.setName(budget_ETXT_name.getText().toString())
                .setType(budget_ETXT_type.getText().toString())
                .setCost(Float.valueOf(budget_ETXT_price.getText().toString()));
        if(isNewIteam==true){
            theEvent.addShop(budgetItem);
        }
        MyDB.getInstance().addEvent(theEvent.getMy_uid(),theEvent);
        budget_LNR_newitem.setVisibility(View.INVISIBLE);
        budget_RTL_foucus.setVisibility(View.INVISIBLE);
        theEvent.updateBudget();
        refreshUI();
    }
    private void clickedOnBudget(BudgetItem budgetItem, int position){
        this.budgetItem=budgetItem;
        isNewIteam=false;
        budget_LNR_newitem.setVisibility(View.VISIBLE);
        budget_RTL_foucus.setVisibility(View.VISIBLE);
    }

    BudgetAdapter.BudgetListener budgetListener = new BudgetAdapter.BudgetListener() {
        @Override
        public void clicked(BudgetItem budgetItem, int position) {
            clickedOnBudget(budgetItem,position);
        }
        public void remove(BudgetItem budgetItem, int position) {
            theEvent.getShopping().remove(position);
            MyDB.getInstance().addEvent(theEvent.getMy_uid(),theEvent);
            refreshUI();
        }
    };



    private Fragment_callback fragment_callback;
    public void setFragment_callback(Fragment_callback fragment_callback) {
        this.fragment_callback = fragment_callback;
    }
}