package dev.mrodriguezul.mismascotas.restApi;

import dev.mrodriguezul.mismascotas.restApi.model.CredencialResponse;
import dev.mrodriguezul.mismascotas.restApi.model.FollowerResponse;
import dev.mrodriguezul.mismascotas.restApi.model.LikeResponse;
import dev.mrodriguezul.mismascotas.restApi.model.MediaInsResponse;
import dev.mrodriguezul.mismascotas.restApi.model.UsuarioInsResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EndPointsApi {

    @GET(ConstantesRestApi.URL_GET_SEARCH)
    Call<UsuarioInsResponse> getUsersInsBySearch(@Query("q") String queryStrig);

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)
    Call<MediaInsResponse> getRecentMediaUser(@Path("id") String userId);

    @GET(ConstantesRestApi.URL_GET_FOLLOWERS)
    Call<FollowerResponse> getFollowers();

    @POST(ConstantesRestApi.URL_SET_LIKE_MEDIA)
    Call<MediaInsResponse> setLikeMedia(@Path("media-id") String mediaId);

    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_USUARIO)
    Call<CredencialResponse> registrarTokenId(@Field("id_dispositivo") String id_dispositivo, @Field("id_usuario_instagram") String id_usuario_instagram);

    @GET(ConstantesRestApi.KEY_GET_LIKE)
    Call<LikeResponse> registrarLike(@Path("id_foto_instagram") String id_foto_instagram, @Path("id_usuario_instagram") String id_usuario_instagram, @Path("id_dispositivo") String id_dispositivo);
}
