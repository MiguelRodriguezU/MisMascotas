package dev.mrodriguezul.mismascotas.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import dev.mrodriguezul.mismascotas.R;
import dev.mrodriguezul.mismascotas.beans.MediaIns;


public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.MediaViewHolder>{
    private ArrayList<MediaIns> mediasInstagram;
    private Activity actividad;

    public MediaAdapter(Activity actividad,ArrayList<MediaIns> mediasInstagram) {
        this.mediasInstagram = mediasInstagram;
        this.actividad = actividad;
    }


    @Override
    public MediaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_foto,parent,false);

        return new MediaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MediaViewHolder holder, int position) {
        final MediaIns mediaIns = mediasInstagram.get(position);

        Picasso.with(actividad)
                .load(mediaIns.getUrl())
                .placeholder(R.drawable.ic_pet_1)
                .into(holder.ivPerfilFoto);

        holder.tvPerfilRaiting.setText(""+mediaIns.getLikes());

    }

    @Override
    public int getItemCount() {
        return mediasInstagram.size();
    }

    public static class MediaViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivPerfilFoto;
        private TextView tvPerfilRaiting;

        public MediaViewHolder(View itemView) {
            super(itemView);
            this.ivPerfilFoto = (ImageView)itemView.findViewById(R.id.iv_perfil_card_foto);
            this.tvPerfilRaiting = (TextView) itemView.findViewById(R.id.tv_perfil_card_raiting);
        }
    }
}
