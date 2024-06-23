package edu.uade.ar.findyourguide.controller;

import edu.uade.ar.findyourguide.mappers.Mapper;
import edu.uade.ar.findyourguide.model.dto.FacturaDTO;
import edu.uade.ar.findyourguide.model.entity.FacturaEntity;
import edu.uade.ar.findyourguide.service.IFacturaService;
import edu.uade.ar.findyourguide.service.IPagoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FacturaController {

    private IFacturaService facturaService;
    private Mapper<FacturaEntity, FacturaDTO> facturaMapper;

    public FacturaController(IFacturaService facturaService, Mapper<FacturaEntity, FacturaDTO> facturaMapper) {
        this.facturaService = facturaService;
        this.facturaMapper = facturaMapper;
    }

    @PostMapping(path = "/facturas")
    public ResponseEntity<FacturaDTO> crearFactura(@RequestBody FacturaDTO facturaDto) {
        FacturaEntity factura = facturaMapper.mapFrom(facturaDto);
        FacturaEntity facturaGuardada = facturaService.save(factura);
        return new ResponseEntity<>(facturaMapper.mapTo(facturaGuardada), HttpStatus.CREATED);
    }



}
