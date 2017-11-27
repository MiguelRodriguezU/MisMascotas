package dev.mrodriguezul.mismascotas.db;

/**
 * Created by MIGUEL on 26/11/2017.
 */

public class ConstantesBaseDatos {
    public static final String DATABASE_NAME = "mascotas";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_MASCOTA             = "contacto";
    public static final String TABLE_MASCOTA_ID          = "id";
    public static final String TABLE_MASCOTA_NOMBRE      = "nombre";
    public static final String TABLE_MASCOTA_FOTO        = "foto";

    public static final String TABLE_RAITING             = "raiting";
    public static final String TABLE_RAITING_ID          = "id";
    public static final String TABLE_RAITING_ID_MASCOTA  = "id_mascota";
    public static final String TABLE_RAITING_NRO_RAITING = "nro_raiting";
}
