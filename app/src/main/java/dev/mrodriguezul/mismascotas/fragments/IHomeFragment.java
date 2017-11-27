package dev.mrodriguezul.mismascotas.fragments;

import java.util.ArrayList;

import dev.mrodriguezul.mismascotas.Adapter.MascotaAdapter;
import dev.mrodriguezul.mismascotas.beans.Mascota;

/**
 * Created by MIGUEL on 26/11/2017.
 */

public interface IHomeFragment {
    public void generarLinearLayoutVertical();
    public MascotaAdapter crearAdaptador(ArrayList<Mascota> mascotas);
    public void inicializarAdaptador(MascotaAdapter adaptador);
}
