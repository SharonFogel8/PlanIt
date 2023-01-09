package com.sharon.planitall.tools;


import com.sharon.planitall.Objects.MyUser;

public class DataManager {
    private static DataManager _instance= new DataManager();

    private MyUser user;

    public DataManager() {
    }

    public static DataManager get_instance() {
        return _instance;
    }

    public static void set_instance(DataManager _instance) {
        DataManager._instance = _instance;
    }

    public MyUser getUser() {
        return user;
    }

    public DataManager setUser(MyUser user) {
        this.user = user;
        return this;
    }



}
