package dev.mrodriguezul.mismascotas.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;
import android.util.Log;
import android.view.Gravity;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import dev.mrodriguezul.mismascotas.MainActivity;
import dev.mrodriguezul.mismascotas.R;

/**
 * Created by MIGUEL on 22/01/2018.
 */

public class NotificationService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";
    private static final int NOTIFICATION_ID = 001;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //super.onMessageReceived(remoteMessage);

        Intent intentPerfil = new Intent(this, MainActivity.class);
        intentPerfil.setAction(ActionKeys.ACTION_VIEW_PROFILE);
        Intent intentFollow = new Intent(ActionKeys.ACTION_FOLLOW);
        Intent intentVerUsuario = new Intent(ActionKeys.ACTION_VIEWP_USER);

        PendingIntent pendingIntentPerfil = PendingIntent.getActivity(this, 0, intentPerfil, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentFollow = PendingIntent.getBroadcast(this, 0, intentFollow, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentVerusuario = PendingIntent.getBroadcast(this, 0, intentVerUsuario, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Action actionPerfil = new NotificationCompat.Action.Builder(R.drawable.ic_full_perfil,getString(R.string.action_perfil), pendingIntentPerfil).build();
        NotificationCompat.Action actionFollow = new NotificationCompat.Action.Builder(R.drawable.ic_full_follow,getString(R.string.action_follow), pendingIntentFollow).build();
        NotificationCompat.Action actionVerUsuario = new NotificationCompat.Action.Builder(R.drawable.ic_full_verusuario,getString(R.string.action_verusuario), pendingIntentVerusuario).build();

        //Se setea todas las acciones para el movil
        NotificationCompat.WearableExtender wearableExtender =
                new NotificationCompat.WearableExtender()
                        .setHintHideIcon(true)
                        .setBackground(BitmapFactory.decodeResource(getResources(),R.drawable.bk_androidwear_notification))
                        .setGravity(Gravity.CENTER_VERTICAL);

        /*wearableExtender.addAction(actionPerfil)
                        .addAction(actionFollow);*/

        Log.i(TAG, "mrodriguezul - se creo la extención para el weareable");

        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_pets)
                .setContentTitle(remoteMessage.getNotification().getTitle())
                .setContentText(remoteMessage.getNotification().getBody())
                .setSound(sonido)
                .setContentIntent(pendingIntentPerfil)
                .setAutoCancel(true)
                .extend(wearableExtender.addAction(actionPerfil).addAction(actionFollow).addAction(actionVerUsuario))
                .addAction(actionFollow)
                .addAction(actionVerUsuario);

                //.addAction(0,"action",pendingIntentPerfil);

        Log.i(TAG, "mrodriguezul - Lanzamos la notificación!!");

        //NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, notificacion.build());
    }
}
