package com.agendaeditor.domain.usecase.agenda.getById;

import com.agendaeditor.domain.kernel.command.agenda.AgendaCommand;
import com.agendaeditor.domain.kernel.utils.mappers.AgendaMapper;
import com.agendaeditor.domain.kernel.utils.response.AgendaResponse;
import com.agendaeditor.domain.services.agenda.AgendaService;
import com.agendaeditor.domain.usecase.UseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@AllArgsConstructor
@Slf4j
public class AgendaIdUseCase implements UseCase<AgendaCommand, AgendaResponse> {

    private final AgendaService agendaService;

    @Override
    public AgendaResponse execute(AgendaCommand command) throws JsonProcessingException {
        return agendaService.getAgendaById(command.getId())
                .map(agendaDTO -> {
                    if(agendaDTO != null){
                        return AgendaMapper.buildResponse(true, agendaDTO);
                    }else{
                        return AgendaMapper.buildResponse(false, null);
                    }
                }).block();
    }
}
