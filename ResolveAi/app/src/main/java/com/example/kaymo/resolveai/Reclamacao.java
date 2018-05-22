package com.example.kaymo.resolveai;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.ArrayList;

/**
 * Created by kaymo on 28/04/2018.
 */

public class Reclamacao extends SugarRecord {
    private Long id;
    private String categoria;
    private String descricao;
    private int curtir;
    private int naoCurtir;

    public Reclamacao(String categoria, String descricao) {
        this.categoria = categoria;
        this.descricao = descricao;
    }

    public Reclamacao(String categoria, String descricao, int curtir, int naoCurtir) {
        this.categoria = categoria;
        this.descricao = descricao;
        this.curtir = curtir;
        this.naoCurtir = naoCurtir;

    }

    public Reclamacao(String categoria, String descricao, String curtir) {
        this.categoria = categoria;
        this.descricao = descricao;
        this.curtir = Integer.parseInt(curtir);
    }

    public Reclamacao() {

    }

    public String getCategoria() {
        return categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getCurtir() { return curtir; }

    public void setCurtir() { this.curtir = this.curtir + 1; }

    public int getNaoCurtir() { return naoCurtir; }

    public void setNaoCurtir() { this.naoCurtir = + 1; }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
//

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
