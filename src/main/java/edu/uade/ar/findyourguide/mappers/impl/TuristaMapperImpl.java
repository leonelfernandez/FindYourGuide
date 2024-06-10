package edu.uade.ar.findyourguide.mappers.impl;

import edu.uade.ar.findyourguide.mappers.Mapper;
import edu.uade.ar.findyourguide.model.dto.GuiaDTO;
import edu.uade.ar.findyourguide.model.dto.TuristaDTO;
import edu.uade.ar.findyourguide.model.entity.GuiaEntity;
import edu.uade.ar.findyourguide.model.entity.TuristaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TuristaMapperImpl implements Mapper<TuristaEntity, TuristaDTO> {
    private final ModelMapper modelMapper;


    public TuristaMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public TuristaDTO mapTo(TuristaEntity turistaEntity) {
        return modelMapper.map(turistaEntity, TuristaDTO.class);
    }

    @Override
    public TuristaEntity mapFrom(TuristaDTO turistaDTO) {
        return modelMapper.map(turistaDTO, TuristaEntity.class);
    }



}
