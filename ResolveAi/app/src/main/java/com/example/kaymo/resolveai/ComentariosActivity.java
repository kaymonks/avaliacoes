package com.example.kaymo.resolveai;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.orm.SugarContext;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class ComentariosActivity extends AppCompatActivity {

    Button salvar;
    String descricao;
    Long reclamacaoId;
    ComentarioAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentarios);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("login", MODE_PRIVATE);
        final String login = sharedPreferences.getString("username", "null");

        reclamacaoId =  getIntent().getLongExtra("reclamacaoId", -1);
        Log.d("TAG", "RECLAMACAOID"+reclamacaoId);
        final Reclamacao reclamacao = Reclamacao.findById(Reclamacao.class, reclamacaoId);
        Log.d("Log id reclamacao", "Log id reclamacao"+reclamacao.getId());

        RecyclerView rvLista = findViewById(R.id.rvComentarios);
        SugarContext.init(this);

        List<Comentario> comentarios = Comentario.findWithQuery(Comentario.class, "SELECT * FROM comentarios WHERE reclamacao = ?", reclamacaoId.toString());
        Log.d("TAG", "TESTE COMENT"+comentarios.toString());
        adaptador = new ComentarioAdapter(ComentariosActivity.this, comentarios);
        rvLista.setAdapter(adaptador);
        rvLista.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        rvLista.setLayoutManager(new LinearLayoutManager(this));

        salvar = findViewById(R.id.btSalvar);
        ((View) salvar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText etDescricao = findViewById(R.id.etDescricao);

                if (etDescricao.getText().toString().equals("")){
                    Toast.makeText(getBaseContext(), "Descrição obrigatória", Toast.LENGTH_LONG).show();
                    return;
                }

                descricao = etDescricao.getText().toString();
                //String nome, String decricao, String data, Reclamacao reclamacao
                Log.d("TAG", "descricao"+descricao);
                Calendar c = Calendar.getInstance();
                SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
                String datetime = dateformat.format(c.getTime());
                //System.out.println(datetime);
                Comentario comentario = new Comentario("nome", descricao, datetime, reclamacao.getId());

                comentario.save();

                //Log.d("TAG", "Ultimo id comentario inserido"+idInserido);
                sair();
            }
        });
    }

    private void sair() {
        Intent intent = new Intent(this, ReclamacoesActivity.class);
        this.startActivity(intent);
    }
}
