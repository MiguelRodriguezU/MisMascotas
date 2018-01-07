package dev.mrodriguezul.mismascotas.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences {
    private static Preferences instancia = null;
    private SharedPreferences mSharedPrefs;
    private SharedPreferences.Editor mPrefsEditor;

    private Context mContext;

    private final String PREFERENCE_USUARIO_NOMBRE = "PREFERENCE_USUARIO_NOMBRE";

    public static Preferences getInstancia(Context mContext){
        if(instancia == null){
            instancia = new Preferences(mContext);
        }
        return instancia;
    }

    public Preferences(Context mContext) {
        this.mContext = mContext;

        mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.mContext.getApplicationContext());
        mPrefsEditor = mSharedPrefs.edit();
    }

    public void setUsuarioNombre(String value){
        mPrefsEditor.putString(PREFERENCE_USUARIO_NOMBRE, value);
        mPrefsEditor.commit();
    }

    public String getUsuarioNombre(){
        return mSharedPrefs.getString(PREFERENCE_USUARIO_NOMBRE, "");
    }
}
