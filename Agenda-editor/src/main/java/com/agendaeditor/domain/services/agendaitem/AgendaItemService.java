package com.agendaeditor.domain.services.agendaitem;

import com.agendaeditor.domain.kernel.dto.agendaitem.AgendaItemDTO;
import com.agendaeditor.domain.ports.agendaItem.AgendaItemPort;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class AgendaItemService {

    private final AgendaItemPort port;

    public Mono<AgendaItemDTO> createAgendasItem(AgendaItemDTO dto){
        return port.create(dto);
    }

    public Mono<AgendaItemDTO> updateAgendaItem(Long id, AgendaItemDTO dto){
        return port.update(id, dto);
    }

    public Mono<Boolean> deleteAgendaItemById(Long id){
        return port.delete(id);
    }
}
