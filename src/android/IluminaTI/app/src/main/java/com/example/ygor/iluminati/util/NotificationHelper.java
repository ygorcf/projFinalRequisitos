package com.example.ygor.iluminati.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;

import com.example.ygor.iluminati.R;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by Ygor on 20/11/2017.
 */

public class NotificationHelper {

        public static void newNotification(Context context, CharSequence mensagemBarraStatus,
                CharSequence titulo, CharSequence mensagemMini, CharSequence mensagemCompleta, long quandoAparecer, Class activity) {
            // Recupera o serviço do NotificationManager
            NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            builder.setSmallIcon(R.drawable.notification);
            builder.setTicker(mensagemBarraStatus);
            builder.setWhen(System.currentTimeMillis());
            builder.setContentTitle(titulo);
            builder.setContentText(mensagemMini);
            builder.setSubText(mensagemCompleta);

            Intent intent = new Intent(context, activity);
            intent.putExtra("notify", false);

            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addParentStack(activity);
            stackBuilder.addNextIntent(intent);

            PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
            builder.setContentIntent(resultPendingIntent);
            Notification n = builder.build();

            n.flags |= Notification.FLAG_AUTO_CANCEL;

            n.vibrate = new long[] { 100, 250, 100, 500 };

            manager.notify(R.string.app_name, n);
    }
}
