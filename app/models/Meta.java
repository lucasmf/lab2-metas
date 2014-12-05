package models;

import javax.persistence.*;
import java.util.GregorianCalendar;

/**
 * Created by lucas on 11/22/14.
 */
@Entity(name = "Meta")
public class Meta implements Comparable{

    @Id
    @SequenceGenerator(name = "META_SEQUENCE", sequenceName = "META_SEQUENCE", allocationSize = 1, initialValue = 0)
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    private String nome;
    private String descricao;
    private int semanasRestantes;
    private int prioridade;
    private int alcancada;

    public Meta() {}

    public Meta(String nome, String descricao,  int semanasRestantes, int prioridade) {
        this.nome = nome;
        this.descricao = descricao;
        this.semanasRestantes = semanasRestantes;
        this.prioridade = prioridade;
        alcancada = 0;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getAlcancada() {
        return alcancada;
    }

    public void setAlcancada(int alcancada) {
        this.alcancada = alcancada;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getSemanasRestantes() {
        return semanasRestantes;
    }

    public void setSemanasRestantes(int semanasRestantes) {
        this.semanasRestantes = semanasRestantes;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int compareTo(Object o) {
        Meta outraMeta = (Meta)o;
        if(this.alcancada != outraMeta.alcancada) return outraMeta.alcancada-this.alcancada;
        if(this.semanasRestantes != outraMeta.semanasRestantes) return this.semanasRestantes - outraMeta.semanasRestantes;
        return outraMeta.prioridade-this.prioridade;

    }



}
