package dev.mrodriguezul.mismascotas.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import dev.mrodriguezul.mismascotas.beans.Mascota;

/**
 * Created by MIGUEL on 26/11/2017.
 */

public class LocalData {
    public static LocalData instancia = null;
    private Context mContext;
    private BaseDatos basedatos;
    private SQLiteDatabase db;

    public static LocalData getInstance(Context context){
        if(instancia==null){
            instancia = new LocalData(context);
        }
        return instancia;
    }

    public LocalData(Context contexto){
        mContext=contexto;
        init();
    }

    public void init(){
        basedatos = new BaseDatos(mContext);
    }

    public void insertarMascota(ContentValues contentValues){
        db=basedatos.getWritableDatabase();
        if(db!=null){
            try {
                db.insert(ConstantesBaseDatos.TABLE_MASCOTA,null,contentValues);
                db.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void insertarRaitingMascota(ContentValues contentValues){
        db=basedatos.getWritableDatabase();
        if(db!=null){
            try {
                db.insert(ConstantesBaseDatos.TABLE_RAITING,null,contentValues);
                db.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public int obtenerRaitingMascota(Mascota mascota){
        int raiting = 0;
        String query = " SELECT COUNT ("+ConstantesBaseDatos.TABLE_RAITING_NRO_RAITING+") " +
                       " FROM "+ConstantesBaseDatos.TABLE_RAITING +
                       " WHERE "+ConstantesBaseDatos.TABLE_RAITING_ID_MASCOTA + " = " + mascota.getId();

        db = basedatos.getReadableDatabase();
        if(db != null){
            Cursor registros = db.rawQuery(query,null);
            while(registros.moveToNext()){
                raiting = registros.getInt(0);
            }
            db.close();
        }
        return raiting;
    }

    public ArrayList<Mascota> obtenerMascotas(){
        ArrayList<Mascota> mascotas = new ArrayList<Mascota>();

        String query = "SELECT * FROM "+ConstantesBaseDatos.TABLE_MASCOTA;
        db = basedatos.getReadableDatabase();

        if(db != null){
            Cursor registros = db.rawQuery(query,null);
            while(registros.moveToNext()){
                Mascota mascota = new Mascota();

                mascota.setId(registros.getInt(0));
                mascota.setNombre(registros.getString(1));
                mascota.setFoto(registros.getInt(2));

                String queryRaiting = " SELECT COUNT( "+ConstantesBaseDatos.TABLE_RAITING_NRO_RAITING+") as raiting " +
                        " FROM "+ConstantesBaseDatos.TABLE_RAITING+
                        " WHERE "+ConstantesBaseDatos.TABLE_RAITING_ID_MASCOTA +" = "+ mascota.getId();

                Cursor registrosRaiting = db.rawQuery(queryRaiting,null);
                if(registrosRaiting.moveToNext()){
                    mascota.setRaiting(registrosRaiting.getInt(0));
                }else{
                    mascota.setRaiting(0);
                }
                mascotas.add(mascota);
            }
            db.close();
        }

        return mascotas;
    }

    public ArrayList<Mascota> obtenerMascotasRankeadas(){
        ArrayList<Mascota> mascotas = new ArrayList<Mascota>();

        String query = " SELECT m.* FROM "+ConstantesBaseDatos.TABLE_MASCOTA+" m "+
                       " JOIN ( SELECT " + ConstantesBaseDatos.TABLE_RAITING_ID_MASCOTA+ ", count(*) as c "+
                       "        FROM "+ConstantesBaseDatos.TABLE_RAITING +
                       "        GROUP BY  "+ConstantesBaseDatos.TABLE_RAITING_ID_MASCOTA+
                       "        HAVING COUNT(*) > 0 "+
                       "        ORDER BY c DESC "+
                       "        LIMIT 5 ) r"+
                       " ON m."+ConstantesBaseDatos.TABLE_MASCOTA_ID+" = r."+ConstantesBaseDatos.TABLE_RAITING_ID_MASCOTA;

        db = basedatos.getReadableDatabase();

        if(db != null){
            Cursor registros = db.rawQuery(query,null);
            while(registros.moveToNext()){
                Mascota mascota = new Mascota();

                mascota.setId(registros.getInt(0));
                mascota.setNombre(registros.getString(1));
                mascota.setFoto(registros.getInt(2));

                String queryRaiting = " SELECT COUNT( "+ConstantesBaseDatos.TABLE_RAITING_NRO_RAITING+") as raiting " +
                                      " FROM "+ConstantesBaseDatos.TABLE_RAITING+
                                      " WHERE "+ConstantesBaseDatos.TABLE_RAITING_ID_MASCOTA +" = "+ mascota.getId();

                Cursor registrosRaiting = db.rawQuery(queryRaiting,null);
                if(registrosRaiting.moveToNext()){
                    mascota.setRaiting(registrosRaiting.getInt(0));
                }else{
                    mascota.setRaiting(0);
                }
                mascotas.add(mascota);
            }
            db.close();
        }

        return mascotas;
    }


}
