package dev.mrodriguezul.mismascotas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvMascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        rvMascotas = (RecyclerView) findViewById(R.id.rvMascotas);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        rvMascotas.setLayoutManager(llm);

        inicializarLista();
    }

    private void inicializarLista(){
        MascotaAdapter adaptador = new MascotaAdapter(this,obtenerMascotas());
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if(id == R.id.menu_favoritos){
            verFavoritos();
        }

        if(id == R.id.menu_contacto){
            verContacto();
        }

        if(id == R.id.menu_acerca_de){
            verAcercaDe();
        }

        return super.onOptionsItemSelected(item);
    }

    private void verFavoritos(){
        Intent intent = new Intent(this,Favoritos.class);
        startActivity(intent);
    }

    private void verContacto(){
        Intent intent = new Intent(this,Contacto.class);
        startActivity(intent);
    }

    private void verAcercaDe(){
        Intent intent = new Intent(this,AcercaDe.class);
        startActivity(intent);
    }
}
