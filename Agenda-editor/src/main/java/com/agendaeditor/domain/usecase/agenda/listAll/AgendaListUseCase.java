package com.agendaeditor.domain.usecase.agenda.listAll;

import com.agendaeditor.domain.kernel.command.agenda.AgendaCommand;
import com.agendaeditor.domain.kernel.utils.mappers.AgendaMapper;
import com.agendaeditor.domain.kernel.utils.response.AgendaListResponse;
import com.agendaeditor.domain.services.agenda.AgendaService;
import com.agendaeditor.domain.usecase.UseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class AgendaListUseCase implements UseCase<AgendaCommand, AgendaListResponse> {

    private final AgendaService agendaService;

    @Override
    public AgendaListResponse execute(AgendaCommand command) throws JsonProcessingException {
        return agendaService.listAllAgenda().map(AgendaMapper::createResponseList).block();
    }
}
