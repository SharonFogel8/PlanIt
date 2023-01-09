package com.sharon.planitall.callbacks;


import com.sharon.planitall.Objects.MyUser;

public interface Find_user_callback {
    void error();
    void user_found(MyUser myUser);
    void user_not_found();
}
