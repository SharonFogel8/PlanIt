package com.sharon.planitall.fregments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sharon.planitall.Objects.Events;
import com.sharon.planitall.R;
import com.sharon.planitall.activities.HomePageActivity;
import com.sharon.planitall.callbacks.Fragment_callback;
import com.sharon.planitall.callbacks.Observable;

public class TDLFragment extends Fragment implements Observable {

    private View view;

    public TDLFragment() {
        // Required empty public constructor
    }




    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tdl, container, false);


        return view;
    }


    private Fragment_callback fragment_callback;
    public void setFragment_callback(Fragment_callback fragment_callback) {
        this.fragment_callback = fragment_callback;
    }
}