package com.example.kaymon.gastronomiakaymon;

import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

@Table(name = "avaliacoes")
public class Avaliacao extends SugarRecord {

    @Column(name = "descricao")
    private String descricao;
    @Column(name = "categoria")
    private String categoria;
    @Column(name = "avaliacao")
    private String avaliacao;

    public Avaliacao(String descricao, String categoria, String avaliacao) {
        this.descricao = descricao;
        this.categoria = categoria;
        this.avaliacao = avaliacao;
    }

    public Avaliacao() {}

    public String getDescricao() {

        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }
}
