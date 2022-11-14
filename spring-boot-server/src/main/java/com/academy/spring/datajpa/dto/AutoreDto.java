package com.academy.spring.datajpa.dto;

import lombok.Data;
import lombok.NonNull;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class AutoreDto {

    private Long id;
    private String nome;
    private String cognome;
    private Date dataDiNascita;
    private List<TutorialDto> listaTutorial;
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
    public List<TutorialDto> getListaTutorial() {
        return listaTutorial;
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
