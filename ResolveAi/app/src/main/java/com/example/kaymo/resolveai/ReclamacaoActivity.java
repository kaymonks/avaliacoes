package com.example.kaymo.resolveai;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.text.SimpleDateFormat;

import java.util.Calendar;

public class ReclamacaoActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    RadioGroup categoriaGroup;
    RadioButton button;
    String categoria, imagemCategoria;
    String descricao;
    Button salvar;
    int checkedRadioButtonId, idMysql;
    Context context;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamacao);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("login", MODE_PRIVATE);
        final String login = sharedPreferences.getString("username", "null");
        databaseReference = FirebaseDatabase.getInstance().getReference("reclamacao");
        salvar = findViewById(R.id.btSalvar);
        salvar.setOnClickListener(new View.OnClickListener() {
//            final String URL = "http://192.168.15.14:80/80/api/reclamation/";

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


                String id = databaseReference.push().getKey();
                Log.d("TAG", "TESTEEE id "+id);


                Calendar c = Calendar.getInstance();
                SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
                String datetime = dateformat.format(c.getTime());
                Reclamacao reclamacao = new Reclamacao(id, categoria, descricao, datetime, 0, 0, false, false);

                databaseReference.child(id).setValue(reclamacao);

                Log.d("TAG", "Ultimo id inserido"+id);
                sair();
            }
        });

    }

    public void sair() {
        Intent intent = new Intent(this, ReclamacoesActivity.class);
        this.startActivity(intent);
    }
}
