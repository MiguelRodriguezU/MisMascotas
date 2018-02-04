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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

import dev.mrodriguezul.mismascotas.Adapter.PageAdapter;
import dev.mrodriguezul.mismascotas.db.Preferences;
import dev.mrodriguezul.mismascotas.fragments.HomeFragment;
import dev.mrodriguezul.mismascotas.fragments.PerfilFragment;
import dev.mrodriguezul.mismascotas.restApi.EndPointsApi;
import dev.mrodriguezul.mismascotas.restApi.adapter.RestApiAdapter;
import dev.mrodriguezul.mismascotas.restApi.model.CredencialResponse;
import dev.mrodriguezul.mismascotas.service.ActionKeys;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "main-test-mascotas";

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

        if(getIntent() != null) {
            String action = getIntent().getAction();
            initPage(action);
        }
    }

    public void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_profile);

    }

    private void  initPage(String idPage){
        if(idPage.equals(ActionKeys.ACTION_VIEW_PROFILE)){
            viewPager.setCurrentItem(1);
        }
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

        if(id == R.id.menu_recibir_notificacion){
            registrarUsuario();
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

    private void registrarUsuario(){
        String usernameAppInstagram = Preferences.getInstancia(this).getUsuarioNombre();
        if(!usernameAppInstagram.equals("")){

            String token = FirebaseInstanceId.getInstance().getToken();
            Log.i("test-mascotas","Token Firebase: "+token);

            enviarusuario(token, usernameAppInstagram);

        }else{
            Toast.makeText(this,getResources().getString(R.string.msg_recibir_notificaciones),Toast.LENGTH_LONG).show();
        }
    }

    private void enviarusuario(String token, String usuarioInstagram){

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndPointsApi endPoints = restApiAdapter.establecerConexionRestApi();
        Call<CredencialResponse> credencialResponseCall = endPoints.registrarTokenId(token,usuarioInstagram);

        credencialResponseCall.enqueue(new Callback<CredencialResponse>() {
            @Override
            public void onResponse(Call<CredencialResponse> call, Response<CredencialResponse> response) {
                CredencialResponse credencialResponse = response.body();

                if(credencialResponse != null){
                    Toast.makeText(MainActivity.this,MainActivity.this.getResources().getString(R.string.msg_registrar_usuario),Toast.LENGTH_LONG).show();
                    Log.i("test-mascotas","Respuesta de Firebase:  "+credencialResponse.getId());
                    Log.i("test-mascotas","Respuesta de Firebase:  "+credencialResponse.getToken());
                }else{
                    Log.i("test-mascotas","El servicio se encuentra inactivo");
                }
            }

            @Override
            public void onFailure(Call<CredencialResponse> call, Throwable t) {

            }
        });
    }
}
