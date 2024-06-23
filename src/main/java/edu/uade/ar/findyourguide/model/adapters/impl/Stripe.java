package edu.uade.ar.findyourguide.model.adapters.impl;

import edu.uade.ar.findyourguide.model.adapters.IPagoAdapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Data
public class Stripe implements IPagoAdapter {

    @Override
    public Boolean realizarPago(Float montoAPagar) {
        return Boolean.TRUE;
    }
}
