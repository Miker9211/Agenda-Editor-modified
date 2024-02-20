package com.agendaeditor.domain.ports.agenda;

import com.agendaeditor.domain.kernel.dto.agenda.AgendaDTO;
import com.agendaeditor.domain.kernel.utils.response.MessageResponse;
import reactor.core.publisher.Mono;

import java.util.List;

public interface AgendaPort {

    Mono<List<AgendaDTO>> listAllAgenda();

    Mono<AgendaDTO> getAgendaById(Long id);

    Mono<AgendaDTO> create(AgendaDTO agendaDTO);

    Mono<AgendaDTO> update(Long id,AgendaDTO agendaDTO);

    Mono<Boolean> delete(Long id);
}
