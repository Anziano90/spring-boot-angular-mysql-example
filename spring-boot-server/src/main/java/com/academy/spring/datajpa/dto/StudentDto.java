package com.academy.spring.datajpa.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NonNull;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class  StudentDto {

    private Long id;
    private String nome;
    private String cognome;
    private Date dataDiNascita;


    private TutorialDto tutorialSeguito;
    private Timestamp creationDate;
    private Timestamp lastUpdated;
    private int version;

    @NonNull
    public Long getId() {
        return id;
    }

    @NonNull
    public String getNome() {
        return nome;
    }

    @NonNull
    public String getCognome() {
        return cognome;
    }

    @NonNull
    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    @NonNull
    public TutorialDto getTutorialSeguito() {
        return tutorialSeguito;
    }

    @NonNull
    public Timestamp getCreationDate() {
        return creationDate;
    }

    @NonNull
    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    @NonNull
    public int getVersion() {
        return version;
    }
}
