package dev.mrodriguezul.mismascotas.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import dev.mrodriguezul.mismascotas.R;
import dev.mrodriguezul.mismascotas.beans.FotoItem;

public class FotoAdapter extends RecyclerView.Adapter<FotoAdapter.FotoViewHolder>{
    private ArrayList<FotoItem> fotos;
    private Activity actividad;

    public FotoAdapter(Activity actividad,ArrayList<FotoItem> fotos) {
        this.fotos = fotos;
        this.actividad = actividad;
    }

    @Override
    public FotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_foto,parent,false);

        return new FotoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FotoViewHolder holder, int position) {
        final FotoItem foto = fotos.get(position);
        holder.ivPerfilFoto.setImageResource(foto.getFoto());
        holder.tvPerfilRaiting.setText(""+foto.getRaiting());
    }

    @Override
    public int getItemCount() {
        return fotos.size();
    }

    public static class FotoViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivPerfilFoto;
        private TextView tvPerfilRaiting;

        public FotoViewHolder(View itemView) {
            super(itemView);
            this.ivPerfilFoto = (ImageView)itemView.findViewById(R.id.iv_perfil_card_foto);
            this.tvPerfilRaiting = (TextView) itemView.findViewById(R.id.tv_perfil_card_raiting);
        }
    }
}
