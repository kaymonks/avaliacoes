package com.example.kaymo.resolveai;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClickAddReclamacao(View view) {
        Intent intencao = new Intent(this, ReclamacaoActivity.class);
        startActivityForResult(intencao, 1);
    }

    public void onClickReclamacoes(View view) {
        Intent intencao = new Intent(this, ReclamacoesActivity.class);
        startActivityForResult(intencao, 1);
    }
}
