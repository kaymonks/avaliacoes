package com.example.kaymo.resolveai;

import android.app.Activity;
import android.content.Context;
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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClickAddReclamacao(View view) {
        Intent intencao;
        if (getLogin() == "null"){
            intencao = new Intent(this, LoginActivity.class);
        }else {
            intencao = new Intent(this, ReclamacaoActivity.class);
        }

        startActivityForResult(intencao, 1);
    }

    public void onClickReclamacoes(View view) {
        Intent intencao = new Intent(this, ReclamacoesActivity.class);
        startActivityForResult(intencao, 1);
    }

    public String getLogin() {

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("login", MODE_PRIVATE);
        //sharedPreferences.edit().clear().apply();
        String login = sharedPreferences.getString("username", "null");

        return login;
    }
}
