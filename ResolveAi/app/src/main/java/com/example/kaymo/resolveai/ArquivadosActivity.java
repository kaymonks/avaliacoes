package com.example.kaymo.resolveai;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.orm.SugarContext;

import java.util.List;

public class ArquivadosActivity extends AppCompatActivity {

    ReclamacaoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arquivados);

        RecyclerView rvLista = findViewById(R.id.rvListaArquivados);
        SugarContext.init(this);

        List<Reclamacao> reclamacoes = Reclamacao.findWithQuery(Reclamacao.class, "SELECT * FROM reclamacoes where arquivados = ? ORDER BY curtir DESC, naocurtir ASC", "1");
        adapter = new ReclamacaoAdapter(ArquivadosActivity.this, reclamacoes);
        rvLista.setAdapter(adapter);
        rvLista.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        rvLista.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onClickAddReclamacao(View view) {
        Intent intencao = new Intent(this, ReclamacaoActivity.class);
        startActivityForResult(intencao, 1);
    }
}
