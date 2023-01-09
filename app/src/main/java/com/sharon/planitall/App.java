package com.sharon.planitall;

import android.app.Application;

import com.sharon.planitall.tools.MyDB;
import com.sharon.planitall.tools.MyServices;


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MyDB.getInstance();
        MyServices.initHelper(this);

    }
}
