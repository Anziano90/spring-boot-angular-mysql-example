package com.academy.spring.datajpa.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Autore {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nome;
    private String cognome;
    private Date dataDiNascita;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Autore(){}

    public Autore(String nome, String cognome, Date dataDiNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(Date dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autore autore = (Autore) o;
        return Objects.equals(id, autore.id) && Objects.equals(nome, autore.nome) && Objects.equals(cognome, autore.cognome) && Objects.equals(dataDiNascita, autore.dataDiNascita);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cognome, dataDiNascita);
    }

    @Override
    public String toString() {
        return "Autore{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataDiNascita=" + dataDiNascita +
                '}';
    }
}
