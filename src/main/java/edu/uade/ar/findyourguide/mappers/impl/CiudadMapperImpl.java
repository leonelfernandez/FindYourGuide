package edu.uade.ar.findyourguide.mappers.impl;

import edu.uade.ar.findyourguide.mappers.Mapper;
import edu.uade.ar.findyourguide.model.dto.CiudadDTO;
import edu.uade.ar.findyourguide.model.dto.GuiaDTO;
import edu.uade.ar.findyourguide.model.entity.CiudadEntity;
import edu.uade.ar.findyourguide.model.entity.GuiaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CiudadMapperImpl implements Mapper<CiudadEntity, CiudadDTO> {
    private final ModelMapper modelMapper;

    public CiudadMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CiudadDTO mapTo(CiudadEntity ciudadEntity) {
        return modelMapper.map(ciudadEntity, CiudadDTO.class);
    }

    @Override
    public CiudadEntity mapFrom(CiudadDTO ciudadDTO) {
        return modelMapper.map(ciudadDTO, CiudadEntity.class);
    }
}
