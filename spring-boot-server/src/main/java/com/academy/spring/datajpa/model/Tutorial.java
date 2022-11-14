package com.academy.spring.datajpa.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "tutorials")
@ToString
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Builder
public class Tutorial {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "published")
    private boolean published;

    @OneToMany
    @JsonManagedReference
    @JoinColumn(name="tutorial_id")
    private List<Student> studenti;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Autore autore;

    @CreatedDate
    @Column(name = "creation_date")
    private Timestamp creationDate;

    @LastModifiedDate
    @Column(name = "last_update")
    private Timestamp lastUpdated;

    @Version
    private int version;

    public Tutorial() {

    }

}
