package dev.mrodriguezul.mismascotas.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import dev.mrodriguezul.mismascotas.Adapter.FotoAdapter;
import dev.mrodriguezul.mismascotas.R;
import dev.mrodriguezul.mismascotas.beans.FotoItem;


public class PerfilFragment extends Fragment {
    private RecyclerView rvFotos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        rvFotos = (RecyclerView) v.findViewById(R.id.rvFotos);

        GridLayoutManager glm = new GridLayoutManager(this.getActivity(),3);
        rvFotos.setLayoutManager(glm);

        inicializarLista();
        return v;
    }

    private void inicializarLista(){
        FotoAdapter adaptador = new FotoAdapter(this.getActivity(),obtenerFotosMascotas());
        rvFotos.setAdapter(adaptador);
    }

    private ArrayList<FotoItem> obtenerFotosMascotas(){
        ArrayList<FotoItem> fotos = new ArrayList<FotoItem>();
        fotos.add(new FotoItem(R.drawable.ic_pet_1,4));
        fotos.add(new FotoItem(R.drawable.ic_pet_2,10));
        fotos.add(new FotoItem(R.drawable.ic_pet_3,10));
        fotos.add(new FotoItem(R.drawable.ic_pet_4,3));
        fotos.add(new FotoItem(R.drawable.ic_pet_5,5));
        fotos.add(new FotoItem(R.drawable.ic_pet_6,10));
        fotos.add(new FotoItem(R.drawable.ic_pet_7,30));
        fotos.add(new FotoItem(R.drawable.ic_pet_8,10));
        fotos.add(new FotoItem(R.drawable.ic_pet_9,0));
        fotos.add(new FotoItem(R.drawable.ic_pet_10,1));

        return fotos;
    }


}
