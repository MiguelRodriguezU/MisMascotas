package dev.mrodriguezul.mismascotas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import java.util.ArrayList;

public class Favoritos extends AppCompatActivity {

    private RecyclerView rvMascotasFavoritas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        Toolbar myActionBar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(myActionBar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //getSupportActionBar().
        //Hello World desde la nueva branch

        rvMascotasFavoritas = (RecyclerView) findViewById(R.id.rvMascotasFavoritas);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        rvMascotasFavoritas.setLayoutManager(llm);

        inicializarLista();
    }

    private void inicializarLista(){
        MascotaAdapter adaptador = new MascotaAdapter(this,obtenerMascotasFavoritas());
        rvMascotasFavoritas.setAdapter(adaptador);
    }

    private ArrayList<MascotaItem> obtenerMascotasFavoritas(){
        ArrayList<MascotaItem> mascotas = new ArrayList<MascotaItem>();
        mascotas.add(new MascotaItem("Mascota 1",R.drawable.ic_pet_1,4));
        mascotas.add(new MascotaItem("Mascota 3",R.drawable.ic_pet_3,10));
        mascotas.add(new MascotaItem("Mascota 5",R.drawable.ic_pet_5,5));
        mascotas.add(new MascotaItem("Mascota 8",R.drawable.ic_pet_8,10));
        mascotas.add(new MascotaItem("Mascota 10",R.drawable.ic_pet_10,1));

        return mascotas;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_favoritos, menu);
        return true;
    }
}
