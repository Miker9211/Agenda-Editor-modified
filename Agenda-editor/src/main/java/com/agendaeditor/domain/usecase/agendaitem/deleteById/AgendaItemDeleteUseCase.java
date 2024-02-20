package com.agendaeditor.domain.usecase.agendaitem.deleteById;

import com.agendaeditor.domain.kernel.command.agendaitem.AgendaItemCommand;
import com.agendaeditor.domain.kernel.utils.response.AgendaItemResponse;
import com.agendaeditor.domain.services.agendaitem.AgendaItemService;
import com.agendaeditor.domain.usecase.UseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AgendaItemDeleteUseCase implements UseCase<AgendaItemCommand, AgendaItemResponse> {

    private final AgendaItemService service;

    @Override
    public AgendaItemResponse execute(AgendaItemCommand command) throws JsonProcessingException {
        return service.deleteAgendaItemById(command.getId())
                .map(aBoolean -> new AgendaItemResponse(aBoolean, null))
                .block();
    }
}
