package com.agendaeditor.domain.usecase.agendaitem.update;

import com.agendaeditor.domain.kernel.command.agendaitem.AgendaItemCommand;
import com.agendaeditor.domain.kernel.dto.agendaitem.AgendaItemDTO;
import com.agendaeditor.domain.kernel.utils.mappers.AgendaItemMapper;
import com.agendaeditor.domain.kernel.utils.response.AgendaItemResponse;
import com.agendaeditor.domain.services.agendaitem.AgendaItemService;
import com.agendaeditor.domain.usecase.UseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AgendaItemUpdateUseCase implements UseCase<AgendaItemCommand, AgendaItemResponse> {

    private final AgendaItemService service;

    @Override
    public AgendaItemResponse execute(AgendaItemCommand command) throws JsonProcessingException {
        return service.updateAgendaItem(command.getId(), buildDto(command))
                .map(agendaItemDTO ->
                        agendaItemDTO != null ? new AgendaItemResponse(true, agendaItemDTO) : new AgendaItemResponse(false, null))
                .block();
    }

    private AgendaItemDTO buildDto(AgendaItemCommand command){
        return AgendaItemMapper.commandToDto(command);
    }
}
