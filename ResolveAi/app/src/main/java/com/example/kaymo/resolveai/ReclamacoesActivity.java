package com.example.kaymo.resolveai;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orm.SugarContext;
import com.orm.query.Select;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ReclamacoesActivity extends AppCompatActivity {

    private final int ADICIONAR_RECLAMACAO_CODE = 1;
    private Gson gson;
    ReclamacaoAdapter adaptador;
    ConexaoInternet conexaoInternet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamacoes);

        final ArrayList<Reclamacao> mReclamacaoList = new ArrayList<>();
        String URL = "http://192.168.15.14:8080/api/reclamations";
        final RecyclerView rvLista = findViewById(R.id.rvLista);
        SugarContext.init( this );
//        conexaoInternet = new ConexaoInternet(this);
//        StringRequest request = new StringRequest(
//                Request.Method.GET,
//                URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.d("TAG", response.toString());
//                        GsonBuilder builder = new GsonBuilder();
//                        Gson gson = builder.create();
//                        List<Reclamacao> reclamacoes = Arrays.asList(gson.fromJson(response, Reclamacao[].class));
//                        adaptador = new ReclamacaoAdapter(ReclamacoesActivity.this, reclamacoes);
//                        rvLista.setAdapter(adaptador);
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.d("TAG", error.getMessage());
//                        Toast.makeText(ReclamacoesActivity.this, "Alguma coisa deu errado" + error.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//        );
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(request);

//            List<Reclamacao> reclamacoes = Reclamacao.listAll(Reclamacao.class);
        List<Reclamacao> reclamacoes = Reclamacao.findWithQuery(Reclamacao.class, "SELECT * FROM reclamacoes where resolvido = ? ORDER BY curtir DESC, naocurtir ASC", "0");
        adaptador = new ReclamacaoAdapter(ReclamacoesActivity.this, reclamacoes);
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
