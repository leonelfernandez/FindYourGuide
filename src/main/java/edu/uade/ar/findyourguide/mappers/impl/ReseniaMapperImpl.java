package edu.uade.ar.findyourguide.mappers.impl;

import edu.uade.ar.findyourguide.mappers.Mapper;
import edu.uade.ar.findyourguide.model.dto.ReseniaDTO;
import edu.uade.ar.findyourguide.model.entity.ReseniaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ReseniaMapperImpl implements Mapper<ReseniaEntity, ReseniaDTO> {

    private final ModelMapper modelMapper;

    public ReseniaMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ReseniaDTO mapTo(ReseniaEntity reseniaEntity) {
        return modelMapper.map(reseniaEntity, ReseniaDTO.class);
    }

    @Override
    public ReseniaEntity mapFrom(ReseniaDTO reseniaDTO) {
        return modelMapper.map(reseniaDTO, ReseniaEntity.class);
    }
}
