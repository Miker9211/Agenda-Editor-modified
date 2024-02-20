package com.agendaeditor.infrastructure.adapters.out.agendaitem.repository;

import com.agendaeditor.infrastructure.adapters.out.agendaitem.entity.AgendaItem;
import org.springframework.data.repository.CrudRepository;

public interface AgendaItemRepository extends CrudRepository<AgendaItem, Long> {
}
