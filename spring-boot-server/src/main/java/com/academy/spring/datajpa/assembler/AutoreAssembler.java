package com.academy.spring.datajpa.assembler;

import com.academy.spring.datajpa.dto.AutoreDto;
import com.academy.spring.datajpa.model.Autore;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class AutoreAssembler {

    @Autowired
    ModelMapper modelMapper;

    public AutoreDto convertToDto(Autore autore) {
        AutoreDto result = modelMapper.map(autore, AutoreDto.class);
        return result;
    }

    public Autore convertToEntity(AutoreDto a){
        Autore result = modelMapper.map(a, Autore.class);
        return result;
    }
}
