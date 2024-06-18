package edu.uade.ar.findyourguide.mappers.impl;

import edu.uade.ar.findyourguide.mappers.Mapper;
import edu.uade.ar.findyourguide.model.dto.ViajeDTO;
import edu.uade.ar.findyourguide.model.entity.ViajeEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ViajeMapperImpl implements Mapper<ViajeEntity, ViajeDTO> {

    private final ModelMapper modelMapper;

    public ViajeMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public ViajeDTO mapTo(ViajeEntity viajeEntity) {
        return modelMapper.map(viajeEntity, ViajeDTO.class);
    }

    @Override
    public ViajeEntity mapFrom(ViajeDTO viajeDTO) {
        return modelMapper.map(viajeDTO, ViajeEntity.class);
    }

}
