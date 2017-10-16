package com.toelai.androidplayground.notification;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.toelai.androidplayground.R;
import com.toelai.androidplayground.alarm.AlarmInitiatorService;

import java.util.concurrent.atomic.AtomicInteger;

public class NotificationActivity extends AppCompatActivity {

    private final String CHANNEL_ID = "PLAYGROUND_NOTIFICATION_CHANNEL_ID";

    private static final int REQUEST_CODE = 0;

    private AtomicInteger mNotificationID = new AtomicInteger(100);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AlarmInitiatorService.start(this);
    }

    public void showNotification(View view) {
        final String CHANNEL_ID = "PLAYGROUND_NOTIFICATION_CHANNEL_ID";
        for (int i = 0; i < 3; i++) {
            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(this, CHANNEL_ID)
                            .setSmallIcon(R.drawable.ic_local_play_color_24dp)
                            .setAutoCancel(true)
                            .setContentTitle("My notification")
                            .setColor(ContextCompat.getColor(this, R.color.md_indigo_500))
                            .setContentText("This should pass extra - " + i);
            builder.setContentIntent(createPendingIntent(i));

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

    public void startNotiHandler1(View view) {
        startActivity(new Intent(this, NotiHandler1Activity.class));
    }

    public void showRegularActNoti(View view) {
        Intent resultIntent = new Intent(this, RegularActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        // Adds the back stack
        stackBuilder.addParentStack(RegularActivity.class);
        // Adds the Intent to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        // Gets a PendingIntent containing the entire back stack
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        final String CHANNEL_ID = "PLAYGROUND_NOTIFICATION_CHANNEL_ID";
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_local_play_color_24dp)
                        .setAutoCancel(true)
                        .setContentTitle("Regular Act")
                        .setColor(ContextCompat.getColor(this, R.color.md_indigo_500))
                        .setContentText("That will launch Regular Activity");
        builder.setContentIntent(resultPendingIntent);

        NotificationManagerCompat notificationManagerCompat
                = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NotificationID.getID(), builder.build());
    }

    public void showSpecialActNoti(View view) {


        // Instantiate a Builder object.
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_local_play_color_24dp)
                        .setAutoCancel(true)
                        .setContentTitle("Special Act")
                        .setColor(ContextCompat.getColor(this, R.color.md_indigo_500))
                        .setContentText("That will launch Special Activity");
        // Creates an Intent for the Activity
        Intent notifyIntent =
                new Intent(this, SpecialActivity.class);
                //new Intent(new ComponentName(this, SpecialActivity.class));
        // Sets the Activity to start in a new, empty task
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TASK);
        // Creates the PendingIntent
        PendingIntent pendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        notifyIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        // Puts the PendingIntent into the notification builder
        builder.setContentIntent(pendingIntent);
        // Notifications are issued by sending them to the
        // NotificationManager system service.
        NotificationManagerCompat mNotificationManager = NotificationManagerCompat.from(this);
        // Builds an anonymous Notification object from the builder, and
        // passes it to the NotificationManager
        mNotificationManager.notify(NotificationID.getID(), builder.build());
    }

    public void startAlarmService(View view) {
        AlarmInitiatorService.start(this);
    }
}
