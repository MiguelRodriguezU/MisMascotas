package dev.mrodriguezul.mismascotas.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import dev.mrodriguezul.mismascotas.R;
import dev.mrodriguezul.mismascotas.beans.MediaIns;
import dev.mrodriguezul.mismascotas.restApi.EndPointsApi;
import dev.mrodriguezul.mismascotas.restApi.adapter.RestApiAdapter;
import dev.mrodriguezul.mismascotas.restApi.model.LikeResponse;
import dev.mrodriguezul.mismascotas.restApi.model.MediaInsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MIGUEL on 12/11/2017.
 */

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.TimelineViewHolder>  {
    private ArrayList<MediaIns> medias;
    private Activity actividad;

    public TimelineAdapter(Activity actividad, ArrayList<MediaIns> mendias) {
        this.medias = mendias;
        this.actividad = actividad;
    }

    @Override
    public TimelineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_mascota,parent,false);

        return new TimelineViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final TimelineViewHolder holder, int position) {
        final MediaIns mediaIns = medias.get(position);
        Picasso.with(actividad)
                .load(mediaIns.getUrl())
                .placeholder(R.drawable.ic_pet_1)
                .into(holder.ivMascotaFoto);

        holder.tvMascotaNombre.setText(mediaIns.getNombre());
        holder.tvMascotaRaiting.setText(""+mediaIns.getLikes());

        holder.ibMascotaRaitear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                darLikeInstagram(holder,mediaIns);
                registrarLike(mediaIns);
            }
        });
    }

    private void darLikeInstagram(final TimelineViewHolder holder, final MediaIns media){
        RestApiAdapter restApiAdapter = RestApiAdapter.getInstancia();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionRestApiInstagram();

        Call<MediaInsResponse> mediaInsResponseCall;

        mediaInsResponseCall = endPointsApi.setLikeMedia(media.getId());
        mediaInsResponseCall.enqueue(new Callback<MediaInsResponse>() {
            @Override
            public void onResponse(Call<MediaInsResponse> call, Response<MediaInsResponse> response) {
                MediaInsResponse mediaInsResponse = response.body();
                if(mediaInsResponse != null){
                    Toast.makeText(actividad,"Te gustó la foto de "+media.getNombre(),Toast.LENGTH_SHORT).show();
                    media.setLikes(media.getLikes()+1);
                    holder.tvMascotaRaiting.setText(media.getLikes()+"");
                }
            }

            @Override
            public void onFailure(Call<MediaInsResponse> call, Throwable t) {
                Toast.makeText(actividad, "Lo sentimos, no pudimos registrar tu like, intenta nuevamente", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void registrarLike(final MediaIns media){
        RestApiAdapter restApiAdapter = RestApiAdapter.getInstancia();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionRestApi();

        String idDispositivo = FirebaseInstanceId.getInstance().getToken();

        Call<LikeResponse> likeResponseCall;
        likeResponseCall = endPointsApi.registrarLike(media.getId(),media.getUsername(),idDispositivo);
        likeResponseCall.enqueue(new Callback<LikeResponse>() {
            @Override
            public void onResponse(Call<LikeResponse> call, Response<LikeResponse> response) {
                LikeResponse likeResponse = response.body();
                if(likeResponse != null){
                    Log.i("notificacion-test", "retornó desde registrar el like");
                    Log.i("notificacion-test", "idLike: "+likeResponse.getId());
                }
            }

            @Override
            public void onFailure(Call<LikeResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return medias.size();
    }

    public static class TimelineViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivMascotaFoto;
        private TextView tvMascotaNombre;
        private TextView tvMascotaRaiting;
        private ImageButton ibMascotaRaitear;

        public TimelineViewHolder(View itemView) {
            super(itemView);
            this.ivMascotaFoto = (ImageView)itemView.findViewById(R.id.iv_mascota_card_foto);
            this.tvMascotaNombre = (TextView) itemView.findViewById(R.id.tv_mascota_card_nombre);
            this.tvMascotaRaiting = (TextView) itemView.findViewById(R.id.tv_mascota_card_raiting);
            this.ibMascotaRaitear = (ImageButton) itemView.findViewById(R.id.iv_mascota_card_hueso1);
        }
    }
}
