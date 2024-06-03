package edu.uade.ar.findyourguide.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PackServicio extends Servicio{

    private List<Servicio> servicios = new ArrayList<>();

    @Override
    public String getServicioContratado() {
        return servicios.stream().map(Servicio::getServicioContratado)
                                .collect(Collectors.joining(", "));
    }

    public void agregarServicio(Servicio servicio) {
        servicios.add(servicio);
    }

    public void eliminarServicio(Servicio servicio) {
        servicios.remove(servicio);
    }

}
