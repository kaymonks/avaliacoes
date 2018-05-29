package com.example.kaymo.resolveai;

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

public class NovoUsuarioActivity extends AppCompatActivity {

    EditText etLogin, etSenha;
    Button btLogin, btRegistro;
    String login, senha;
    final String URL = "http://192.168.15.14:8080/api/login/create";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_usuario);

        etLogin = findViewById(R.id.etLogin);
        etSenha = findViewById(R.id.etSenha);
        btRegistro = findViewById(R.id.btCadastro);
    }

        public void cadastrar(View view) {
            login = etLogin.getText().toString();
            senha = etSenha.getText().toString();

            if (login.equals("")) {
                Toast.makeText(getBaseContext(), "Login obrigatório", Toast.LENGTH_LONG).show();
                return;
            }

            if (senha.equals("")) {
                Toast.makeText(getBaseContext(), "Senha obrigatório", Toast.LENGTH_LONG).show();
                return;
            }

            setPreferences(login, senha);
            Log.d("TAG", "Dados"+login+senha);
            startActivity(new Intent(NovoUsuarioActivity.this, LoginActivity.class));
    }

    public void setPreferences(String login, String senha){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("login", MODE_PRIVATE);
//        sharedPreferences.edit().clear().apply();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putString("username", login);
        editor.putString("senha", senha);
        editor.apply();
    }
}
