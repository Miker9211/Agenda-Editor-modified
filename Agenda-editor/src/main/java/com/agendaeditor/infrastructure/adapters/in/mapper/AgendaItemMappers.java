package com.agendaeditor.infrastructure.adapters.in.mapper;

import com.agendaeditor.domain.kernel.command.agenda.AgendaCommand;
import com.agendaeditor.domain.kernel.command.agendaitem.AgendaItemCommand;
import com.agendaeditor.infrastructure.adapters.in.agendaitem.request.AgendaItemRequest;

public class AgendaItemMappers {

    public static AgendaItemCommand requestToCommand(AgendaItemRequest request){
        return AgendaItemCommand
                .builder()
                .itemOrder(request.getItemOrder())
                .phase(request.getPhase())
                .objectives(request.getObjectives())
                .content(request.getContent())
                .creditable(request.isCreditable())
                .duration(request.getDuration())
                .agenda(AgendaCommand
                        .builder()
                        .id(request.getAgenda().getId())
                        .name(request.getAgenda().getName())
                        .build())
                .build();
    }

    public static AgendaItemCommand requestToCommand(Long id, AgendaItemRequest request){
        return AgendaItemCommand
                .builder()
                .id(id)
                .itemOrder(request.getItemOrder())
                .phase(request.getPhase())
                .objectives(request.getObjectives())
                .content(request.getContent())
                .agenda(AgendaCommand
                        .builder()
                        .id(request.getAgenda().getId())
                        .build())
                .creditable(request.isCreditable())
                .duration(request.getDuration())
                .build();
    }

    public static AgendaItemCommand requestToCommand(Long id){
        return AgendaItemCommand
                .builder()
                .id(id)
                .build();
    }
}
