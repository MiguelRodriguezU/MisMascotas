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
import dev.mrodriguezul.mismascotas.beans.Mascota;
import dev.mrodriguezul.mismascotas.R;
import dev.mrodriguezul.mismascotas.db.LocalData;
import dev.mrodriguezul.mismascotas.presentador.HomeFragmentPresenter;
import dev.mrodriguezul.mismascotas.presentador.IHomeFragmentPresenter;

public class HomeFragment extends Fragment implements IHomeFragment{
    private RecyclerView rvMascotas;
    private IHomeFragmentPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);
        rvMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);

        presenter = new HomeFragmentPresenter(this,getActivity());

        presenter.obtenerMascotas();

        /*LinearLayoutManager llm = new LinearLayoutManager(this.getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        rvMascotas.setLayoutManager(llm);*/

        //inicializarLista();

        return v;
    }

    /*private void inicializarLista(){
        ArrayList<Mascota> mascotas = LocalData.getInstance(this.getContext()).obtenerMascotas();
        MascotaAdapter adaptador = new MascotaAdapter(this.getActivity(),mascotas);
        rvMascotas.setAdapter(adaptador);
    }*/

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(this.getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        rvMascotas.setLayoutManager(llm);
    }

    @Override
    public MascotaAdapter crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdapter adaptador = new MascotaAdapter(this.getActivity(),mascotas);
        return adaptador;
    }

    @Override
    public void inicializarAdaptador(MascotaAdapter adaptador) {
        rvMascotas.setAdapter(adaptador);
    }

    /*
    private ArrayList<Mascota> obtenerMascotas(){
        ArrayList<Mascota> mascotas = new ArrayList<Mascota>();
        mascotas.add(new Mascota("Mascota 1",R.drawable.ic_pet_1,4));
        mascotas.add(new Mascota("Mascota 2",R.drawable.ic_pet_2,10));
        mascotas.add(new Mascota("Mascota 3",R.drawable.ic_pet_3,10));
        mascotas.add(new Mascota("Mascota 4",R.drawable.ic_pet_4,3));
        mascotas.add(new Mascota("Mascota 5",R.drawable.ic_pet_5,5));
        mascotas.add(new Mascota("Mascota 6",R.drawable.ic_pet_6,10));
        mascotas.add(new Mascota("Mascota 7",R.drawable.ic_pet_7,30));
        mascotas.add(new Mascota("Mascota 8",R.drawable.ic_pet_8,10));
        mascotas.add(new Mascota("Mascota 9",R.drawable.ic_pet_9,0));
        mascotas.add(new Mascota("Mascota 10",R.drawable.ic_pet_10,1));

        return mascotas;
    }*/

}
