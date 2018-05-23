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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.orm.SugarRecord;

import java.util.HashMap;
import java.util.Map;

public class DetalhesActivity extends AppCompatActivity {

    String categoria, descricao, icon;
    TextView tvDescricao, tvCategoria;
    TextView tvIcon;
    ConexaoInternet conexaoInternet;
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
        final String URL = "http://192.168.15.14:8080/api/reclamation/"+id;
        conexaoInternet = new ConexaoInternet(this);
        btCurtir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Tag", id+"id"+"qtdcurtir"+qtdCurtir);
                qtdCurtir += 1;
                Log.d("Tag", id+"id"+"qtdcurtir atualizado"+qtdCurtir);



                StringRequest request = new StringRequest(
                        Request.Method.PUT,
                        URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getApplication(), "Obrigado pelo voto", Toast.LENGTH_LONG).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("Tag", error+"");
                            }
                        }
                ){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("curtir", String.valueOf(qtdCurtir));

                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(DetalhesActivity.this);
                requestQueue.add(request);

                Reclamacao reclamacao = Reclamacao.findById(Reclamacao.class, id);
                reclamacao.setCurtir(qtdCurtir);
                reclamacao.save();

                Intent intent = new Intent(getBaseContext(), ReclamacoesActivity.class);
                startActivity(intent);

            }
        });

        btNaoCurtir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qtdNaoCurtir += 1;
                StringRequest request = new StringRequest(
                        Request.Method.PUT,
                        URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
//                                Toast.makeText(getApplication(), "Obrigado pelo voto", Toast.LENGTH_LONG).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("Tag", error+"");
                            }
                        }
                ){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("naocurtir", String.valueOf(qtdNaoCurtir));

                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(DetalhesActivity.this);
                requestQueue.add(request);

                Intent intent = new Intent(getBaseContext(), ReclamacoesActivity.class);
                startActivity(intent);

                Reclamacao reclamacao = Reclamacao.findById(Reclamacao.class, id);
                reclamacao.setNaoCurtir(qtdNaoCurtir);
                reclamacao.save();

            }
        });

    }
}
