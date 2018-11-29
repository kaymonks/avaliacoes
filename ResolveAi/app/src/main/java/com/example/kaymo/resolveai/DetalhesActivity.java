package com.example.kaymo.resolveai;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.orm.SugarRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetalhesActivity extends AppCompatActivity {

    String categoria, descricao, icon, usuario, data;
    TextView tvDescricao, tvCategoria, tvData;
    TextView tvIcon;
    ConexaoInternet conexaoInternet;
    FloatingActionButton  btCurtir, btNaoCurtir;
    int qtdCurtir, qtdNaoCurtir, excluirItem = 10;//diferença entre não curtir e curtir exclui o registro
    Long id;
    CheckBox cbResolvido;
    Button comentario;
    boolean resolvido = false;
    boolean novoResolvido;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        tvCategoria = findViewById(R.id.tvCategoria);
        tvDescricao = findViewById(R.id.tvDescricao);
        tvIcon = findViewById(R.id.tvIcon);
        tvData = findViewById(R.id.tvData);
        btCurtir = findViewById(R.id.btCurtir);
        btNaoCurtir = findViewById(R.id.btNaoCurtir);
        cbResolvido = (CheckBox) findViewById(R.id.cbResolvido);
        comentario = findViewById(R.id.btComentario);

        icon = getIntent().getExtras().getString("icon");
        categoria = getIntent().getExtras().getString("categoria");
        descricao = getIntent().getExtras().getString("descricao");
        data = getIntent().getExtras().getString("data");
        id = getIntent().getExtras().getLong("id", -1);
        qtdCurtir = getIntent().getExtras().getInt("curtir", -1);
        qtdNaoCurtir = getIntent().getExtras().getInt("naoCurtir", -1);
        usuario = getIntent().getExtras().getString("usuario", null);
        resolvido = getIntent().getExtras().getBoolean("resolvido");
        Log.d("TAG", "atributo resolvido"+ resolvido+"Usuario"+usuario);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("login", MODE_PRIVATE);
        String login = sharedPreferences.getString("username", "null");
        final Reclamacao reclamacao = Reclamacao.findById(Reclamacao.class, id);

        if (usuario != null) {
            if (resolvido) {
                cbResolvido.setChecked(true);
            }
            cbResolvido.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cbResolvido.isChecked()) {
                        novoResolvido = true;
                    } else {
                        novoResolvido = false;
                    }
                }
            });
        }

        if (login.equals("null")) {
            cbResolvido.setVisibility(View.GONE);
            Log.d("TAG", "usuario diferente de 'admin':"+usuario);
        }

        tvIcon.setText(icon);
        tvCategoria.setText(categoria);
        tvDescricao.setText(descricao);
        tvData.setText(data);
        conexaoInternet = new ConexaoInternet(this);
        btCurtir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.d("Tag", id+"id"+"qtdcurtir"+qtdCurtir+"Resolvido"+novoResolvido);
            qtdCurtir += 1;
//                Log.d("Tag", id+"id"+"qtdcurtir atualizado"+qtdCurtir);

            Reclamacao reclamacao = Reclamacao.findById(Reclamacao.class, id);

            reclamacao.setCurtir(qtdCurtir);
            if (reclamacao.getNaoCurtir() - reclamacao.getCurtir() <= excluirItem) {
                reclamacao.setArquivado(false);
            }
            reclamacao.setResolvido(novoResolvido);
            reclamacao.save();

            Intent intent = new Intent(getBaseContext(), ReclamacoesActivity.class);
            startActivity(intent);

            }
        });

        btNaoCurtir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            qtdNaoCurtir += 1;

            Reclamacao reclamacao = Reclamacao.findById(Reclamacao.class, id);
            reclamacao.setNaoCurtir(qtdNaoCurtir);
            reclamacao.setResolvido(novoResolvido);
            if (reclamacao.getNaoCurtir() - reclamacao.getCurtir() > excluirItem) {
                reclamacao.setArquivado(true);
            }
            reclamacao.save();

            Intent intent = new Intent(getBaseContext(), ReclamacoesActivity.class);
            startActivity(intent);

            }
        });

        comentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ComentariosActivity.class);
                intent.putExtra("reclamacaoId", reclamacao.getId());
                startActivity(intent);
            }
        });

    }
}
