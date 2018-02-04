package dev.mrodriguezul.mismascotas.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by MIGUEL on 3/02/2018.
 */

public class NotificationBroadcast extends BroadcastReceiver{
    private static final String TAG = "notification-broadcast";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.i(TAG,"LLegada de broadcast, accion: "+action);

        Toast.makeText(context, "Si llegó la acción: "+action, Toast.LENGTH_LONG).show();

        switch (action){
            case ActionKeys.ACTION_FOLLOW:
                Toast.makeText(context, "FOLLOW", Toast.LENGTH_LONG).show();
                //something Follow!!
                break;
            case ActionKeys.ACTION_VIEWP_USER:
                Toast.makeText(context, "Ver USUARIO", Toast.LENGTH_LONG).show();
                //view user profile
                break;

        }




    }
}
