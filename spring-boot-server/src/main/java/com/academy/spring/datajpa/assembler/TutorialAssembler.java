package com.academy.spring.datajpa.assembler;


import com.academy.spring.datajpa.dto.TutorialDto;
import com.academy.spring.datajpa.model.Tutorial;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;


public class TutorialAssembler {


    public TutorialAssembler(){
        this.modelMapper.getConfiguration().setPreferNestedProperties(false);
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }
    ModelMapper modelMapper=new ModelMapper();

    public TutorialDto convertToDto(Tutorial tutorial) {
        TutorialDto result = modelMapper.map(tutorial, TutorialDto.class);
        return result;
    }

    public Tutorial convertToEntity(TutorialDto a){
        Tutorial result = modelMapper.map(a, Tutorial.class);
        return result;
    }
}
