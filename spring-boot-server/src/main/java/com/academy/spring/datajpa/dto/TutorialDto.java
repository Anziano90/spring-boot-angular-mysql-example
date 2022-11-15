package com.academy.spring.datajpa.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NonNull;

import java.sql.Timestamp;
import java.util.List;

@Data
public class TutorialDto {

    private long id;

    private String title;

    private String description;

    private boolean published;


    private List<StudentDto> studenti;


    private AutoreDto autore;

    private Timestamp creationDate;

    private Timestamp lastUpdated;

    private int version;

    @NonNull
    public long getId() {
        return id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    @NonNull
    public boolean isPublished() {
        return published;
    }

    @NonNull
    public List<StudentDto> getStudenti() {
        return studenti;
    }

    @NonNull
    public AutoreDto getAutore() {
        return autore;
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
