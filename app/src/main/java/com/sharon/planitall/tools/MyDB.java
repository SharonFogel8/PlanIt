package com.sharon.planitall.tools;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.sharon.planitall.Objects.Events;
import com.sharon.planitall.Objects.MyUser;
import com.sharon.planitall.Objects.TDL;
import com.sharon.planitall.callbacks.Find_user_callback;
import com.sharon.planitall.callbacks.User_create_callback;
import com.sharon.planitall.callbacks.get_events_callback;
import com.sharon.planitall.callbacks.get_users_callback;

import java.util.HashMap;

public class MyDB {
    public static final String PRIVATE_ACCOUNTS = "PRIVATE_ACCOUNTS";
    public static final String EVENTS = "EVENTS";

    private static MyDB _instance = new MyDB();
    private Find_user_callback callback_find_user;
    private FirebaseDatabase database;
    private DatabaseReference refPrivateAccounts;
    private DatabaseReference refEvents;
    private get_events_callback get_events_callback;
    private get_users_callback get_users_callback;

    public MyDB() {
        database = FirebaseDatabase.getInstance("https://mystical-melody-374022-default-rtdb.europe-west1.firebasedatabase.app");
        refPrivateAccounts = database.getReference(PRIVATE_ACCOUNTS);
        refEvents= database.getReference(EVENTS);
    }

    public static MyDB getInstance() {
        return _instance;
    }



    public void createUser(MyUser user) {
        if (this.user_create_callback != null) {
            refPrivateAccounts.child(user.getMy_uid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
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
    public void getUsers(){
        if(get_users_callback!=null){
            refPrivateAccounts.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    GenericTypeIndicator<HashMap<String,MyUser>> dataType = new GenericTypeIndicator<HashMap<String, MyUser>>() {};
                    HashMap<String,MyUser> users = snapshot.getValue(dataType);
                    get_users_callback.getUsers(users);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

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
                    Log.d("myLog","is user exists"+user.toString());
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
    public void addEventToAccount(String eventUID, Events event){
        refPrivateAccounts.child(DataManager.get_instance().getUser().getMy_uid()).child("myEvents").child(eventUID).setValue(eventUID);
    }
    public void addTasks(TDL tdl){
        refPrivateAccounts.child(DataManager.get_instance().getUser().getMy_uid()).child("myTDL").child(tdl.getTask()).setValue(tdl);
    }
    public void addEventToAnotherAccount(String userUid, Events event){
        refPrivateAccounts.child(userUid).child("myEvents").child(event.getMy_uid()).setValue(event.getMy_uid());
    }

    public void addEvent(String UID,Events event){
        refEvents.child(UID).setValue(event);
    }
    public void getEvents(){
        if(this.get_events_callback!=null){
            refEvents.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    GenericTypeIndicator<HashMap<String,Events>> dataType = new GenericTypeIndicator<HashMap<String, Events>>() {};
                    HashMap<String,Events> events = snapshot.getValue(dataType);
                    get_events_callback.getEvents(events);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
    public void getUser(String userUid){
        if(this.get_users_callback!=null){
            refPrivateAccounts.child(userUid).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    MyUser user = dataSnapshot.getValue(MyUser.class);
                    get_users_callback.getUser(user);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    callback_find_user.error();
                }
            });
        }
    }
    public void getEvent(String eventUID) {
        if (this.get_events_callback != null) {
            refEvents.child(eventUID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Events event = dataSnapshot.getValue(Events.class);
                    get_events_callback.getEvent(event);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    callback_find_user.error();
                }
            });
        }
    }
    public void setGet_events_callback(get_events_callback get_events_callback){
        this.get_events_callback=get_events_callback;
    }
    public void setGet_users_callback(get_users_callback get_users_callback){
        this.get_users_callback= get_users_callback;
    }

}

