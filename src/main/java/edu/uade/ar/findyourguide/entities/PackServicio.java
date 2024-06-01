package edu.uade.ar.findyourguide.entities;
import java.util.ArrayList;
import java.util.List;


public class PackServicio extends ServicioAgregado {
    private List<ServicioAgregado> servicios = new ArrayList<ServicioAgregado>();

    public void agregarServicio(ServicioAgregado servicio) {
        servicios.add(servicio);
    }
    public void eliminarServicio(ServicioAgregado servicio){
        servicios.remove(servicio);
    }
}
