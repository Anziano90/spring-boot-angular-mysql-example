package com.academy.spring.datajpa.controller;

import com.academy.spring.datajpa.assembler.AutoreAssembler;
import com.academy.spring.datajpa.assembler.TutorialAssembler;
import com.academy.spring.datajpa.dto.AutoreDto;
import com.academy.spring.datajpa.dto.TutorialDto;
import com.academy.spring.datajpa.model.Autore;
import com.academy.spring.datajpa.model.Tutorial;
import com.academy.spring.datajpa.service.AutoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/authors")
public class AutoreController {
    @Autowired
    AutoreService autoreService;

    AutoreAssembler autoreAssembler=new AutoreAssembler();

    TutorialAssembler tutorialAssembler=new TutorialAssembler();

    @GetMapping
    public ResponseEntity<List<AutoreDto>> getAllAuthors(@RequestParam(required = false) String cognome) {
        try {
            List<AutoreDto> autoriDto = new ArrayList<>();
            List<Autore> autoriEntity = autoreService.getAllBySurname(cognome);
            //autoriEntity.stream().map(this.autoreAssembler::convertToDto).collect(Collectors.toList());

            for (Autore a: autoriEntity) {
                AutoreDto tmp = autoreAssembler.convertToDto(a);
                autoriDto.add(tmp);
            }

            if (autoriDto.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(autoriDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Transient
    @PutMapping("/{id}")
    public ResponseEntity<AutoreDto> updateAutore(@RequestBody AutoreDto newAutore, @PathVariable("id") Long id) {

        Optional<Autore> autoreData = autoreService.getAutore(id);
        if (autoreData.isPresent()) {
            Autore a = autoreData.get();
            a.setNome(newAutore.getNome());
            a.setCognome(newAutore.getCognome());
            a.setDataDiNascita(newAutore.getDataDiNascita());
            AutoreDto autore=autoreAssembler.convertToDto(a);
            autoreService.save(a);
            return new ResponseEntity<>(autore, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping()
    public ResponseEntity<AutoreDto> createAutore(@RequestBody AutoreDto newAutoreDto) {
        try {
            Autore autore=autoreAssembler.convertToEntity(newAutoreDto);
            autoreService.createAutore(autore);
            autoreService.save(autore);
            return new ResponseEntity<>(newAutoreDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAutore(@PathVariable("id") Long id) {
        try {
            autoreService.deleteAutore(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutoreDto> findById(@PathVariable("id") Long id) {
        Optional<Autore> a = autoreService.getAutore(id);
        AutoreDto autoreDto=autoreAssembler.convertToDto(a.get());
        if (a.isPresent()) {
            return new ResponseEntity<>(autoreDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{datebefore}")
    public ResponseEntity<List<AutoreDto>> findByDateBefore(@PathVariable("datebefore") Date date) {
        try {
            List<AutoreDto> autoriDto=new ArrayList<>();
            List<Autore> autori = autoreService.getAutoreBornBefore(date);
            if (autori.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            for(Autore a:autori){
                autoriDto.add(autoreAssembler.convertToDto(a));
            }
            return new ResponseEntity<>(autoriDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{dateafter}")
    public ResponseEntity<List<AutoreDto>> findByDateAfter(@PathVariable("dateafter") Date date) {
        try {
            List<AutoreDto> autoriDto=new ArrayList<>();
            List<Autore> autori = autoreService.getAutoreBornAfter(date);

            if (autori.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            for(Autore a:autori){
                autoriDto.add(autoreAssembler.convertToDto(a));
            }
            return new ResponseEntity<>(autoriDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tutorial/{id}")
    public ResponseEntity<List<TutorialDto>> getAllTutorialByAuthor(@RequestParam(required = true) Long id) {
        try {
            List<TutorialDto> tutorialsDto=new ArrayList<>();
            List<Tutorial> tutorials = autoreService.getAllTutorialsByAuthor(id);
            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            for(Tutorial a:tutorials){
                tutorialsDto.add(tutorialAssembler.convertToDto(a));
            }
            return new ResponseEntity<>(tutorialsDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/allordered")
//    public ResponseEntity<List<Autore>> findAllByCognomeNomeOrdered() {
//        try {
//            List<Autore> result = autoreService.getAllAutoriOrderedByCognomeNome();
//            if(result.isEmpty()){
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(result, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
