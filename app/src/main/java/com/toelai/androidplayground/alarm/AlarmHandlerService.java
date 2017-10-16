package com.toelai.androidplayground.alarm;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.toelai.androidplayground.R;
import com.toelai.androidplayground.notification.NotiHandler1Activity;
import com.toelai.androidplayground.notification.NotificationID;

/**
 * Created by toelie on 10/13/17.
 */

public class AlarmHandlerService extends IntentService {

    private static final String TAG = AlarmHandlerService.class.getSimpleName();

    public static void start(Context context) {
        context.startService(new Intent(context, AlarmHandlerService.class));
    }

    public AlarmHandlerService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "onHandleIntent: ");
        generateNotification();
    }


    private void generateNotification() {
        Log.d(TAG, "generateNotification: " + "Generating notification ...");

        final String CHANNEL_ID = "PLAYGROUND_NOTIFICATION_CHANNEL_ID";
        for (int i = 0; i < 3; i++) {
            NotificationCompat.Builder builder =
                    new android.support.v4.app.NotificationCompat.Builder(this, CHANNEL_ID)
                            .setSmallIcon(R.drawable.ic_local_play_color_24dp)
                            .setAutoCancel(true)
                            .setContentTitle("My notification")
                            .setColor(ContextCompat.getColor(this, R.color.md_indigo_500))
                            .setContentText("This should pass extra - " + i);
            builder.setContentIntent(createPendingIntent(i));


            //notification.defaults |= Notification.DEFAULT_LIGHTS; // LED
            //notification.defaults |= Notification.DEFAULT_VIBRATE; //Vibration
            //notification.defaults |= Notification.DEFAULT_SOUND; // Sound
            //notification.defaults  |= Notification.FLAG_AUTO_CANCEL;//clear noti

            NotificationManagerCompat notificationManagerCompat
                    = NotificationManagerCompat.from(this);
            notificationManagerCompat.notify(NotificationID.getID(), builder.build());
        }
    }

    private PendingIntent createPendingIntent(int i) {
        String text = "TEXT#" + i;
        Intent intent = new Intent(this, NotiHandler1Activity.class);
        Bundle extras = new Bundle();
        extras.putString(Intent.EXTRA_TEXT, text);
        intent.putExtras(extras);

        return PendingIntent.getActivity(this, i, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
