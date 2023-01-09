package com.sharon.planitall.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.sharon.planitall.R;
import com.sharon.planitall.callbacks.Fragment_callback;
import com.sharon.planitall.callbacks.Observable;
import com.sharon.planitall.fregments.HomeFragment;
import com.sharon.planitall.fregments.NewEventFragment;

public class HomePageActivity extends AppCompatActivity {
    private MaterialButton bottom_BTN_profile;
    private MaterialButton bottom_BTN_home;
    private MaterialButton bottom_BTN_favourites;
    private MaterialButton bottom_BTN_search;
    private MaterialButton bottom_BTN_tdl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        findViews();
        initViews();
        openHomeFragment();


    }

    private void initViews() {
        bottom_BTN_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomeFragment();
            }
        });

        bottom_BTN_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProfileFragment();
            }
        });
        bottom_BTN_favourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFavourites();


            }
        });
        bottom_BTN_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSearch();


            }
        });
        bottom_BTN_tdl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTDL();


            }
        });
    }
//TODO conect with all pages!
    private void openTDL() {
        refrushUI();
        bottom_BTN_tdl.setIconResource(R.drawable.icn_tdl_clicked);
    }

    private void refrushUI() {
        bottom_BTN_tdl.setIconResource(R.drawable.img_tdl_btn);
        bottom_BTN_search.setIconResource(R.drawable.img_search_btn);
        bottom_BTN_profile.setIconResource(R.drawable.img_profile_btn);
        bottom_BTN_favourites.setIconResource(R.drawable.img_faivorite_btn);
        bottom_BTN_home.setIconResource(R.drawable.img_home_btn);
    }

    private void openSearch() {
        refrushUI();
        bottom_BTN_search.setIconResource(R.drawable.icn_search_clicked);

    }

    private void openFavourites() {
        refrushUI();
        bottom_BTN_favourites.setIconResource(R.drawable.icn_faivorite_clicked);
    }

    private void openProfileFragment() {
        refrushUI();
        bottom_BTN_profile.setIconResource(R.drawable.icn_profile_clicked);
    }

    private void openHomeFragment() {
        HomeFragment homeActivity= new HomeFragment();
        refrushUI();
        bottom_BTN_home.setIconResource(R.drawable.img_home_clicked);
        fragment_callback.go_next(homeActivity,homeActivity);
    }

    private void findViews() {
        bottom_BTN_home=findViewById(R.id.bottom_BTN_home);
        bottom_BTN_favourites=findViewById(R.id.bottom_BTN_favourites);
        bottom_BTN_search=findViewById(R.id.bottom_BTN_search);
        bottom_BTN_tdl=findViewById(R.id.bottom_BTN_tdl);
        bottom_BTN_profile=findViewById(R.id.bottom_BTN_profile);
    }

    private Fragment_callback fragment_callback= new Fragment_callback() {
        @Override
        public  void go_next(Fragment next, Observable observable) {
            observable.setFragment_callback(this);
            HomePageActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.home_LAY_page, next).commit();

        }
    };
}