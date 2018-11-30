package com.example.kaymo.resolveai;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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

public class ReclamacoesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private final int ADICIONAR_RECLAMACAO_CODE = 1;
    ReclamacaoAdapter adaptador;
    Reclamacao todasReclamacoes;
    FloatingActionButton btAdicionar;

    DatabaseReference databaseReference;
    List<Reclamacao> reclamacoes;
    RecyclerView rvLista;
    Context context;
    int qtdReclamacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamacoes);

        //databaseReference = FirebaseDatabase.getInstance().getReference().child("reclamacoes");
        //databaseReference.keepSynced(true);
        rvLista = findViewById(R.id.rvLista);
        rvLista.setHasFixedSize(true);
        rvLista.setLayoutManager(new LinearLayoutManager(this));
        rvLista.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        reclamacoes = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("reclamacao").orderByChild("descricao").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    todasReclamacoes = postSnapshot.getValue(Reclamacao.class);
                    Log.d("TAG", "msg"+todasReclamacoes);

                    reclamacoes.add(todasReclamacoes);
                }

                adaptador.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Log.d("TAG", "msg"+reclamacoes);
        adaptador = new ReclamacaoAdapter(ReclamacoesActivity.this, reclamacoes);
        rvLista.setAdapter(adaptador);


                /* Menu Navigation Drawer*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btAdicionarReclamacoes);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getLogin() == "null"){
                    Intent intent = new Intent(getApplication(), LoginActivity.class);
                    startActivityForResult(intent, 1);
                } else {
                    Intent intencao = new Intent(getApplication(), ReclamacaoActivity.class);
                    startActivityForResult(intencao, 1);
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("login", MODE_PRIVATE);
        String login = sharedPreferences.getString("username", "null");
        Log.d("TAG", "Nome login nas preferences:"+login);
        if (!login.equals("admin")){
            hideItem();
        }
        /* //Menu*/
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void hideItem() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        Menu nav_arq = navigationView.getMenu();
        nav_arq.findItem(R.id.nav_arq).setVisible(false);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADICIONAR_RECLAMACAO_CODE){
            if (resultCode == Activity.RESULT_OK){
                adaptador.notifyDataSetChanged();
            }
        }
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

    public String getLogin() {

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("login", MODE_PRIVATE);
//        sharedPreferences.edit().clear().apply();
        String login = sharedPreferences.getString("username", "null");

        return login;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.reclamacao_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_res) {
            Intent intencao = new Intent(this, ResolvidosActivity.class);
            startActivityForResult(intencao, 1);
        } else if (id == R.id.nav_arq) {
            Intent intencao = new Intent(this, ArquivadosActivity.class);
            startActivityForResult(intencao, 1);
        }else if (id == R.id.nav_home) {
            Intent intencao = new Intent(this, MainActivity.class);
            startActivityForResult(intencao, 1);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
