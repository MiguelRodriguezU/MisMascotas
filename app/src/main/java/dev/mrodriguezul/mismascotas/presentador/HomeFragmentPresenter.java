package dev.mrodriguezul.mismascotas.presentador;

import android.content.Context;

import java.util.ArrayList;

import dev.mrodriguezul.mismascotas.R;
import dev.mrodriguezul.mismascotas.beans.Mascota;
import dev.mrodriguezul.mismascotas.db.ConstructorMascotas;
import dev.mrodriguezul.mismascotas.fragments.IHomeFragment;

/**
 * Created by MIGUEL on 26/11/2017.
 */

public class HomeFragmentPresenter implements IHomeFragmentPresenter{
    private IHomeFragment iHomeFragment;
    private Context contexto;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public HomeFragmentPresenter(IHomeFragment iHomeFragment, Context contex) {
        this.iHomeFragment = iHomeFragment;
        this.contexto = contex;
        this.constructorMascotas = new ConstructorMascotas(contex);

        //insertarMascotas();//Solamente para cargar los datos
    }

    @Override
    public void obtenerMascotas() {
        mascotas = constructorMascotas.obtenerMascotas();
        mostrarMascotas();
    }

    @Override
    public void mostrarMascotas() {
        iHomeFragment.inicializarAdaptador(iHomeFragment.crearAdaptador(mascotas));
        iHomeFragment.generarLinearLayoutVertical();
    }

    public void insertarMascotas(){
        constructorMascotas.insertarMascota(new Mascota("Mascota 1", R.drawable.ic_pet_1,0));
        constructorMascotas.insertarMascota(new Mascota("Mascota 2",R.drawable.ic_pet_2,10));
        constructorMascotas.insertarMascota(new Mascota("Mascota 3",R.drawable.ic_pet_3,10));
        constructorMascotas.insertarMascota(new Mascota("Mascota 4",R.drawable.ic_pet_4,3));
        constructorMascotas.insertarMascota(new Mascota("Mascota 5",R.drawable.ic_pet_5,5));
        constructorMascotas.insertarMascota(new Mascota("Mascota 6",R.drawable.ic_pet_6,10));
        constructorMascotas.insertarMascota(new Mascota("Mascota 7",R.drawable.ic_pet_7,30));
        constructorMascotas.insertarMascota(new Mascota("Mascota 8",R.drawable.ic_pet_8,10));
        constructorMascotas.insertarMascota(new Mascota("Mascota 9",R.drawable.ic_pet_9,0));
        constructorMascotas.insertarMascota(new Mascota("Mascota 10",R.drawable.ic_pet_10,1));
    }
}
