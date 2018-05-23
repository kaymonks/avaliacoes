package com.example.kaymo.resolveai;

import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.dsl.Ignore;
import com.orm.dsl.Table;

import java.util.ArrayList;

/**
 * Created by kaymo on 28/04/2018.
 */

@Table(name = "reclamacoes")
public class Reclamacao extends SugarRecord {

    @Column(name = "categoria")
    private String categoria;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "curtir")
    private int curtir;
    @Column(name = "naoCurtir")
    private int naocurtir;

    public Reclamacao(String categoria, String descricao) {
        this.categoria = categoria;
        this.descricao = descricao;
    }

    public Reclamacao(String categoria, String descricao, int curtir, int naocurtir) {
        this.categoria = categoria;
        this.descricao = descricao;
        this.curtir = curtir;
        this.naocurtir = naocurtir;

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

    public void setCurtir(int qtdCurtir) { this.curtir = qtdCurtir; }

    public int getNaoCurtir() { return naocurtir; }

    public void setNaoCurtir() { this.naocurtir = + 1; }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
//

//    public Long getId() {
//        return id;
//    }
//
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
