package com.agendaeditor.domain.services.agenda;

import com.agendaeditor.domain.kernel.dto.agenda.AgendaDTO;
import com.agendaeditor.domain.ports.agenda.AgendaPort;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;

@AllArgsConstructor
public class AgendaService {

    private final AgendaPort agendaPort;

    public Mono<List<AgendaDTO>> listAllAgenda(){
        return agendaPort.listAllAgenda();
    }

    public Mono<AgendaDTO> getAgendaById(Long id){
        return agendaPort.getAgendaById(id);
    }

    public Mono<AgendaDTO> createAgenda(AgendaDTO agendaDTO){
        return agendaPort.create(agendaDTO);
    }

    public Mono<AgendaDTO> updateAgenda(Long id, AgendaDTO agendaDTO){
        return agendaPort.update(id,agendaDTO);
    }

    public Mono<Boolean> deleteAgenda(Long id){
        return agendaPort.delete(id);
    }
}
