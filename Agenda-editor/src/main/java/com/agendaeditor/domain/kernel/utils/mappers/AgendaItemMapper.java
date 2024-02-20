package com.agendaeditor.domain.kernel.utils.mappers;

import com.agendaeditor.domain.kernel.command.agendaitem.AgendaItemCommand;
import com.agendaeditor.domain.kernel.dto.agenda.AgendaDTO;
import com.agendaeditor.domain.kernel.dto.agendaitem.AgendaItemDTO;

public class AgendaItemMapper {

    public static AgendaItemDTO commandToDto(AgendaItemCommand command){
        return AgendaItemDTO
                .builder()
                .phase(command.getPhase())
                .itemOrder(command.getItemOrder())
                .content(command.getContent())
                .objectives(command.getObjectives())
                .creditable(command.isCreditable())
                .duration(command.getDuration())
                .agendaDTO(AgendaDTO
                        .builder()
                        .id(command.getAgenda().getId())
                        .name(command.getAgenda().getName())
                        .build())
                .build();
    }
}
