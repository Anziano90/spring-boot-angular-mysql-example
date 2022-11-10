package com.academy.spring.datajpa.repository;

import com.academy.spring.datajpa.model.Autore;
import com.academy.spring.datajpa.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByNome(String nome);
    List<Student> findByCognome(String cognome);
    List<Student> findByDataDiNascitaAfter(Date data);
    List<Student> findByDataDiNascitaBefore(Date data);
    List<Student> findAllByAutoreContainingIgnoreCase(String title);


}
