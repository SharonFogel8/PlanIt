package com.sharon.planitall.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.firebase.auth.FirebaseAuth;
import com.sharon.planitall.Objects.MyUser;
import com.sharon.planitall.R;
import com.sharon.planitall.callbacks.User_create_callback;
import com.sharon.planitall.tools.DataManager;
import com.sharon.planitall.tools.MyDB;

public class RegisterActivity extends AppCompatActivity {
    private MaterialButton register_BTN_photo;
    private EditText register_ETXT_name;
    private MaterialButton      register_BTN_create;
    private MaterialRadioButton register_BTN_supplier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        MyDB.getInstance().setCallback_account_creating(user_create_callback);
        findViews();
        initActions();
    }

    private void initActions() {
        register_BTN_photo.setOnClickListener(view -> {
            addPhoto();
        });
        register_BTN_create.setOnClickListener(view -> {
            MyUser user= new MyUser();
            user.setName(register_ETXT_name.getText().toString()).setUID(FirebaseAuth.getInstance().getCurrentUser().getUid());
            MyDB.getInstance().createUser(user);
        });
    }

    private void addPhoto() {
        //TODO image
    }

    private void findViews() {
        register_BTN_photo=findViewById(R.id.register_BTN_photo);
        register_ETXT_name=findViewById(R.id.register_ETXT_name);
        register_BTN_create=findViewById(R.id.register_BTN_create);
        register_BTN_supplier=findViewById(R.id.register_BTN_supplier);
    }
    private <T extends AppCompatActivity> void go_next(Class<T> nextActivity ) {
        Intent intent = new Intent(this, nextActivity);
        startActivity(intent);
        finish();
    }
    private User_create_callback user_create_callback = new User_create_callback() {
        @Override
        public void user_create(MyUser user) {
            DataManager.get_instance().setUser(user);
            go_next(HomePageActivity.class);
        }

        @Override
        public void error() {
            Toast.makeText(RegisterActivity.this,"Something went wrong, please check your network" , Toast.LENGTH_LONG).show();
        }
    };
}