package com.example.kaymo.resolveai;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orm.SugarContext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReclamacaoActivity extends AppCompatActivity {

    RadioGroup categoriaGroup;
    RadioButton button;
    String categoria, imagemCategoria;
    String descricao;
    Button salvar;
    int checkedRadioButtonId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamacao);
        SugarContext.init( this );
        salvar = findViewById(R.id.btSalvar);
        salvar.setOnClickListener(new View.OnClickListener() {
            String URL = "http://192.168.15.14:8080/api/reclamation/";

            @Override
            public void onClick(View view) {
                final EditText etDescricao = findViewById(R.id.etDescricao);

                categoriaGroup = ( RadioGroup ) findViewById(R.id.rgCategoria);
                checkedRadioButtonId = categoriaGroup.getCheckedRadioButtonId();

                if (checkedRadioButtonId == -1) {
                    Toast.makeText(getBaseContext(), "Escolha uma categoria", Toast.LENGTH_LONG).show();
                    return;
                }

                if (etDescricao.getText().toString().equals("")) {
                    Toast.makeText(getBaseContext(), "Descrição obrigatório", Toast.LENGTH_LONG).show();
                    return;
                }

                button = categoriaGroup.findViewById(checkedRadioButtonId);
                categoria = (String) button.getText();
                descricao = etDescricao.getText().toString();

//                Log.d(TAG, "onClick: "+categoria + descricao);

                StringRequest request = new StringRequest(
                        Request.Method.POST,
                        URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getApplication(), "Problema adicionado com sucesso", Toast.LENGTH_LONG).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(ReclamacaoActivity.this, error+"", Toast.LENGTH_LONG).show();
                            }
                        }
                ){
                  @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                      Map<String, String> params = new HashMap<String, String>();
                      params.put("categoria", categoria);
                      params.put("descricao", descricao);
                      params.put("curtir", "0");
                      params.put("naocurtir", "0");
                      return params;
                  }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(ReclamacaoActivity.this);
                requestQueue.add(request);


//                Reclamacao reclamacao = new Reclamacao(categoria, descricao, 0, 0);
//
//                reclamacao.save();

                sair();
            }
        });

    }

    public void sair() {
        Intent intent = new Intent(this, ReclamacoesActivity.class);
        this.startActivity(intent);
    }
}
