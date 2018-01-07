package dev.mrodriguezul.mismascotas.fragments;

import java.util.ArrayList;

import dev.mrodriguezul.mismascotas.Adapter.FotoAdapter;
import dev.mrodriguezul.mismascotas.Adapter.MediaAdapter;
import dev.mrodriguezul.mismascotas.beans.FotoItem;
import dev.mrodriguezul.mismascotas.beans.MediaIns;


public interface IPerfilFragment {
    public void generarGridLayout();
    public FotoAdapter generarAdaptadorFotos(ArrayList<FotoItem> fotos);
    public void inicializarAdaptador(FotoAdapter adaptador);
    public MediaAdapter generarAdaptadorMedias(ArrayList<MediaIns> medias);
    public void inicializarAdaptador(MediaAdapter adaptador);
}
