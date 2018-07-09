package com.example.kaymon.gastronomiakaymon;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.orm.SugarContext;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    AvaliacaoAdapter adapter;
    Button btTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btAdicionarAvaliacoes);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), AvaliacaoCadastroActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        RecyclerView rvLista = findViewById(R.id.rvLista);
        SugarContext.init(this);

        List<Avaliacao> avaliacoes =  Avaliacao.findWithQuery(Avaliacao.class, "SELECT * FROM avaliacoes");
        adapter = new AvaliacaoAdapter(MainActivity.this, avaliacoes);
        rvLista.setAdapter(adapter);
        rvLista.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        rvLista.setLayoutManager(new LinearLayoutManager(this));
    }
}
