package com.academy.spring.datajpa.repository;

import com.academy.spring.datajpa.model.Autore;
import com.academy.spring.datajpa.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface AutoreRepository extends JpaRepository<Autore, Long> {
    List<Autore>findByNome(String nome);
    List<Autore>findByCognome(String cognome);
    List<Autore>findById(long id);
    List<Autore>findAutoreByDataDiNascitaAfter(Date data);
    List<Autore>findAutoreByDataDiNascitaBefore(Date data);
}
