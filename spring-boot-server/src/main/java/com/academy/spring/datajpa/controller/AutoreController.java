package com.academy.spring.datajpa.controller;

import com.academy.spring.datajpa.model.Autore;
import com.academy.spring.datajpa.repository.AutoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/authors")
public class AutoreController {
    @Autowired
    AutoreRepository autoreRepository;

    @GetMapping
    public ResponseEntity<List<Autore>> getAllAuthors(@RequestParam(required = false) String cognome) {
        try {
            List<Autore> autori = new ArrayList<>();
            if (cognome == null) {
                autoreRepository.findAll().forEach(autori::add);
            } else {
                autoreRepository.findByCognome(cognome).forEach(autori::add);
            }
            if (autori.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(autori, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autore> updateAutore(@RequestBody Autore newAutore, @PathVariable("id") Long id) {
        Optional<Autore> autoreData = autoreRepository.findById(id);
        if(autoreData.isPresent()) {
            Autore a = autoreData.get();
            a.setNome(newAutore.getNome());
            a.setCognome(newAutore.getCognome());
            a.setDataDiNascita(newAutore.getDataDiNascita());
            return new ResponseEntity<>(autoreRepository.save(a), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping()
    public ResponseEntity<Autore> createAutore(@RequestBody Autore newAutore) {
        try {
            Autore autore = autoreRepository.save(new Autore(newAutore.getNome(), newAutore.getCognome(), newAutore.getDataDiNascita()));
            return new ResponseEntity<>(autore, HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Autore> deleteAutore(@PathVariable("id") Long id) {
        try {
            autoreRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Autore> findById(@PathVariable("id") Long id) {
        Optional<Autore> a = autoreRepository.findById(id);
        if(a.isPresent()) {
            return new ResponseEntity<>(a.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
