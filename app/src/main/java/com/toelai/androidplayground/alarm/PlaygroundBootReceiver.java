package com.toelai.androidplayground.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


/**
 * Created by bomber_bus on 10/9/2017.
 */

public class PlaygroundBootReceiver extends BroadcastReceiver {

    public static final String TAG = PlaygroundBootReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: ");
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            Log.d(TAG, "onReceive: By Boot");
            AlarmInitiatorService.start(context);
        }
    }
}
