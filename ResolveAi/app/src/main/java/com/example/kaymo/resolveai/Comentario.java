package com.example.kaymo.resolveai;

import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.util.List;

@Table(name = "comentarios")
public class Comentario extends SugarRecord {
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "descricao")
    private String decricao;
    @Column(name = "data")
    private String data;

    Long reclamacao;
    private List<Comentario> comentarios;

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public Comentario() {}

    public Comentario(String nome, String decricao, String data, Long reclamacao) {
        this.nome = nome;
        this.decricao = decricao;
        this.data = data;
        this.reclamacao = reclamacao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDecricao(String decricao) {
        this.decricao = decricao;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public Long getId() { return id; }

    public String getDecricao() {
        return decricao;
    }

    public String getData() {
        return data;
    }


}
