package com.example.kaymo.resolveai;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ComentarioAdapter extends RecyclerView.Adapter {
    private List<Comentario> listaComentario;
    private Context context;

    public ComentarioAdapter(Context context, List<Comentario> listaComentario) {
        this.listaComentario = listaComentario;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View elementoPrincipalXML = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comentario, parent, false);
        ComentarioHolder gaveta = new ComentarioHolder(elementoPrincipalXML);
       return gaveta;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final ComentarioHolder gaveta = (ComentarioHolder) holder;
        final Comentario daVez = this.listaComentario.get(position);
        gaveta.exibeComentario(daVez);
    }

    @Override
    public int getItemCount() {
        return this.listaComentario.size();
    }
}
