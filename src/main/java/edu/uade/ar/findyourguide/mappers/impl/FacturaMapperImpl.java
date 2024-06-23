package edu.uade.ar.findyourguide.mappers.impl;

import edu.uade.ar.findyourguide.mappers.Mapper;
import edu.uade.ar.findyourguide.model.dto.FacturaDTO;
import edu.uade.ar.findyourguide.model.entity.FacturaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class FacturaMapperImpl implements Mapper<FacturaEntity, FacturaDTO> {

    private final ModelMapper modelMapper;

    public FacturaMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public FacturaDTO mapTo(FacturaEntity facturaEntity) {
        return modelMapper.map(facturaEntity, FacturaDTO.class);
    }

    @Override
    public FacturaEntity mapFrom(FacturaDTO facturaDTO) {
        return modelMapper.map(facturaDTO, FacturaEntity.class);
    }
}
