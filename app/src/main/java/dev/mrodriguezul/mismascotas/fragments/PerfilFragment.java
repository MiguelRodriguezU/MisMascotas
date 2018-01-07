package dev.mrodriguezul.mismascotas.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import dev.mrodriguezul.mismascotas.Adapter.FotoAdapter;
import dev.mrodriguezul.mismascotas.Adapter.MediaAdapter;
import dev.mrodriguezul.mismascotas.R;
import dev.mrodriguezul.mismascotas.beans.FotoItem;
import dev.mrodriguezul.mismascotas.beans.MediaIns;
import dev.mrodriguezul.mismascotas.db.Preferences;
import dev.mrodriguezul.mismascotas.presentador.IPerfilFragmentPresenter;
import dev.mrodriguezul.mismascotas.presentador.PerfilFragmentPresenter;


public class PerfilFragment extends Fragment implements IPerfilFragment{
    private RecyclerView rvFotos;
    private IPerfilFragmentPresenter presenter;
    private TextView tvUsername;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);
        rvFotos = (RecyclerView) v.findViewById(R.id.rvFotos);
        tvUsername = (TextView) v.findViewById(R.id.tv_perfil_usuario);

        presenter = new PerfilFragmentPresenter(this,getContext());

        String usernameApp = Preferences.getInstancia(getContext()).getUsuarioNombre();


        usernameApp = "mrodriguezul";
        Log.i("test-mascotas","Usuario Instagram: "+usernameApp);

        tvUsername.setText(usernameApp);
        presenter.obtenerUsuariosInstagramBySearch(usernameApp);

        return v;
    }

    /*
    private void inicializarLista(){
        FotoAdapter adaptador = new FotoAdapter(this.getActivity(),obtenerFotosMascotas());
        rvFotos.setAdapter(adaptador);
    }*/

    /*private ArrayList<FotoItem> obtenerFotosMascotas(){
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
    }*/


    @Override
    public void generarGridLayout() {
        GridLayoutManager glm = new GridLayoutManager(getContext(),3);
        rvFotos.setLayoutManager(glm);
    }

    @Override
    public FotoAdapter generarAdaptadorFotos(ArrayList<FotoItem> fotos) {
        FotoAdapter adaptador = new FotoAdapter(this.getActivity(), fotos);
        return adaptador;
    }

    @Override
    public void inicializarAdaptador(FotoAdapter adaptador) {
        rvFotos.setAdapter(adaptador);
    }

    @Override
    public MediaAdapter generarAdaptadorMedias(ArrayList<MediaIns> medias) {
        MediaAdapter adaptador = new MediaAdapter(this.getActivity(), medias);
        return adaptador;
    }

    @Override
    public void inicializarAdaptador(MediaAdapter adaptador) {
        rvFotos.setAdapter(adaptador);
    }


}
