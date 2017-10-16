package com.toelai.androidplayground.notification;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.toelai.androidplayground.R;

public class NotiHandler1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView tv = new TextView(this);
        tv.setText("NoTiHandler - 1");
        setContentView(tv);

        processExtras();
    }

    private void processExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String text = extras.getString(Intent.EXTRA_TEXT);
            Toast.makeText(this, "Text: " + text, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No Extras: " + extras, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            startActivity(new Intent(this, NotiHandler2Activity.class));
            return true;
        }
        return super.onTouchEvent(event);
    }
}
