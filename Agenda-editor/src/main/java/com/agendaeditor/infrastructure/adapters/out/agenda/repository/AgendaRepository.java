package com.agendaeditor.infrastructure.adapters.out.agenda.repository;

import com.agendaeditor.infrastructure.adapters.out.agenda.entity.Agenda;
import org.springframework.data.repository.CrudRepository;

public interface AgendaRepository extends CrudRepository<Agenda, Long> {
}
