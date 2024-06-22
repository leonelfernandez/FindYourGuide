package edu.uade.ar.findyourguide.mappers.impl;

import edu.uade.ar.findyourguide.mappers.Mapper;
import edu.uade.ar.findyourguide.model.dto.GuiaDTO;
import edu.uade.ar.findyourguide.model.entity.GuiaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GuiaMapperImpl implements Mapper<GuiaEntity, GuiaDTO> {

    private final ModelMapper modelMapper;

    public GuiaMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public GuiaDTO mapTo(GuiaEntity guiaEntity) {
        return modelMapper.map(guiaEntity, GuiaDTO.class);
    }

    @Override
    public GuiaEntity mapFrom(GuiaDTO guiaDTO) {
        return modelMapper.map(guiaDTO, GuiaEntity.class);
    }
}
