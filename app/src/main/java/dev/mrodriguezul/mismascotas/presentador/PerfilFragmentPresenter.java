package dev.mrodriguezul.mismascotas.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import dev.mrodriguezul.mismascotas.R;
import dev.mrodriguezul.mismascotas.beans.FotoItem;
import dev.mrodriguezul.mismascotas.beans.MediaIns;
import dev.mrodriguezul.mismascotas.beans.UsuarioIns;
import dev.mrodriguezul.mismascotas.db.Preferences;
import dev.mrodriguezul.mismascotas.fragments.IPerfilFragment;
import dev.mrodriguezul.mismascotas.restApi.EndPointsApi;
import dev.mrodriguezul.mismascotas.restApi.adapter.RestApiAdapter;
import dev.mrodriguezul.mismascotas.restApi.model.MediaInsResponse;
import dev.mrodriguezul.mismascotas.restApi.model.UsuarioInsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilFragmentPresenter implements IPerfilFragmentPresenter{
    private IPerfilFragment iPerfilFragment;
    private Context contexto;
    private ArrayList<FotoItem> fotos;
    private ArrayList<MediaIns> medias;

    public PerfilFragmentPresenter(IPerfilFragment iPerfilFragment, Context contexto) {
        this.iPerfilFragment = iPerfilFragment;
        this.contexto = contexto;
    }

    @Override
    public void mostrarFotos(){
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

        iPerfilFragment.generarGridLayout();
        iPerfilFragment.inicializarAdaptador(iPerfilFragment.generarAdaptadorFotos(fotos));


        //String usuarioInstagra = Preferences.getInstancia(this.contexto).getUsuarioNombre();
        /*usernameApp = "mrodriguezul";
        Log.i("test-mascotas","Usuario Instagram: "+usernameApp);
        obtenerUsuariosInstagramBySearch(usernameApp);*/

    }

    @Override
    public void obtenerUsuariosInstagramBySearch(final String username) {
        RestApiAdapter restApiAdapter = RestApiAdapter.getInstancia();
        Gson gsonUsuariosInsBySearch = restApiAdapter.construyeGSONDeserializadoUsuariosBySearch();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonUsuariosInsBySearch);
        Call<UsuarioInsResponse> usuarioInsResponseCall = endPointsApi.getUsersInsBySearch(username);

        usuarioInsResponseCall.enqueue(new Callback<UsuarioInsResponse>() {
            @Override
            public void onResponse(Call<UsuarioInsResponse> call, Response<UsuarioInsResponse> response) {
                UsuarioInsResponse usuarioInsResponse = response.body();

                if(usuarioInsResponse != null){

                    ArrayList<UsuarioIns> usuariosIns = usuarioInsResponse.getUsuariosInstagram();
                    UsuarioIns usuarioApp = null;
                    for(int i=0;i<usuariosIns.size();i++){

                        usuarioApp = usuariosIns.get(i);
                        if(usuarioApp.getUsername().equals(username)){
                            Log.i("test-mascotas","Este es el usuario: "+username+" que debo analizar!: ");
                            break;
                        }
                    }
                    if(usuarioApp != null){
                        obtenerMediaUsuario(usuarioApp.getId());
                    }
                }
            }

            @Override
            public void onFailure(Call<UsuarioInsResponse> call, Throwable t) {
                Toast.makeText(contexto,"Falló conexión, intenta nuevamente",Toast.LENGTH_LONG).show();
                Log.e("test-mascotas", "Error de conexión :"+t.getMessage());
            }
        });
        Log.i("test-mascotas","Fin de llamada al api! ");
    }

    @Override
    public void obtenerMediaUsuario(String userId) {
        RestApiAdapter restApiAdapter = RestApiAdapter.getInstancia();
        Gson gsonMediasFromUser = restApiAdapter.construyeGSONDeserializadoMediaUser();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediasFromUser);
        Call<MediaInsResponse> mediaInsResponseCall = endPointsApi.getRecentMediaUser(userId);

        Log.i("test-mascotas","Llamada al api - Medias!!");
        mediaInsResponseCall.enqueue(new Callback<MediaInsResponse>() {
            @Override
            public void onResponse(Call<MediaInsResponse> call, Response<MediaInsResponse> response) {
                MediaInsResponse mediaInsResponse = response.body();
                if(mediaInsResponse != null){
                    medias = mediaInsResponse.getMediasInstagram();
                    for(int i=0;i<medias.size();i++){
                        MediaIns media = medias.get(i);
                        Log.i("test-mascotas","Medias: "+media.getUrl()+" - "+media.getLikes());
                    }
                    mostrarResultado();
                }
            }

            @Override
            public void onFailure(Call<MediaInsResponse> call, Throwable t) {
                Toast.makeText(contexto,"Falló conexión, intenta nuevamente",Toast.LENGTH_LONG).show();
                Log.e("test-mascotas", "Error al obtener medias: "+t.getMessage());
            }

        });
    }

    @Override
    public void mostrarResultado() {
        iPerfilFragment.inicializarAdaptador(iPerfilFragment.generarAdaptadorMedias(medias));
        iPerfilFragment.generarGridLayout();
    }
}
