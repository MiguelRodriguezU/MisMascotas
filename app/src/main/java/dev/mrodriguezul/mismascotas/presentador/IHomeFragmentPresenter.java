package dev.mrodriguezul.mismascotas.presentador;

import java.util.ArrayList;

import dev.mrodriguezul.mismascotas.beans.Follower;
import dev.mrodriguezul.mismascotas.beans.MediaIns;

/**
 * Created by MIGUEL on 26/11/2017.
 */

public interface IHomeFragmentPresenter {
    public void obtenerMascotas();
    public void mostrarMascotas();
    public void obtenerFollowers();
    public void obtenerMediasFollowers(ArrayList<Follower> followers);
    public void mostrarTimeLine(ArrayList<MediaIns> mediasFollowers);
}
