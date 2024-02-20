package com.agendaeditor.domain.ports.agendaItem;

import com.agendaeditor.domain.kernel.dto.agendaitem.AgendaItemDTO;
import reactor.core.publisher.Mono;

public interface AgendaItemPort {

    Mono<AgendaItemDTO> create(AgendaItemDTO dto);

    Mono<AgendaItemDTO> update(Long id, AgendaItemDTO dto);

    Mono<Boolean> delete(Long id);
}
