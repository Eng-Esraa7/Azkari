package com.example.azkary;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class notification2 extends AppCompatActivity implements View.OnClickListener {
    private int notiId = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        findViewById(R.id.setbtn).setOnClickListener(this);
        findViewById(R.id.cancelbtn).setOnClickListener(this);

        TextView t = findViewById(R.id.textnoti);
        t.setText("أذكار المساء");
    }

    @Override
    public void onClick(View v) {
        TimePicker timePicker = findViewById(R.id.timerpicker);
        //set noti& message
        Intent intent = new Intent(notification2.this,AlarmReciever.class);
        intent.putExtra("notificationid",notiId);
        intent.putExtra("message","حان موعد قراه اذكار المساء");
        //pending intent
        PendingIntent alarmintent = PendingIntent.getBroadcast(
                notification2.this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT
        );
        //alarm manager
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        switch (v.getId()){
            case R.id.setbtn:
                //set alarm
                int hour = timePicker.getCurrentHour();
                int min = timePicker.getCurrentMinute();
                //create time
                Calendar starttime = Calendar.getInstance();
                starttime.set(Calendar.HOUR_OF_DAY,hour);
                starttime.set(Calendar.MINUTE,min);
                starttime.set(Calendar.SECOND,0);
                long alarmstarttime = starttime.getTimeInMillis();
                //set alarm
                alarmManager.set(AlarmManager.RTC_WAKEUP,alarmstarttime,alarmintent);
                Toast.makeText(notification2.this,"Done!",Toast.LENGTH_LONG).show();
                break;
            case R.id.cancelbtn:
                //cancel alarm
                alarmManager.cancel(alarmintent);
                Toast.makeText(notification2.this,"Canceled",Toast.LENGTH_LONG).show();
                break;
        }
    }
}