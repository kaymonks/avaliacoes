package com.example.kaymo.resolveai;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

class ComentarioHolder extends RecyclerView.ViewHolder {

    private TextView tvNome, tvComentario, tvData;
    public  TextView tvIcon;


    public ComentarioHolder(View itemView) {
        super(itemView);
        tvIcon = itemView.findViewById(R.id.tvIcon);
        tvNome = itemView.findViewById(R.id.tvNome);
        tvComentario = itemView.findViewById(R.id.tvComentario);
        tvData = itemView.findViewById(R.id.tvData);

    }

    public void exibeComentario(Comentario comentario) {
        tvIcon.setText(comentario.getNome().toUpperCase().substring(0, 1));
        tvNome.setText(comentario.getNome());
        tvComentario.setText(comentario.getDecricao());
        tvData.setText(comentario.getData());
    }
}
