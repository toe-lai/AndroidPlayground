package com.toelai.androidplayground.alarm;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import java.util.Calendar;

public class AlarmInitiatorService extends IntentService {

    public static final String TAG = AlarmInitiatorService.class.getSimpleName();

    public AlarmInitiatorService() {
        super("AlarmInitiatorService");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, AlarmInitiatorService.class);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent: " + TAG + " is starting ...");
        setAlarm();
        enableReceiver();
    }

    /**
     * Sets a repeating alarm that runs once a day at approximately 7:00 a.m.
     */
    private void setAlarm() {

        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,
                intent, 0);

        final long INTERVAL_FIFTEEN_MINUTES = 1 * 60 * 1000;

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, getAlarmTriggerTime().getTimeInMillis(),
                    INTERVAL_FIFTEEN_MINUTES, pendingIntent);
        } else {
            Log.e(TAG, "setAlarm: " + "AlarmManager is null.", new NullPointerException());
        }
    }

    // Get the alarm's trigger time in 7.00 (7 AM)
    private Calendar getAlarmTriggerTime() {

        Calendar now = Calendar.getInstance();

        Calendar alarmStartTime = Calendar.getInstance();
        alarmStartTime.setTimeInMillis(System.currentTimeMillis());
        alarmStartTime.set(Calendar.HOUR_OF_DAY, 7);
        alarmStartTime.set(Calendar.MINUTE, 0);
        alarmStartTime.set(Calendar.SECOND, 0);


        if (now.after(alarmStartTime)) {
            alarmStartTime.add(Calendar.DATE, 1);
        }

        return alarmStartTime;
    }

    private void enableReceiver() {
        ComponentName receiver = new ComponentName(this, PlaygroundBootReceiver.class);
        PackageManager pm = getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }
}
