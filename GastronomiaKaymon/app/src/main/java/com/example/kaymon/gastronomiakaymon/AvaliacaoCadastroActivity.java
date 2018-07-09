package com.example.kaymon.gastronomiakaymon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AvaliacaoCadastroActivity extends AppCompatActivity {

    Button salvar;
    EditText etDescricao;
    int checkedRadioButtonId;
    RadioButton button;
    RadioGroup categoriaGroup;
    String categoria, descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao_cadastro);

        salvar = findViewById(R.id.btSalvar);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etDescricao = findViewById(R.id.etDescricao);
                categoriaGroup = findViewById(R.id.rgCategoria);
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

                Avaliacao avaliacao = new Avaliacao(categoria, descricao, "avaliacao");
                Long idInserido = avaliacao.save();

                Log.d("TAG", "ultimo id"+idInserido);
                sair();
            }
        });
    }

    private void sair() {
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }
}
