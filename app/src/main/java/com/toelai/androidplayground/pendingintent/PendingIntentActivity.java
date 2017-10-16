package com.toelai.androidplayground.pendingintent;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.toelai.androidplayground.R;

public class PendingIntentActivity extends AppCompatActivity {
    public static final String TAG = PendingIntentActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_intent);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void testIntentfilterEquals(View view) {
        // same
        /* Intent intent1 = new Intent(PendingIntentActivity.this, PICallingActivity.class);
        Intent intent2 = new Intent(PendingIntentActivity.this, PICallingActivity.class); */

        // same
        /* Intent intent1 = new Intent(PendingIntentActivity.this, PICallingActivity.class)
                .putExtra(Intent.EXTRA_TEXT, "Hello");
        Intent intent2 = new Intent(PendingIntentActivity.this, PICallingActivity.class); */

        // same
        /* Intent intent1 = new Intent(PendingIntentActivity.this, PICallingActivity.class);
        Bundle extras = new Bundle();
        extras.putString(Intent.EXTRA_TEXT, "Hello");
        intent1.putExtras(extras);

        Intent intent2 = new Intent(PendingIntentActivity.this, PICallingActivity.class);
        Bundle extras2 = new Bundle();
        extras2.putString(Intent.EXTRA_TITLE, "Hi");
        intent2.putExtras(extras2); */

        // not same
        /*Intent intent1 = new Intent(PendingIntentActivity.this, PICallingActivity.class)
                .putExtra(Intent.EXTRA_TEXT, "Hello");
        intent1.setData(Uri.parse("http://"));

        Intent intent2 = new Intent(PendingIntentActivity.this, PICallingActivity.class);*/

        // same
        Intent intent1 = new Intent(PendingIntentActivity.this, PICallingActivity.class);
        Intent intent2 = new Intent(PendingIntentActivity.this, PICalling2Activity.class);

        boolean result = intent1.filterEquals(intent2);
        Log.d(TAG, "testIntentfilterEquals: " + result);
    }

    public void startPendingIntent(View view) {
        final int REQUEST_CODE = 0;
        Intent intent1 = new Intent(PendingIntentActivity.this, PICallingActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, REQUEST_CODE, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        try {
            pendingIntent.send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
        if (pendingIntent == null) {
            Log.d(TAG, "startPendingIntent: " + "PendingIntent is null.");
        } else {
            Log.d(TAG, "startPendingIntent: " + "PendingIntent is not null.");
        }
    }

}
