package com.agendaeditor.domain.usecase.agenda.create;

import com.agendaeditor.domain.kernel.command.agenda.AgendaCommand;
import com.agendaeditor.domain.kernel.dto.agenda.AgendaDTO;
import com.agendaeditor.domain.kernel.utils.mappers.AgendaMapper;
import com.agendaeditor.domain.kernel.utils.response.AgendaResponse;
import com.agendaeditor.domain.services.agenda.AgendaService;
import com.agendaeditor.domain.usecase.UseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AgendaCreateUseCase implements UseCase<AgendaCommand, AgendaResponse> {

    private final AgendaService agendaService;
    @Override
    public AgendaResponse execute(AgendaCommand command) throws JsonProcessingException {
        return agendaService.createAgenda(buildDto(command))
                .map(agendaDTO -> {
                    if(agendaDTO != null){
                        return AgendaMapper.buildResponse(true, agendaDTO);
                    }else{
                        return AgendaMapper.buildResponse(false, null);
                    }
                }).block();
    }

    private AgendaDTO buildDto(AgendaCommand command){
        return AgendaMapper.commandToDto(command);
    }
}
