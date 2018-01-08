package dev.mrodriguezul.mismascotas.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import dev.mrodriguezul.mismascotas.R;
import dev.mrodriguezul.mismascotas.beans.MediaIns;

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
