package com.example.kaymo.resolveai;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by kaymo on 28/04/2018.
 */

class ReclamacaoAdapter extends RecyclerView.Adapter {
    private List<Reclamacao> listaReclamacao;
    private Context context;

    public ReclamacaoAdapter(Context context, List<Reclamacao> listaReclamacao) {
        this.listaReclamacao = listaReclamacao;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View elementoPrincipalXML = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reclamacao, parent, false);
        ReclamacaoHolder gaveta = new ReclamacaoHolder(elementoPrincipalXML);
        return gaveta;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final ReclamacaoHolder gaveta = (ReclamacaoHolder) holder;
        final Reclamacao daVez = this.listaReclamacao.get(position);
        Log.d(TAG, "onBindViewHolder: "+String.valueOf(daVez.getCurtir())+String.valueOf(daVez.getCategoria()));
        gaveta.exibeReclamacao(daVez);
        gaveta.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetalhesActivity.class);
                intent.putExtra("id", daVez.getId());
                intent.putExtra("icon", gaveta.tvIcon.getText().toString());
                intent.putExtra("descricao", daVez.getDescricao());
                intent.putExtra("categoria", daVez.getCategoria());
                intent.putExtra("curtir", daVez.getCurtir());
                intent.putExtra("naoCurtir", daVez.getNaoCurtir());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.listaReclamacao.size();
    }
}
