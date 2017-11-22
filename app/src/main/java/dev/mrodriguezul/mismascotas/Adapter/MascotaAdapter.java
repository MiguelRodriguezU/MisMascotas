package dev.mrodriguezul.mismascotas.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import dev.mrodriguezul.mismascotas.R;
import dev.mrodriguezul.mismascotas.beans.MascotaItem;

/**
 * Created by MIGUEL on 12/11/2017.
 */

public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder>  {
    private ArrayList<MascotaItem> mascotas;
    private Activity actividad;

    public MascotaAdapter(Activity actividad,ArrayList<MascotaItem> mascotas) {
        this.mascotas = mascotas;
        this.actividad = actividad;
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_mascota,parent,false);

        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MascotaViewHolder holder, int position) {
        final MascotaItem mascota = mascotas.get(position);
        holder.ivMascotaFoto.setImageResource(mascota.getFoto());
        holder.tvMascotaNombre.setText(mascota.getNombre());
        holder.tvMascotaRaiting.setText(""+mascota.getRaiting());

        holder.ibMascotaRaitear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(actividad,"Huesito arriba!!"+mascota.getNombre(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivMascotaFoto;
        private TextView tvMascotaNombre;
        private TextView tvMascotaRaiting;
        private ImageButton ibMascotaRaitear;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            this.ivMascotaFoto = (ImageView)itemView.findViewById(R.id.iv_mascota_card_foto);
            this.tvMascotaNombre = (TextView) itemView.findViewById(R.id.tv_mascota_card_nombre);
            this.tvMascotaRaiting = (TextView) itemView.findViewById(R.id.tv_mascota_card_raiting);
            this.ibMascotaRaitear = (ImageButton) itemView.findViewById(R.id.iv_mascota_card_hueso1);
        }
    }
}
