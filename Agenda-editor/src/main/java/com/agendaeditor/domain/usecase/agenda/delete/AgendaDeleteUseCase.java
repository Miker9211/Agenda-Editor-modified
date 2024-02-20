package com.agendaeditor.domain.usecase.agenda.delete;

import com.agendaeditor.domain.kernel.command.agenda.AgendaCommand;
import com.agendaeditor.domain.kernel.utils.mappers.AgendaMapper;
import com.agendaeditor.domain.kernel.utils.response.AgendaResponse;
import com.agendaeditor.domain.services.agenda.AgendaService;
import com.agendaeditor.domain.services.agendaitem.AgendaItemService;
import com.agendaeditor.domain.usecase.UseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AgendaDeleteUseCase implements UseCase<AgendaCommand, AgendaResponse> {

    private final AgendaService service;
    @Override
    public AgendaResponse execute(AgendaCommand command) throws JsonProcessingException {
        return service.deleteAgenda(command.getId())
                .map(aBoolean -> AgendaMapper.buildResponse(aBoolean, null))
                .block();
    }
}
