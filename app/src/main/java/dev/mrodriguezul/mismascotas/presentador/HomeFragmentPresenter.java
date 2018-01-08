package dev.mrodriguezul.mismascotas.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import dev.mrodriguezul.mismascotas.R;
import dev.mrodriguezul.mismascotas.beans.Follower;
import dev.mrodriguezul.mismascotas.beans.Mascota;
import dev.mrodriguezul.mismascotas.beans.MediaIns;
import dev.mrodriguezul.mismascotas.db.ConstructorMascotas;
import dev.mrodriguezul.mismascotas.fragments.IHomeFragment;
import dev.mrodriguezul.mismascotas.restApi.EndPointsApi;
import dev.mrodriguezul.mismascotas.restApi.adapter.RestApiAdapter;
import dev.mrodriguezul.mismascotas.restApi.model.FollowerResponse;
import dev.mrodriguezul.mismascotas.restApi.model.MediaInsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragmentPresenter implements IHomeFragmentPresenter{
    private IHomeFragment iHomeFragment;
    private Context contexto;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public HomeFragmentPresenter(IHomeFragment iHomeFragment, Context contex) {
        this.iHomeFragment = iHomeFragment;
        this.contexto = contex;
        this.constructorMascotas = new ConstructorMascotas(contex);
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

    @Override
    public void obtenerFollowers() {
        RestApiAdapter restApiAdapter = RestApiAdapter.getInstancia();
        Gson gsonFollowers = restApiAdapter.construyeGSONDeserializadoFollowers();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonFollowers);
        Call<FollowerResponse> followerResponseCall = endPointsApi.getFollowers();

        Log.i("test-mascotas","Llamada al api de followers!!");

        followerResponseCall.enqueue(new Callback<FollowerResponse>() {
            @Override
            public void onResponse(Call<FollowerResponse> call, Response<FollowerResponse> response) {
                FollowerResponse followerResponse = response.body();
                ArrayList<Follower> followers = followerResponse.getFollowers();

                //una vez obtenido los followers, vamos a obtener los feeds media de cada uno
                obtenerMediasFollowers(followers);
            }

            @Override
            public void onFailure(Call<FollowerResponse> call, Throwable t) {
                Toast.makeText(contexto,"Falló conexión, intenta nuevamente",Toast.LENGTH_LONG).show();
                Log.e("test-mascotas", "Error de conexión followers:"+t.getMessage());
            }
        });
        Log.i("test-mascotas","Fin de llamada al api desde followers! ");
    }

    @Override
    public void obtenerMediasFollowers(ArrayList<Follower> followers) {
        RestApiAdapter restApiAdapter = RestApiAdapter.getInstancia();
        Gson gsonMediasFromUser = restApiAdapter.construyeGSONDeserializadoMediaUser();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediasFromUser);
        Call<MediaInsResponse> mediaInsResponseCall;

        Follower follower = null;
        for(int i=0;i<followers.size();i++){
            follower = followers.get(i);
            Log.i("test-mascotas","Este follower - ");

            Log.i("test-mascotas","obteniendo medias de :"+follower.getUsername()+" - "+follower.getId());
            mediaInsResponseCall = endPointsApi.getRecentMediaUser(follower.getId());
            mediaInsResponseCall.enqueue(new Callback<MediaInsResponse>() {
                @Override
                public void onResponse(Call<MediaInsResponse> call, Response<MediaInsResponse> response) {
                    MediaInsResponse mediaInsResponse = response.body();
                    ArrayList<MediaIns> mediasFollowers = mediaInsResponse.getMediasInstagram();
                    mostrarTimeLine(mediasFollowers);
                }

                @Override
                public void onFailure(Call<MediaInsResponse> call, Throwable t) {
                    Log.e("test-mascotas", "Error al obtener medias de followers en el Home - "+t.getMessage());
                }
            });
        }
    }

    @Override
    public void mostrarTimeLine(ArrayList<MediaIns> mediasFollowers) {
        if(iHomeFragment.verificaAdaptador() == false){//si aún no se crea el adaptador
            iHomeFragment.inicializarAdaptadorTimeline(iHomeFragment.crearAdaptadorTimeline(mediasFollowers));
            iHomeFragment.generarLinearLayoutVertical();
        }else{//si ya se creó el adaptador, solamente añadir los nuevos feeds
            iHomeFragment.añadirMedias(mediasFollowers);
        }
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
