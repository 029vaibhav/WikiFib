package com.teamie.wikifib.application;

import android.app.Application;

import com.teamie.wikifib.utils.Utilities;

/**
 * Created by vaibhav on 23/12/16.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utilities.getInstance().setContext(this);
    }
}
