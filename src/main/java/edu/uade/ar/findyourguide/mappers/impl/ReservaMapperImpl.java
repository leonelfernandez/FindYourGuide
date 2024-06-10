package edu.uade.ar.findyourguide.mappers.impl;

import edu.uade.ar.findyourguide.mappers.Mapper;
import edu.uade.ar.findyourguide.model.dto.ReservaDTO;
import edu.uade.ar.findyourguide.model.entity.ReservaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ReservaMapperImpl implements Mapper<ReservaEntity, ReservaDTO> {

    private final ModelMapper modelMapper;

    public ReservaMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ReservaDTO mapTo(ReservaEntity reservaEntity) {
        return modelMapper.map(reservaEntity, ReservaDTO.class);
    }

    @Override
    public ReservaEntity mapFrom(ReservaDTO reservaDTO) {
        return modelMapper.map(reservaDTO, ReservaEntity.class);
    }
}
