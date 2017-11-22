package dev.mrodriguezul.mismascotas.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import dev.mrodriguezul.mismascotas.Adapter.MascotaAdapter;
import dev.mrodriguezul.mismascotas.beans.MascotaItem;
import dev.mrodriguezul.mismascotas.R;

public class HomeFragment extends Fragment {
    private RecyclerView rvMascotas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);
        rvMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);

        LinearLayoutManager llm = new LinearLayoutManager(this.getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        rvMascotas.setLayoutManager(llm);

        inicializarLista();

        return v;
    }

    private void inicializarLista(){
        MascotaAdapter adaptador = new MascotaAdapter(this.getActivity(),obtenerMascotas());
        rvMascotas.setAdapter(adaptador);
    }

    private ArrayList<MascotaItem> obtenerMascotas(){
        ArrayList<MascotaItem> mascotas = new ArrayList<MascotaItem>();
        mascotas.add(new MascotaItem("Mascota 1",R.drawable.ic_pet_1,4));
        mascotas.add(new MascotaItem("Mascota 2",R.drawable.ic_pet_2,10));
        mascotas.add(new MascotaItem("Mascota 3",R.drawable.ic_pet_3,10));
        mascotas.add(new MascotaItem("Mascota 4",R.drawable.ic_pet_4,3));
        mascotas.add(new MascotaItem("Mascota 5",R.drawable.ic_pet_5,5));
        mascotas.add(new MascotaItem("Mascota 6",R.drawable.ic_pet_6,10));
        mascotas.add(new MascotaItem("Mascota 7",R.drawable.ic_pet_7,30));
        mascotas.add(new MascotaItem("Mascota 8",R.drawable.ic_pet_8,10));
        mascotas.add(new MascotaItem("Mascota 9",R.drawable.ic_pet_9,0));
        mascotas.add(new MascotaItem("Mascota 10",R.drawable.ic_pet_10,1));

        return mascotas;
    }

}
