package edu.uade.ar.findyourguide.service.impl;

import edu.uade.ar.findyourguide.model.adapters.IAdapterChat;
import edu.uade.ar.findyourguide.model.entity.GuiaEntity;
import edu.uade.ar.findyourguide.model.entity.TuristaEntity;
import edu.uade.ar.findyourguide.service.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements IChatService {

    @Autowired
    IAdapterChat adapterChat;

    public ChatServiceImpl(IAdapterChat adapterChat) {
        this.adapterChat = adapterChat;
    }

    @Override
    public Boolean iniciarChat(GuiaEntity guia, TuristaEntity turista) {
        return adapterChat.iniciarChat(guia.getNombre(), turista.getNombre());
    }
}
