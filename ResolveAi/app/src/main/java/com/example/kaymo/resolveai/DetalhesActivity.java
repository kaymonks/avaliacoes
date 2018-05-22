package com.example.kaymo.resolveai;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.orm.SugarRecord;

public class DetalhesActivity extends AppCompatActivity {

    String categoria, descricao, icon;
    TextView tvDescricao, tvCategoria, tvCurtidas, tvNaoCurtidas;
    TextView tvIcon;

    FloatingActionButton  btCurtir, btNaoCurtir;
    int qtdCurtir, qtdNaoCurtir;
    Long id;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        tvCategoria = findViewById(R.id.tvCategoria);
        tvDescricao = findViewById(R.id.tvDescricao);
        tvIcon = findViewById(R.id.tvIcon);
        btCurtir = findViewById(R.id.btCurtir);
        btNaoCurtir = findViewById(R.id.btNaoCurtir);

        icon = getIntent().getExtras().getString("icon");
        categoria = getIntent().getExtras().getString("categoria");
        descricao = getIntent().getExtras().getString("descricao");
        id = getIntent().getExtras().getLong("id", -1);
        qtdCurtir = getIntent().getExtras().getInt("curtir", -1);
        qtdNaoCurtir = getIntent().getExtras().getInt("naoCurtir", -1);

        tvIcon.setText(icon);
        tvCategoria.setText(categoria);
        tvDescricao.setText(descricao);

        btCurtir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Reclamacao reclamacao = Reclamacao.findById(Reclamacao.class, id);
                reclamacao.setCurtir();
                reclamacao.save();

                Intent intent = new Intent(getBaseContext(), ReclamacoesActivity.class);
                startActivity(intent);

            }
        });

        btNaoCurtir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Reclamacao reclamacao = Reclamacao.findById(Reclamacao.class, id);
                reclamacao.setNaoCurtir();
                reclamacao.save();

                Intent intent = new Intent(getBaseContext(), ReclamacoesActivity.class);
                startActivity(intent);
            }
        });

    }
}
