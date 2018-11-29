package com.example.kaymo.resolveai;

import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.util.List;

@Table(name = "reclamacoes")
public class Reclamacao extends SugarRecord {
    private Long id;
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
    @Column(name = "data")
    private String data;

    public Long getId() {
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

    public List<Comentario> getAllComentario() {
        List<Comentario> comentario = Comentario.find(Comentario.class, "reclamacao = ?", getId().toString());
        return comentario;
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

    public Reclamacao(String categoria, String descricao, String data, int curtir, int naocurtir, String usuario, boolean resolvido, boolean arquivado) {
        this.categoria = categoria;
        this.descricao = descricao;
        this.data = data;
        this.curtir = curtir;
        this.naoCurtir = naocurtir;
        this.usuario = usuario;
        this.resolvido = resolvido;
        this.arquivados = arquivado;
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
