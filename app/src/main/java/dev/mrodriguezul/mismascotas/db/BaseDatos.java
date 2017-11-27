package dev.mrodriguezul.mismascotas.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MIGUEL on 26/11/2017.
 */

public class BaseDatos extends SQLiteOpenHelper {
    private Context context;

    public BaseDatos(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_createTablaMascota = " CREATE TABLE " + ConstantesBaseDatos.TABLE_MASCOTA + " ( " +
                                        ConstantesBaseDatos.TABLE_MASCOTA_ID       + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                                        ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE   + " TEXT, "+
                                        ConstantesBaseDatos.TABLE_MASCOTA_FOTO     + " INTEGER "+
                                        " ) ";

        String sql_createRaitingMascota = " CREATE TABLE " + ConstantesBaseDatos.TABLE_RAITING + " ( " +
                                        ConstantesBaseDatos.TABLE_RAITING_ID              + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                                        ConstantesBaseDatos.TABLE_RAITING_ID_MASCOTA     + " INTEGER, "+
                                        ConstantesBaseDatos.TABLE_RAITING_NRO_RAITING    + " INTEGER, "+
                                        "FOREIGN KEY ( "+ConstantesBaseDatos.TABLE_RAITING_ID_MASCOTA+" ) "+
                                        "REFERENCES "+ConstantesBaseDatos.TABLE_MASCOTA + "("+ConstantesBaseDatos.TABLE_MASCOTA_ID+")"+
                                        " ) ";

        db.execSQL(sql_createTablaMascota);
        db.execSQL(sql_createRaitingMascota);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+ ConstantesBaseDatos.TABLE_RAITING);
        db.execSQL("DROP TABLE IF EXIST "+ ConstantesBaseDatos.TABLE_MASCOTA);

        onCreate(db);
    }
}
