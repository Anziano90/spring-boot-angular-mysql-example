package com.academy.spring.datajpa.controller;

import com.academy.spring.datajpa.assembler.StudentAssembler;
import com.academy.spring.datajpa.assembler.TutorialAssembler;
import com.academy.spring.datajpa.dto.StudentDto;
import com.academy.spring.datajpa.dto.TutorialDto;
import com.academy.spring.datajpa.model.Student;
import com.academy.spring.datajpa.model.Tutorial;
import com.academy.spring.datajpa.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/tutorials")
public class TutorialController {

    @Autowired
    TutorialService tutorialService;
    TutorialAssembler tutorialAssembler=new TutorialAssembler();
    StudentAssembler studentAssembler=new StudentAssembler();

    @GetMapping
    public ResponseEntity<List<TutorialDto>> getAllTutorials(@RequestParam(required = false) String title) {
        try {
            List<TutorialDto> tutorialsDto = new ArrayList<>();
            List<Tutorial> tutorials = tutorialService.getAllByTitle(title);
            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            for (Tutorial t : tutorials) {
                tutorialsDto.add(tutorialAssembler.convertToDto(t));
            }
            return new ResponseEntity<>(tutorialsDto, HttpStatus.OK);
        } catch (Exception e) {
            Logger.getLogger("marco").info(e.getLocalizedMessage()+" ");
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TutorialDto> getTutorialById(@PathVariable("id") long id) {
        TutorialDto tutorialData = tutorialAssembler.convertToDto(tutorialService.getTutorial(id).get());

        if (tutorialData != null) {
            return new ResponseEntity<>(tutorialData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<TutorialDto> createTutorial(@RequestBody TutorialDto tutorial) {
        try {
            Tutorial _tutorial = tutorialService.createTutorial(tutorialAssembler.convertToEntity(tutorial));
            return new ResponseEntity<>(tutorial, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TutorialDto> updateTutorial(@PathVariable("id") long id, @RequestBody TutorialDto tutorial) {
        Optional<Tutorial> tutorialData = tutorialService.getTutorial(id);
        if (tutorialData.isPresent()) {
            Tutorial _tutorial = tutorialData.get();
            _tutorial.setTitle(tutorial.getTitle());
            _tutorial.setDescription(tutorial.getDescription());
            _tutorial.setPublished(tutorial.isPublished());
            tutorialService.save(_tutorial);
            TutorialDto dto=tutorialAssembler.convertToDto(_tutorial);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        try {
            tutorialService.deleteTutorial(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        try {
            tutorialService.deleteAllTutorial();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/published")
    public ResponseEntity<List<TutorialDto>> findByPublished() {
        try {
            List<TutorialDto> tutorialsDto = new ArrayList<>();
            List<Tutorial> tutorials = tutorialService.getAllPublished();

            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            for (Tutorial t:tutorials) {
                tutorialsDto.add(tutorialAssembler.convertToDto(t));
            }
            return new ResponseEntity<>(tutorialsDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}/students")
    public ResponseEntity<List<StudentDto>> findAllStudentsByTutorialid(@PathVariable("id") long id) {
        List<StudentDto> studentsDto = new ArrayList<>();
        List<Student> students = tutorialService.findStudentsFollowingTutorial(id);
        if (students == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        for(Student s:students){
        studentsDto.add(studentAssembler.convertToDto(s));
        }
        return new ResponseEntity<>(studentsDto, HttpStatus.OK);
    }

}
