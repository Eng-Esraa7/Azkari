package com.example.azkary;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

public class AlarmReciever extends BroadcastReceiver {
    private static final String channel_id = "SAMPLE_CHANNEL";
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        //get id & message from intent
        int notiId = intent.getIntExtra("notificationid",0);
        String message = intent.getStringExtra("message");
        //call main activity when noti is tapped
        Intent mainIntent = new Intent(context,MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(
                context,0,mainIntent,0
        );
        //noti manager
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //for Api 26 and above
            CharSequence channel_name = "My Notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channel_id,channel_name,importance);
            notificationManager.createNotificationChannel(channel);
        }
        //prepare notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,channel_id)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Title")
                .setContentText(message)
                .setContentIntent(contentIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);
        //notify
        notificationManager.notify(notiId,builder.build());
    }
}
