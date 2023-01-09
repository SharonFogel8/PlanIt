package com.sharon.planitall.callbacks;


import com.sharon.planitall.Objects.MyUser;

public interface User_create_callback {
    void user_create(MyUser user);
    void error();
}
