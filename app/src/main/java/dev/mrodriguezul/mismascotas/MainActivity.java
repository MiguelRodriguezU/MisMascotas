package dev.mrodriguezul.mismascotas;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import dev.mrodriguezul.mismascotas.Adapter.PageAdapter;
import dev.mrodriguezul.mismascotas.fragments.HomeFragment;
import dev.mrodriguezul.mismascotas.fragments.PerfilFragment;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvMascotas;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        viewPager = (ViewPager)findViewById(R.id.viewPager);

        if(toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        setUpViewPager();
    }

    public void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_profile);

    }

    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new PerfilFragment());

        return fragments;
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

        if(id == R.id.menu_configurar_cuenta ){
            verConfigurarCuenta();
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

    private void verConfigurarCuenta(){
        Intent intent = new Intent(this,ConfigurarCuenta.class);
        startActivity(intent);
    }
}
