package com.sharon.planitall.callbacks;

import com.sharon.planitall.Objects.Events;
import com.sharon.planitall.Objects.MyUser;

import java.util.HashMap;

public interface get_users_callback {
    void getUsers(HashMap<String, MyUser> users);
    void getUser(MyUser user);
}
