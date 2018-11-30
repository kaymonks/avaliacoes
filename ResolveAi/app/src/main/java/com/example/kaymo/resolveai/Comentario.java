package com.example.kaymo.resolveai;

public class Comentario  {
    private String id;
    private String nome;
    private String decricao;
    private String data;
    private String reclamacao;

    public Comentario() {}

    public Comentario(String id, String nome, String decricao, String data, String reclamacao) {
        this.nome = nome;
        this.decricao = decricao;
        this.data = data;
        this.reclamacao = reclamacao;
        this.id = id;
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

    public String getId() { return id; }

    public String getDecricao() {
        return decricao;
    }

    public String getData() {
        return data;
    }

    public String getReclamacao() { return reclamacao; }

    public void setReclamacao(String reclamacao) { this.reclamacao = reclamacao; }


}
