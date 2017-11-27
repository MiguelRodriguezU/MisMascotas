package dev.mrodriguezul.mismascotas.db;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;

import dev.mrodriguezul.mismascotas.beans.Mascota;

/**
 * Created by MIGUEL on 27/11/2017.
 */

public class ConstructorMascotas {
    private Context contexto;
    public static final int RAITING = 1;

    public ConstructorMascotas(Context contexto) {
        this.contexto = contexto;
    }

    public ArrayList<Mascota> obtenerMascotas(){
        LocalData db = LocalData.getInstance(this.contexto);
        //insertar3Contactos(db);
        return db.obtenerMascotas();
    }

    public void insertarMascota(Mascota mascota){
        LocalData db = LocalData.getInstance(this.contexto);

        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE,mascota.getNombre());
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO,mascota.getFoto());

        db.insertarMascota(contentValues);
    }

    public void raitearMascota(Mascota mascota){
        LocalData db = LocalData.getInstance(this.contexto);
        ContentValues contentValues = new ContentValues();

        contentValues.put(ConstantesBaseDatos.TABLE_RAITING_ID_MASCOTA,mascota.getId());
        contentValues.put(ConstantesBaseDatos.TABLE_RAITING_NRO_RAITING,RAITING);

        db.insertarRaitingMascota(contentValues);
    }

    public int obtenerRaitingMascota(Mascota mascota){
        LocalData db = LocalData.getInstance(this.contexto);
        return db.obtenerRaitingMascota(mascota);
    }
}
