package com.example.kaymo.resolveai;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ReclamacaoActivity extends AppCompatActivity {

    RadioGroup categoriaGroup;
    RadioButton button;
    String categoria, imagemCategoria;
    String descricao;
    Button salvar;
    int checkedRadioButtonId;
    private ReclamacaoDAO bd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamacao);


        salvar = findViewById(R.id.btSalvar);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bd = new ReclamacaoDAO(getBaseContext());
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

                Reclamacao reclamacao = new Reclamacao();
                reclamacao.setCategoria(categoria);
                reclamacao.setDescricao(descricao);
                bd.addReclamacao(reclamacao);

                sair();
            }
        });

    }

    public void sair() {
        Intent intent = new Intent(this, ReclamacoesActivity.class);
        this.startActivity(intent);
    }
}
