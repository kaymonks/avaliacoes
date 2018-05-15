package com.example.kaymo.resolveai;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import static android.support.constraint.Constraints.TAG;

/**
 * Created by kaymo on 28/04/2018.
 */

class ReclamacaoHolder extends RecyclerView.ViewHolder {

    private ImageView ivCategoria;
    private TextView tvDescricao, tvCategoria, tvUp, tvDown;
    public TextView tvIcon;

    public ReclamacaoHolder(View itemView) {
        super(itemView);
        tvIcon = itemView.findViewById(R.id.tvIcon);
        tvCategoria = itemView.findViewById(R.id.tvCategoria);
        tvDescricao = itemView.findViewById(R.id.tvDescricao);
//        ivCategoria = itemView.findViewById(R.id.ivCategoria);
        tvUp = itemView.findViewById(R.id.tvUp);
        tvDown = itemView.findViewById(R.id.tvDown);
    }

    public void exibeReclamacao(Reclamacao daVez) {
        tvIcon.setText(daVez.getCategoria().substring(0, 1));
        tvDescricao.setText(daVez.getDescricao());
        tvUp.setText(String.valueOf(daVez.getCurtir()));
        tvDown.setText(String.valueOf(daVez.getNaoCurtir()));
        tvCategoria.setText(daVez.getCategoria());
        Random tvIconRandom = new Random();
        int color = Color.argb(255, tvIconRandom.nextInt(256), tvIconRandom.nextInt(256), tvIconRandom.nextInt(256));
        Log.d(TAG, "exibeReclamacao: "+color);
        ((GradientDrawable) tvIcon.getBackground()).setColor(color);
    }
}
