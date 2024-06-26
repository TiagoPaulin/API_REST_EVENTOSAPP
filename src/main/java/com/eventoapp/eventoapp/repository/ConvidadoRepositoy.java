package com.eventoapp.eventoapp.repository;

import com.eventoapp.eventoapp.models.Convidado;
import com.eventoapp.eventoapp.models.Evento;
import org.springframework.data.repository.CrudRepository;

public interface ConvidadoRepositoy extends CrudRepository<Convidado, String> {

    Iterable<Convidado> findByEvento(Evento evento);

    Convidado findByRg(String rg);
}
