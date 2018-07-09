package com.example.kaymon.gastronomiakaymon;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class AvaliacaoHolder extends RecyclerView.ViewHolder {

    public TextView tvDescricao, tvCategoria, tvFavorito, tvImagem;

    public AvaliacaoHolder(View itemView) {
        super(itemView);
        tvDescricao = itemView.findViewById(R.id.tvDescricao);
        tvCategoria = itemView.findViewById(R.id.tvCategoria);

    }

    public void exibeMapa(Avaliacao daVez) {
        tvDescricao.setText(daVez.getDescricao());
        tvCategoria.setText(daVez.getCategoria());
    }
}
