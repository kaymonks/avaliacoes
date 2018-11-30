package com.example.kaymo.resolveai;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ComentariosActivity extends AppCompatActivity {

    Button salvar;
    String descricao, nome;
    String reclamacaoId;
    ComentarioAdapter adaptador;
    private DatabaseReference databaseReference;
    List<Comentario> comentarios;
    Comentario todosComentarios;
    RecyclerView rvLista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentarios);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("comentario");
        //databaseReference.keepSynced(true);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("login", MODE_PRIVATE);
        final String login = sharedPreferences.getString("username", "null");

        reclamacaoId =  getIntent().getExtras().getString("reclamacaoId");
        Log.d("TAG", "RECLAMACAOID"+reclamacaoId);

        rvLista = findViewById(R.id.rvComentarios);
        rvLista.setHasFixedSize(true);
        rvLista.setLayoutManager(new LinearLayoutManager(this));
        rvLista.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        comentarios = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("comentario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    todosComentarios = postSnapshot.getValue(Comentario.class);
                    Log.d("TAG", "msg"+todosComentarios);
                    comentarios.add(todosComentarios);
                }
                adaptador.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Log.d("TAG", "TESTE COMENT"+comentarios.toString());
        adaptador = new ComentarioAdapter(ComentariosActivity.this, comentarios);
        rvLista.setAdapter(adaptador);

        databaseReference = FirebaseDatabase.getInstance().getReference("comentario");
        salvar = findViewById(R.id.btSalvar);
        ((View) salvar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText etDescricao = findViewById(R.id.etDescricao);
                final EditText etNome = findViewById(R.id.etNome);

                if (etDescricao.getText().toString().equals("")){
                    Toast.makeText(getBaseContext(), "Descrição obrigatória", Toast.LENGTH_LONG).show();
                    return;
                }
                if (etNome.getText().toString().equals("")){
                    Toast.makeText(getBaseContext(), "Nome obrigatório", Toast.LENGTH_LONG).show();
                    return;
                }

                descricao = etDescricao.getText().toString();
                nome = etNome.getText().toString();

                String id = databaseReference.push().getKey();
                Calendar c = Calendar.getInstance();
//                String dt = DateFormat.getDateInstance().format("dd/MM/yyyy");
                SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
                String datetime = dateformat.format(c.getTime());
                Comentario comentario = new Comentario(id, nome, descricao, datetime, reclamacaoId);


                Log.d("TAG", "TESTEEE COMENTARIO id "+id);

                databaseReference.child(id).setValue(comentario);
                finish();
                startActivity(getIntent());
            }
        });
    }

    private void sair() {
        Intent intent = new Intent(this, ReclamacoesActivity.class);
        this.startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
