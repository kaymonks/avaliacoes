package com.example.kaymo.resolveai;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.orm.SugarContext;

import java.util.List;

public class ResolvidosActivity extends AppCompatActivity {

    ReclamacaoAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resolvidos);

        RecyclerView rvLista = findViewById(R.id.rvListaResolvidos);
        SugarContext.init(this);

        List<Reclamacao> reclamacoes = Reclamacao.findWithQuery(Reclamacao.class, "SELECT * FROM reclamacoes where resolvido = ? ORDER BY curtir DESC, naocurtir ASC", "1");
        adapter = new ReclamacaoAdapter(ResolvidosActivity.this, reclamacoes);
        rvLista.setAdapter(adapter);
        rvLista.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        rvLista.setLayoutManager(new LinearLayoutManager(this));
    }
}
