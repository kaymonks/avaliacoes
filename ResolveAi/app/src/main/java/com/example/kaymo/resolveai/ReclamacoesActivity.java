package com.example.kaymo.resolveai;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ReclamacoesActivity extends AppCompatActivity {

    private final int ADICIONAR_RECLAMACAO_CODE = 1;
    ReclamacaoAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamacoes);

        RecyclerView rvLista = findViewById(R.id.rvLista);
        ReclamacaoDAO reclamacaoDAO = new ReclamacaoDAO(this);


        adaptador = new ReclamacaoAdapter (ReclamacoesActivity.this, reclamacaoDAO.getAllReclamacao());
        rvLista.setAdapter(adaptador);

        rvLista.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));

        rvLista.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onClickAddReclamacao(View view) {
        Intent intencao = new Intent(this, ReclamacaoActivity.class);
        startActivityForResult(intencao, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADICIONAR_RECLAMACAO_CODE){
            if (resultCode == Activity.RESULT_OK){
                adaptador.notifyDataSetChanged();
            }
        }
    }
}
