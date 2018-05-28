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
