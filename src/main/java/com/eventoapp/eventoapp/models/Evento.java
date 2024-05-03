package com.eventoapp.eventoapp.models;

import jakarta.persistence.*;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity // classe entidade será uma tabela no banco de dados
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;

    // id do evento no banco de dados
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // id sera gerado automaticamente
    private long codigo;

    //definindo atributos
    @NotEmpty
    private String nome;
    @NotEmpty
    private String local;
    @NotEmpty
    private String data;
    @NotEmpty
    private String horario;

    // estabelecendo relação entre convidado e evento e sua cardinalidade
    @OneToMany(mappedBy="evento", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Convidado> convidados;

    //metodos get
    public long getCodigo() {
        return codigo;
    }
    public String getNome() {
        return nome;
    }
    public String getLocal() {
        return local;
    }
    public String getData() {
        return data;
    }
    public String getHorario() {
        return horario;
    }


    // metodos set
    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setLocal(String local) {
        this.local = local;
    }
    public void setData(String data) {
        this.data = data;
    }
    public void setHorario(String horario) {
        this.horario = horario;
    }
}
