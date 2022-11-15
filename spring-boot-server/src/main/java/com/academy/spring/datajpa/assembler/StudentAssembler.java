package com.academy.spring.datajpa.assembler;

import com.academy.spring.datajpa.dto.StudentDto;
import com.academy.spring.datajpa.model.Student;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentAssembler {
    public StudentAssembler(){
        this.modelMapper.getConfiguration().setPreferNestedProperties(false);
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    ModelMapper modelMapper=new ModelMapper();

    public StudentDto convertToDto(Student student) {
        StudentDto result = modelMapper.map(student, StudentDto.class);
        return result;
    }

    public Student convertToEntity(StudentDto a){
        Student result = modelMapper.map(a, Student.class);
        return result;
    }
}
