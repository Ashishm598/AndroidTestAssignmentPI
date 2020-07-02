package com.assignment.shadiandroidtest.app;

import android.app.Application;
import android.content.Context;

public class MainApplication extends Application {

    private static Context appContext;

    public static final String LOG_TAG = "tm-tag";

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }

    public static Context getAppContext() {
        return appContext;
    }


}
