package com.sharon.planitall.tools;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sharon.planitall.Objects.MyUser;
import com.sharon.planitall.callbacks.Find_user_callback;
import com.sharon.planitall.callbacks.User_create_callback;

public class MyDB {
    public static final String PRIVATE_ACCOUNTS = "PRIVATE_ACCOUNTS";
    public static final String EVENTS = "EVENTS";

    private static MyDB _instance = new MyDB();
    private Find_user_callback callback_find_user;
    private FirebaseDatabase database;
    private DatabaseReference refPrivateAccounts;

    public MyDB() {
        database = FirebaseDatabase.getInstance("https://mystical-melody-374022-default-rtdb.europe-west1.firebasedatabase.app");
        refPrivateAccounts = database.getReference(PRIVATE_ACCOUNTS);

    }

    public static MyDB getInstance() {
        return _instance;
    }



    public void createUser(MyUser user) {
        if (this.user_create_callback != null) {
            refPrivateAccounts.child(user.getUID()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        user_create_callback.user_create(user);
                        return;
                    }
                    user_create_callback.error();
                }
            });
        }
    }

    public void isUserExists(String accountID) {
        if (this.callback_find_user != null) {
            refPrivateAccounts.child(accountID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    MyUser user = null;
                    user = dataSnapshot.getValue(MyUser.class);
                    if (user == null) {
                        callback_find_user.user_not_found();
                        return;
                    }
                    callback_find_user.user_found(user);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    callback_find_user.error();
                }
            });
        }
    }

    private User_create_callback user_create_callback;

    public void setCallback_account_creating(User_create_callback callback_account_creating) {
        this.user_create_callback = callback_account_creating;
    }
    public MyDB setCallback_find_user(Find_user_callback callback_find_user) {
        this.callback_find_user = callback_find_user;
        return this;
    }
}

