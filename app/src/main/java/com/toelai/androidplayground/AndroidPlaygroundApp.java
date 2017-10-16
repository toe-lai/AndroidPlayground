package com.toelai.androidplayground;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by toelie on 10/12/17.
 */

public class AndroidPlaygroundApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
