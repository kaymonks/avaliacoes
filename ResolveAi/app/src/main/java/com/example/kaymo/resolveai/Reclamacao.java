package com.example.kaymo.resolveai;

public class Reclamacao {
    private String id;
    private String categoria;
    private String descricao;
    private int curtir;
    private int naoCurtir;
    private boolean resolvido;
    boolean arquivados;
    private String data;

    public String getId() {
        return id;
    }

    public boolean isArquivado() {
        return arquivados;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setArquivado(boolean arquivado) {
        this.arquivados = arquivado;
    }

    public Reclamacao() {

    }


    public boolean isResolvido() {
        return resolvido;
    }

    public void setResolvido(boolean resolvido) {
        this.resolvido = resolvido;
    }

    public Reclamacao(String id, String categoria, String descricao, String data, int curtir, int naocurtir, boolean resolvido, boolean arquivado) {
        this.categoria = categoria;
        this.descricao = descricao;
        this.data = data;
        this.curtir = curtir;
        this.naoCurtir = naocurtir;
        this.resolvido = resolvido;
        this.arquivados = arquivado;
        this.id = id;
    }


    public String getCategoria() {
        return categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getCurtir() { return curtir; }

    public void setCurtir(int qtdCurtir) { this.curtir = qtdCurtir; }

    public int getNaoCurtir() { return naoCurtir; }

    public void setNaoCurtir(int qtdNaoCurtir) { this.naoCurtir = qtdNaoCurtir; }

}
