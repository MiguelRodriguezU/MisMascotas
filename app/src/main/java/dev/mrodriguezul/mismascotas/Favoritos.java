package dev.mrodriguezul.mismascotas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import java.util.ArrayList;

import dev.mrodriguezul.mismascotas.Adapter.MascotaAdapter;
import dev.mrodriguezul.mismascotas.beans.Mascota;
import dev.mrodriguezul.mismascotas.db.ConstructorMascotas;

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

    private ArrayList<Mascota> obtenerMascotasFavoritas(){
        ArrayList<Mascota> mascotas = new ConstructorMascotas(this).obtenerMascotasRankeadas();
        return mascotas;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_favoritos, menu);
        return true;
    }
}
