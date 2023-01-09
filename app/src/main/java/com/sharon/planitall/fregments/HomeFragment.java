package com.sharon.planitall.fregments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.button.MaterialButton;
import com.sharon.planitall.R;
import com.sharon.planitall.activities.HomePageActivity;
import com.sharon.planitall.callbacks.Fragment_callback;
import com.sharon.planitall.callbacks.Observable;


public class HomeFragment extends Fragment implements Observable {

    private MaterialButton home_BTN_myEvents;
    private MaterialButton home_BTN_invited;
    private MaterialButton home_BTN_newEvent;
    private HomePageActivity homePageActivity;
    View view;
    public HomeFragment(){
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        homePageActivity = new HomePageActivity();

        findViews();
        initViews();

        return view;
    }

    private void initViews() {
        home_BTN_newEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fragment_callback!=null){
                    NewEventFragment newEventFragment= new NewEventFragment();
                    fragment_callback.go_next(newEventFragment,newEventFragment);
                }

            }
        });
    }

    private void findViews() {
        home_BTN_myEvents= view.findViewById(R.id.home_BTN_myEvents);
        home_BTN_invited= view.findViewById(R.id.home_BTN_invited);
        home_BTN_newEvent= view.findViewById(R.id.home_BTN_newEvent);
    }
    private Fragment_callback fragment_callback;
    public void setFragment_callback(Fragment_callback fragment_callback) {
        this.fragment_callback = fragment_callback;
    }
}