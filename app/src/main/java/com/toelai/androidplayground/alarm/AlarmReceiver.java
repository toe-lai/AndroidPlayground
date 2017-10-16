package com.toelai.androidplayground.alarm;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.toelai.androidplayground.R;
import com.toelai.androidplayground.notification.NotiHandler1Activity;
import com.toelai.androidplayground.notification.NotificationID;

public class AlarmReceiver extends BroadcastReceiver {

    private static final String TAG = AlarmReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: " + "AlarmReceiver is running ...");
        AlarmHandlerService.start(context);
    }
}
