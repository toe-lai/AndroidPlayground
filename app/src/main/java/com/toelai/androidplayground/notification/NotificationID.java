package com.toelai.androidplayground.notification;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.concurrent.atomic.AtomicInteger;

public class NotificationID {

    private final static AtomicInteger c = new AtomicInteger(9);
    public static int getID() {
        return c.incrementAndGet();
    }

}
