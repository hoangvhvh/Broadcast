package org.duchoang.broadcast;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

public class NotificationReceiver extends BroadcastReceiver {

    private static final String CHANNEL_ID = "Your_Channel_ID";
    private static final int NOTIFICATION_ID = 0;

    @SuppressLint("NotificationPermission")
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Channel Name",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            notificationManager.createNotificationChannel(channel);
        }

        Notification notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle("Thông báo")
                .setContentText("Đây là thông báo theo lịch đặt")
                .setSmallIcon(R.drawable.ic_notification)
                .setCustomContentView(getNotificationLayout(context))
                .build();

        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    private RemoteViews getNotificationLayout(Context context) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.activity_notification);
        remoteViews.setTextViewText(R.id.tv_title, "Thông báo");
        remoteViews.setTextViewText(R.id.tv_content, "Đây là thông báo theo lịch đặt");
        return remoteViews;
    }
}
