package com.assignment.shadiandroidtest.app;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.assignment.shadiandroidtest.BuildConfig;
import com.assignment.shadiandroidtest.entities.MyObjectBox;

import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;

public class MainApplication extends Application {

    private static Context appContext;

    public static final String LOG_TAG = "tm-tag";

    private static BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;

        boxStore = MyObjectBox.builder().androidContext(this).build();

        if (BuildConfig.DEBUG) {
            boolean started = new AndroidObjectBrowser(boxStore).start(this);
            Log.i("ObjectBrowser", "Started: " + started);
        }
    }

    public static Context getAppContext() {
        return appContext;
    }

    public static BoxStore getBoxStore() {
        return boxStore;
    }


}
