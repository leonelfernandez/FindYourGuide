package edu.uade.ar.findyourguide.service;

import edu.uade.ar.findyourguide.model.entity.GuiaEntity;
import edu.uade.ar.findyourguide.model.entity.TuristaEntity;

public interface IChatService {

    public Boolean iniciarChat(GuiaEntity guia, TuristaEntity turista);

}
