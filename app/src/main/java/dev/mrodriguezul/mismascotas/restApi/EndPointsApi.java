package dev.mrodriguezul.mismascotas.restApi;

import dev.mrodriguezul.mismascotas.restApi.model.MediaInsResponse;
import dev.mrodriguezul.mismascotas.restApi.model.UsuarioInsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EndPointsApi {

    @GET(ConstantesRestApi.URL_GET_SEARCH)
    Call<UsuarioInsResponse> getUsersInsBySearch(@Query("q") String queryStrig);

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)
    Call<MediaInsResponse> getRecentMediaUser(@Path("id") String userId);
}
