package com.agendaeditor.infrastructure.adapters.out.agenda.mapper;

import com.agendaeditor.domain.kernel.dto.agenda.AgendaDTO;
import com.agendaeditor.domain.kernel.dto.agendaitem.AgendaItemDTO;
import com.agendaeditor.infrastructure.adapters.out.agenda.entity.Agenda;

import java.util.stream.Collectors;

public class AgendaMapper {

    public static AgendaDTO mapToAgendaDto(Agenda agenda){
        return AgendaDTO
                .builder()
                .id(agenda.getId())
                .name(agenda.getName())
                .listItems(agenda.getAgendaItemList().stream()
                        .map(entity -> AgendaItemDTO
                                .builder()
                                .id(entity.getId())
                                .phase(entity.getPhase())
                                .itemOrder(entity.getItemOrder())
                                .objectives(entity.getObjectives())
                                .content(entity.getContent())
                                .creditable(entity.isCreditable())
                                .duration(entity.getDuration())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    public static Agenda mapToEntity(AgendaDTO agendaDTO){
        return Agenda
                .builder()
                .name(agendaDTO.getName())
                .build();
    }
}
