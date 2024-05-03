package com.eventoapp.eventoapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity // classe será uma entidad no banco de dados
public class Convidado {

    // id do convidado no banco de dados
    @Id
    @NotEmpty
    private String rg;

    // definindo atributos
    @NotEmpty
    private String nomeConvidado;

    // estabelecendo relação entre o evento e o convidado e sua cardinalidade
    @ManyToOne
    private Evento evento;

    // métodos get
    public String getRg() {
        return rg;
    }
    public String getNomeConvidado() {
        return nomeConvidado;
    }
    public Evento getEvento() {
        return evento;
    }

    // métodos set
    public void setRg(String rg) {
        this.rg = rg;
    }
    public void setNomeConvidado(String nomeConvidado) {
        this.nomeConvidado = nomeConvidado;
    }
    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}
