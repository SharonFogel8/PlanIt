package com.sharon.planitall.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.sharon.planitall.Objects.MyUser;
import com.sharon.planitall.R;
import com.sharon.planitall.callbacks.Find_user_callback;
import com.sharon.planitall.tools.DataManager;
import com.sharon.planitall.tools.MyDB;

public class ActivityEnterApp extends AppCompatActivity {
    private LottieAnimationView enter_animation_view;

    // TODO: 08/01/2023 change lottie to lottie app icon
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_app);
        MyDB.getInstance().setCallback_find_user(callback_find_account);
        findViews();
        decide_page_to_open();
    }


    private void findViews() {
        enter_animation_view = findViewById(R.id.enter_animation_view);
    }

    private void decide_page_to_open() {
        enter_animation_view.playAnimation();
        enter_animation_view.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                verifyUser();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }
            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });

    }

    private void verifyUser() {
        if(FirebaseAuth.getInstance().getCurrentUser() == null){
            go_next(LoginActivity.class);
            return;
        }
        MyDB.getInstance().isUserExists(FirebaseAuth.getInstance().getCurrentUser().getUid());
    }

    private Find_user_callback callback_find_account= new Find_user_callback() {
        @Override
        public void error() {
            Toast.makeText(ActivityEnterApp.this, "Something went worng... please, check your network", Toast.LENGTH_LONG).show();
            go_next(LoginActivity.class);
        }

        @Override
        public void user_found(MyUser myUser) {
            DataManager.get_instance().setUser(myUser);
            go_next(HomePageActivity.class);
        }

        @Override
        public void user_not_found() {
            go_next(RegisterActivity.class);
        }
    };
    private <T extends AppCompatActivity> void go_next(Class<T> nextActivity ) {
        Intent intent = new Intent(this, nextActivity);
        startActivity(intent);
        finish();
    }
}