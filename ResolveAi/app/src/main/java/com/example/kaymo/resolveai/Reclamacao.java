package com.example.kaymo.resolveai;

import java.util.ArrayList;

/**
 * Created by kaymo on 28/04/2018.
 */

public class Reclamacao extends ArrayList<Reclamacao> {
    private int id;
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
        if (naoCurtir != -1) {
            this.naoCurtir = naoCurtir;
        }
    }

    public Reclamacao(String categoria, String descricao, String curtir) {
        this.categoria = categoria;
        this.descricao = descricao;
        this.curtir = Integer.parseInt(curtir);
    }

    public Reclamacao() {

    }

    public void addCurtir() {
        this.curtir = curtir + 1;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setTitulo(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getCurtir() { return curtir; }

    public void setCurtir(int curtir) { this.curtir = this.curtir + curtir; }

    public int getNaoCurtir() { return naoCurtir; }

    public void setNaoCurtir(int naoCurtir) { this.naoCurtir = naoCurtir; }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
