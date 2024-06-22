package edu.uade.ar.findyourguide.model.adapters.impl;

import edu.uade.ar.findyourguide.model.adapters.IAdapterChat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Data
public class Sendbird implements IAdapterChat {

    @Override
    public Boolean iniciarChat(String guia, String turista) {
        return Boolean.TRUE;
    }
}
