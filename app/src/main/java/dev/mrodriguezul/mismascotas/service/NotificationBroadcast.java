package dev.mrodriguezul.mismascotas.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import dev.mrodriguezul.mismascotas.MainActivity;

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
                Toast.makeText(context, "Seguimos a este usuario", Toast.LENGTH_LONG).show();
                //something Follow!!
                //NO hay ENDpoint Instagram, pero la invocación va desde aquí
                break;
            case ActionKeys.ACTION_VIEWP_USER:
                Toast.makeText(context, "Ver Usuario", Toast.LENGTH_LONG).show();
                //view user profile
                Intent intentVerUsuario = new Intent(context.getApplicationContext(), MainActivity.class);
                intentVerUsuario.setAction(ActionKeys.ACTION_VIEWP_USER);
                context.getApplicationContext().startActivity(intentVerUsuario);
                break;
        }




    }
}
