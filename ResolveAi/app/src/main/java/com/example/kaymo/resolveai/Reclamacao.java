package com.example.kaymo.resolveai;

import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

@Table(name = "reclamacoes")
public class Reclamacao extends SugarRecord {

    @Column(name = "categoria")
    private String categoria;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "curtir")
    int curtir;
    @Column(name = "naoCurtir")
    int naoCurtir;
    @Column(name = "resolvido")
    boolean resolvido;
    @Column(name = "arquivados")
    boolean arquivados;

    public boolean isArquivado() {
        return arquivados;
    }

    public void setArquivado(boolean arquivado) {
        this.arquivados = arquivado;
    }

    public Reclamacao() {

    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Column(name = "usuario")
    String usuario;

    public boolean isResolvido() {
        return resolvido;
    }

    public void setResolvido(boolean resolvido) {
        this.resolvido = resolvido;
    }

    public Reclamacao(String categoria, String descricao, int curtir, int naocurtir, String usuario, boolean resolvido, boolean arquivado) {
        this.categoria = categoria;
        this.descricao = descricao;
        this.curtir = curtir;
        this.naoCurtir = naocurtir;
        this.usuario = usuario;
        this.resolvido = resolvido;
        this.arquivados = arquivado;
    }

    public Reclamacao(String categoria, String descricao) {
        this.categoria = categoria;
        this.descricao = descricao;
    }

    public Reclamacao(String categoria, String descricao, int curtir, int naocurtir) {
        this.categoria = categoria;
        this.descricao = descricao;
        this.curtir = curtir;
        this.naoCurtir = naocurtir;

    }

    public Reclamacao(String categoria, String descricao, String curtir) {
        this.categoria = categoria;
        this.descricao = descricao;
        this.curtir = Integer.parseInt(curtir);
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
