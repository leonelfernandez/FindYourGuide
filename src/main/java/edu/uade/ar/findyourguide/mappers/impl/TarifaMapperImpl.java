package edu.uade.ar.findyourguide.mappers.impl;

import edu.uade.ar.findyourguide.mappers.Mapper;
import edu.uade.ar.findyourguide.model.dto.TarifaDTO;
import edu.uade.ar.findyourguide.model.entity.TarifaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TarifaMapperImpl implements Mapper<TarifaEntity, TarifaDTO> {

    private final ModelMapper modelMapper;

    public TarifaMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public TarifaDTO mapTo(TarifaEntity tarifaEntity) {
        return modelMapper.map(tarifaEntity, TarifaDTO.class);
    }

    @Override
    public TarifaEntity mapFrom(TarifaDTO tarifaDTO) {
        return modelMapper.map(tarifaDTO, TarifaEntity.class);
    }
}
