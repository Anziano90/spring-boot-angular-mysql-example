package com.academy.spring.datajpa.controller;

import com.academy.spring.datajpa.assembler.StudentAssembler;
import com.academy.spring.datajpa.dto.StudentDto;
import com.academy.spring.datajpa.model.Student;
import com.academy.spring.datajpa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    StudentService studentService;

    StudentAssembler studentAssembler=new StudentAssembler();

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents(@RequestParam(required = false) String cognome){
        try{
            List<StudentDto> studentsDto=new ArrayList<>();
            List<Student> students=studentService.getAllBySurname(cognome);
            if(students.isEmpty()){
                return new ResponseEntity<>(HttpStatus.OK);
            }
            for (Student s:students) {
                studentsDto.add(studentAssembler.convertToDto(s));
            }
            return new ResponseEntity<>(studentsDto,HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentsById(@PathVariable("id") long id){
        StudentDto  studentDto;
        Optional<Student> student=studentService.getStudent(id);
        if(student.isPresent()){
            studentDto=studentAssembler.convertToDto(student.get());
            return  new ResponseEntity<>(studentDto,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto){
        try{
            Student s=studentService.createStudent(studentAssembler.convertToEntity(studentDto));
            studentService.save(s);
            StudentDto dto=studentAssembler.convertToDto(s);
            return new ResponseEntity<>(dto,HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus>  delete(@PathVariable("id")Long id){
        try{
            studentService.deleteStudent(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus>  deleteAll(){
        try{
            studentService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable("id") long id,@RequestBody StudentDto newStudent){
        Optional<Student> student=studentService.getStudent(id);
        if(student.isPresent()){
            Student s=student.get();
            s.setCognome(newStudent.getCognome());
            s.setNome(newStudent.getNome());
            s.setDataDiNascita(newStudent.getDataDiNascita());
            studentService.save(s);
            StudentDto dto=studentAssembler.convertToDto(s);
            return new ResponseEntity<>(dto,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
