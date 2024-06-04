package edu.uade.ar.findyourguide.mappers.impl;

import edu.uade.ar.findyourguide.mappers.Mapper;
import edu.uade.ar.findyourguide.model.dto.UsuarioDTO;
import edu.uade.ar.findyourguide.model.entity.UsuarioEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapperImpl implements Mapper<UsuarioEntity, UsuarioDTO> {

    private final ModelMapper modelMapper;

    public UsuarioMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public UsuarioDTO mapTo(UsuarioEntity usuarioEntity) {
        return modelMapper.map(usuarioEntity, UsuarioDTO.class);
    }

    @Override
    public UsuarioEntity mapFrom(UsuarioDTO usuarioDTO) {
        return modelMapper.map(usuarioDTO, UsuarioEntity.class);
    }
}
