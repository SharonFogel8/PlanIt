package com.sharon.planitall.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.sharon.planitall.R;
import com.sharon.planitall.callbacks.Fragment_callback;
import com.sharon.planitall.callbacks.Observable;
import com.sharon.planitall.fregments.EventProfileFragment;
import com.sharon.planitall.fregments.HomeFragment;
import com.sharon.planitall.fregments.NewEventFragment;
import com.sharon.planitall.fregments.TDLFragment;
import com.sharon.planitall.tools.DataManager;

public class HomePageActivity extends AppCompatActivity {
    private AppCompatImageView bottom_BTN_profile;
    private AppCompatImageView bottom_BTN_home;
    private AppCompatImageView bottom_BTN_favourites;
    private AppCompatImageView bottom_BTN_search;
    private AppCompatImageView bottom_BTN_tdl;
    private MaterialButton bottom_BTN_signout;
    private MaterialButton bottom_BTN_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        findViews();
        initViews();
        openHomeFragment();
//        Fragment next_frag = DataManager.get_instance().get_next_frag();
//        if (next_frag == null) {
//            next_frag = new HomeFragment();
//        }
//        ((Observable) next_frag).setFragment_callback(fragment_callback);
//        HomePageActivity.this.getSupportFragmentManager().beginTransaction().add(R.id.home_LAY_page, next_frag).commit();


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
        bottom_BTN_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
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
        bottom_BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(DataManager.get_instance().getCurrentEvent()!=null){
                    EventProfileFragment eventProfileFragment= new EventProfileFragment();
                    fragment_callback.go_next(eventProfileFragment,eventProfileFragment);
                }
                else
                    openHomeFragment();
            }
        });
    }

    private void openTDL() {
        refrushUI();


        bottom_BTN_tdl.setImageResource(R.drawable.icn_tdl_clicked);
        TDLFragment tdlFragment = new TDLFragment();
        fragment_callback.go_next(tdlFragment, tdlFragment);
    }

    private void refrushUI() {
        bottom_BTN_tdl.setImageResource(R.drawable.img_tdl_btn);
        bottom_BTN_search.setImageResource(R.drawable.img_search_btn);
        bottom_BTN_profile.setImageResource(R.drawable.img_profile_btn);
        bottom_BTN_favourites.setImageResource(R.drawable.img_faivorite_btn);
        bottom_BTN_home.setImageResource(R.drawable.img_home_btn);
    }

    private void openSearch() {
        refrushUI();
        bottom_BTN_search.setImageResource(R.drawable.icn_search_clicked);

    }

    private void openFavourites() {
        refrushUI();
        bottom_BTN_favourites.setImageResource(R.drawable.icn_faivorite_clicked);
    }

    private void openProfileFragment() {
        refrushUI();
        bottom_BTN_profile.setImageResource(R.drawable.icn_profile_clicked);
    }

    private void openHomeFragment() {
        HomeFragment homeActivity = new HomeFragment();
        refrushUI();
        bottom_BTN_home.setImageResource(R.drawable.img_home_clicked);
        fragment_callback.go_next(homeActivity, homeActivity);
    }

    private void findViews() {
        bottom_BTN_home = findViewById(R.id.bottom_BTN_home);
        bottom_BTN_favourites = findViewById(R.id.bottom_BTN_favourites);
        bottom_BTN_search = findViewById(R.id.bottom_BTN_search);
        bottom_BTN_tdl = findViewById(R.id.bottom_BTN_tdl);
        bottom_BTN_profile = findViewById(R.id.bottom_BTN_profile);
        bottom_BTN_signout = findViewById(R.id.bottom_BTN_signout);
        bottom_BTN_back= findViewById(R.id.bottom_BTN_back);
    }

    private Fragment_callback fragment_callback = new Fragment_callback() {
        @Override
        public void go_next(Fragment next, Observable observable) {
            observable.setFragment_callback(this);
            HomePageActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.home_LAY_page, next).commit();
//            DataManager.get_instance().set_current_frage(next);
//            Intent intent = new Intent(HomePageActivity.this, HomePageActivity.class);
//            startActivity(intent);
//            HomePageActivity.this.finish();
        }

    };
    private void signOut(){
        FirebaseAuth.getInstance().signOut();
        go_next(LoginActivity.class);
    }
    private <T extends AppCompatActivity> void go_next(Class<T> nextActivity ) {
        Intent intent = new Intent(this, nextActivity);
        startActivity(intent);
        finish();
    }
}