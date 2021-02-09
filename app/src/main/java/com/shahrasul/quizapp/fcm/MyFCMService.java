package com.shahrasul.quizapp.fcm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.shahrasul.quizapp.MainActivity;
import com.shahrasul.quizapp.R;

import java.util.Map;

public class MyFCMService extends FirebaseMessagingService {
    private String title;
    private String message;
    private String imageUrl;
    private String type;
    private int notificationId = 0;
    private Map<String, String> data;

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        OnMessageExecute(remoteMessage);

    }

    public void OnMessageExecute(RemoteMessage remoteMessage) {
        data = remoteMessage.getData();
        title = data.get("title");                                                        //shah lox
        if (remoteMessage.getData().containsKey("content")
                && remoteMessage.getData().get("content") != null) {
            message = data.get("content");
        }

        if (remoteMessage.getData().containsKey("id")
                && remoteMessage.getData().get("id") != null) {
            notificationId = Integer.valueOf(data.get("id"));
        }
            Intent intent = new Intent(this, MainActivity.class);
        showNotifications(intent);
    }

    private void showNotifications(Intent intent) {
        String channelId = "Default";
        PendingIntent pendingIntent = PendingIntent.getActivity(this, notificationId, intent, PendingIntent.FLAG_ONE_SHOT);


        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        NotificationCompat.Builder notBuilder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_icon)
                .setContentTitle(title)
                .setContentText("message")
                .setAutoCancel(true)
                .setSound(uri)
                .setContentIntent(pendingIntent);

        Notification notification = notBuilder.build();

        notification.tickerText = title;

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(notificationId,notification);





    }
}