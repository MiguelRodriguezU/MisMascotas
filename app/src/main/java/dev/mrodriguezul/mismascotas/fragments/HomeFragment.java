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
import dev.mrodriguezul.mismascotas.Adapter.TimelineAdapter;
import dev.mrodriguezul.mismascotas.beans.Follower;
import dev.mrodriguezul.mismascotas.beans.Mascota;
import dev.mrodriguezul.mismascotas.R;
import dev.mrodriguezul.mismascotas.beans.MediaIns;
import dev.mrodriguezul.mismascotas.db.LocalData;
import dev.mrodriguezul.mismascotas.presentador.HomeFragmentPresenter;
import dev.mrodriguezul.mismascotas.presentador.IHomeFragmentPresenter;

public class HomeFragment  extends Fragment implements IHomeFragment{
    private RecyclerView rvMascotas;
    private IHomeFragmentPresenter presenter;
    private TimelineAdapter adaptadorTimeline = null;
    private ArrayList<MediaIns> mediasFollowers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);
        rvMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);

        presenter = new HomeFragmentPresenter(this,getActivity());

        //con esta funcion se invoca para obtener de la base de datos
        //presenter.obtenerMascotas();

        //para invocar desde el api
        //presenter.obtenerFollowers();

        /*
        * Por tema de pruebas y por motivos que solamente puedes tener acceso a los followers que tienen perfil publico
        * lastimosamente solo tengo un follower que aceptó probar mi app, y este usuario tiene perfil privado
        * y siendo así no podré ver sus feeds media, entonces partiendo de ello no se mostraría nada en el timeline
        * por eso, tomé mi usuario: mrodriguezul, asumiendo que es un follower que se obtiene de la función anterior (presenter.obtenerFollowers()) y que
        * exploraré sus feed media para mostrarlo en el timeline
        * Así partiré construyendo un follower con mi usuario
        * */
        //para probar la descarga de Medias de los follower
        ArrayList<Follower> followers = new ArrayList<>();
        followers.add(new Follower("6343508898","mrodriguezul","Miguel Rodríguez","url"));
        presenter.obtenerMediasFollowers(followers);

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

    @Override
    public TimelineAdapter crearAdaptadorTimeline(ArrayList<MediaIns> medias) {
        if(adaptadorTimeline == null){
            mediasFollowers = medias;
            adaptadorTimeline = new TimelineAdapter(this.getActivity(),medias);
        }
        return adaptadorTimeline;
    }

    @Override
    public void inicializarAdaptadorTimeline(TimelineAdapter adaptador) {
        rvMascotas.setAdapter(adaptador);
    }

    @Override
    public boolean verificaAdaptador() {
        if(adaptadorTimeline != null){
            return true;
        }
        return false;
    }

    @Override
    public void añadirMedias(ArrayList<MediaIns> medias) {
        int sizeInicial = medias.size();
        for(int i=0; i<sizeInicial;i++){
            this.mediasFollowers.add(sizeInicial + i, medias.get(i));
        }
        adaptadorTimeline.notifyDataSetChanged();
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
