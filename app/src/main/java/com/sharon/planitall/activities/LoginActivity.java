package com.sharon.planitall.activities;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.sharon.planitall.Objects.MyUser;
import com.sharon.planitall.R;
import com.sharon.planitall.callbacks.Find_user_callback;
import com.sharon.planitall.tools.DataManager;
import com.sharon.planitall.tools.MyDB;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private MaterialButton start_BTN_Login;
    private MaterialButton start_BTN_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        MyDB.getInstance().setCallback_find_user(callback_find_account);
        findViews();
    }

    private void findViews() {
        start_BTN_Login = findViewById(R.id.start_BTN_Login);
        start_BTN_register = findViewById(R.id.start_BTN_register);
        start_BTN_Login.setOnClickListener(clickListener);
        start_BTN_register.setOnClickListener(clickListener);
    }
    private View.OnClickListener clickListener = view -> make_FirebaseAuth();

    private void make_FirebaseAuth() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.PhoneBuilder().build());
        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setLogo(R.drawable.logo)      // Set logo drawable
                .setTheme(R.style.Theme_PlanItAll)      // Set theme
                .setTosAndPrivacyPolicyUrls("https://firebase.google.com/docs/auth/android/firebaseui?hl=en&authuser=0", "https://firebase.google.com/docs/auth/android/firebaseui?hl=en&authuser=0")
                .build();
        signInLauncher.launch(signInIntent);
    }

    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            result -> connect_to_user()
    );
    private void connect_to_user() {

        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            Toast.makeText(this, "There was a problem in enter details, please try again", Toast.LENGTH_LONG).show();

            return;
        }
        MyDB.getInstance().isUserExists( FirebaseAuth.getInstance().getCurrentUser().getUid());
    }

    private <T extends AppCompatActivity> void go_next(Class<T> nextActivity ) {
        Intent intent = new Intent(this, nextActivity);
        startActivity(intent);
        finish();
    }
    private Find_user_callback callback_find_account= new Find_user_callback() {
        @Override
        public void error() {
            Toast.makeText(LoginActivity.this, "Something went wrong... please, check your network", Toast.LENGTH_LONG).show();
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
}