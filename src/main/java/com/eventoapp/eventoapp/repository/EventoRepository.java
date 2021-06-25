package com.eventoapp.eventoapp.repository;

import com.eventoapp.eventoapp.model.Evento;
import org.springframework.data.repository.CrudRepository;

public interface EventoRepository extends CrudRepository<Evento,Long> {
    Evento findByCodigo(Long codigo);
}
