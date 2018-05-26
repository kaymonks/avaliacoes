package com.example.kaymo.resolveai;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText etLogin, etSenha;
    Button btLogin, btRegistro;
    String login, senha, novoLogin, novaSenha;
    Context context;
    int novoId = 0;
    final String URL = "http://192.168.15.14:8080/api/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLogin = findViewById(R.id.etLogin);
        etSenha = findViewById(R.id.etSenha);
        btLogin = findViewById(R.id.btCadastro);
    }

    public void login(View view) {

        novoLogin = etLogin.getText().toString();
        novaSenha = etSenha.getText().toString();

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("login2", MODE_PRIVATE);
//        sharedPreferences.edit().clear().apply();

//        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("login", MODE_PRIVATE);
        login = sharedPreferences.getString("login", "null");
        senha = sharedPreferences.getString("senha", "null");
        Log.d("TAG", "Dados"+login+senha+novoLogin+novaSenha);

        if (login == novoLogin && senha == novaSenha) {
            Intent intent = new Intent(this, ReclamacoesActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Login inválido", Toast.LENGTH_LONG).show();
        }

//        StringRequest request = new StringRequest(
//            Request.Method.POST,
//            URL,
//            new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                        try {
//                            JSONArray array = new JSONArray(response);
//                            Log.d("Tag login com id", array+"teste");
//                            for (int i=0; i < array.length(); i++) {
//                                JSONObject product = array.getJSONObject(i);
//
//                                novoLogin = product.getString("login");
//                                novaSenha = product.getString("senha");
//                                novoId = product.getInt("id");
//
//                            }
//                            if (novoLogin == null) {
//                                Toast.makeText(getApplication(), "Login invalido", Toast.LENGTH_LONG).show();
//                            } else {
//                                setPreferences(novoLogin, novaSenha, novoId);
//                                startActivity(new Intent(LoginActivity.this, ReclamacaoActivity.class));
//                                                            Log.d("Tag login String com id", novoLogin+novaSenha+novoId+"");
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                }
//            },
//            new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Log.d("Tag", error+"");
//                }
//            }
//        ){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("login", login);
//                params.put("senha", senha);
//
//                return params;
//            }
//        };
//
//        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
//        requestQueue.add(request);

    }

    public void novoUsuario(View view) {
        Intent intent = new Intent(this, NovoUsuarioActivity.class);
        this.startActivity(intent);
    }

    public void setPreferences(String login, String senha, int id){
        Log.d("Tag set preferences id", id+"");
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.clear();
        editor.putString("login", login);
        editor.putString("senha", senha);
        editor.putString("id", String.valueOf(id));
        editor.apply();
    }
}
