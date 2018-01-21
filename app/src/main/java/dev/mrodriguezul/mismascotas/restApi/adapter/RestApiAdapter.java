package dev.mrodriguezul.mismascotas.restApi.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import dev.mrodriguezul.mismascotas.restApi.ConstantesRestApi;
import dev.mrodriguezul.mismascotas.restApi.EndPointsApi;
import dev.mrodriguezul.mismascotas.restApi.deserializador.FollowerDeserializador;
import dev.mrodriguezul.mismascotas.restApi.deserializador.MediaInsDeserializador;
import dev.mrodriguezul.mismascotas.restApi.deserializador.UsuarioInsDeserializador;
import dev.mrodriguezul.mismascotas.restApi.model.FollowerResponse;
import dev.mrodriguezul.mismascotas.restApi.model.MediaInsResponse;
import dev.mrodriguezul.mismascotas.restApi.model.UsuarioInsResponse;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiAdapter {
    private static RestApiAdapter instancia = null;
    public static RestApiAdapter getInstancia(){
        if(instancia == null){
            instancia = new RestApiAdapter();
        }
        return instancia;
    }

    public RestApiAdapter() {
    }

    public EndPointsApi establecerConexionRestApiInstagram(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(EndPointsApi.class);
    }

    public EndPointsApi establecerConexionRestApiInstagram(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .client(getClientOkHttp())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(EndPointsApi.class);
    }


    public EndPointsApi establecerConexionRestApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL_NOTIFICACION)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(EndPointsApi.class);
    }

    private OkHttpClient getClientOkHttp(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();

        return okHttpClient;
    }

    public Gson construyeGSONDeserializadoUsuariosBySearch(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(UsuarioInsResponse.class, new UsuarioInsDeserializador());
        return gsonBuilder.create();
    }

    public Gson construyeGSONDeserializadoMediaUser(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MediaInsResponse.class, new MediaInsDeserializador());
        return gsonBuilder.create();
    }

    public Gson construyeGSONDeserializadoFollowers(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(FollowerResponse.class, new FollowerDeserializador());
        return gsonBuilder.create();
    }
}
