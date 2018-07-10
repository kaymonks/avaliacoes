package com.example.kaymon.gastronomiakaymon;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class AvaliacaoAdapter extends RecyclerView.Adapter {
    private static final String TAG = "ContentValues";
    private List<Avaliacao> listaAvaliacao;
    private Context context;

    public AvaliacaoAdapter(Context context, List<Avaliacao> listaAvaliacao) {
        this.listaAvaliacao = listaAvaliacao;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View elementoPrincipalXML = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_avaliacao, parent, false);
        AvaliacaoHolder gaveta = new AvaliacaoHolder(elementoPrincipalXML);
        return gaveta;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final AvaliacaoHolder gaveta = (AvaliacaoHolder) holder;
        final Avaliacao daVez = this.listaAvaliacao.get(position);
        Log.d(TAG, "onBindViewHolder: "+String.valueOf(daVez.getCategoria()));
        gaveta.exibeMapa(daVez);
        gaveta.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MapaActivity.class);
                intent.putExtra("categoria", daVez.getCategoria());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.listaAvaliacao.size();
    }
}
