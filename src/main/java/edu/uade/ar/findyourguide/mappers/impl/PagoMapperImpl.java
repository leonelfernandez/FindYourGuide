package edu.uade.ar.findyourguide.mappers.impl;

import edu.uade.ar.findyourguide.mappers.Mapper;
import edu.uade.ar.findyourguide.model.dto.CiudadDTO;
import edu.uade.ar.findyourguide.model.dto.PagoDTO;
import edu.uade.ar.findyourguide.model.entity.CiudadEntity;
import edu.uade.ar.findyourguide.model.entity.PagoEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PagoMapperImpl implements Mapper<PagoEntity, PagoDTO> {

    private final ModelMapper modelMapper;

    public PagoMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PagoDTO mapTo(PagoEntity pagoEntity) {
        return modelMapper.map(pagoEntity, PagoDTO.class);
    }

    @Override
    public PagoEntity mapFrom(PagoDTO pagoDTO) {
        return modelMapper.map(pagoDTO, PagoEntity.class);
    }
}
