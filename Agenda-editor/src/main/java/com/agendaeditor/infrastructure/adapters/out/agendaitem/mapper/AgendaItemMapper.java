package com.agendaeditor.infrastructure.adapters.out.agendaitem.mapper;

import com.agendaeditor.domain.kernel.dto.agenda.AgendaDTO;
import com.agendaeditor.domain.kernel.dto.agendaitem.AgendaItemDTO;
import com.agendaeditor.infrastructure.adapters.out.agenda.entity.Agenda;
import com.agendaeditor.infrastructure.adapters.out.agendaitem.entity.AgendaItem;

public class AgendaItemMapper {

    public static AgendaItemDTO entityToDto(AgendaItem entity){
        return AgendaItemDTO
                .builder()
                .id(entity.getId())
                .phase(entity.getPhase())
                .itemOrder(entity.getItemOrder())
                .content(entity.getContent())
                .agendaDTO(AgendaDTO
                        .builder()
                        .id(entity.getAgenda().getId())
                        .name(entity.getAgenda().getName())
                        .build())
                .objectives(entity.getObjectives())
                .creditable(entity.isCreditable())
                .duration(entity.getDuration())
                .build();
    }

    public static AgendaItem dtoToEntity(AgendaItemDTO dto){
        if (dto == null || dto.getAgendaDTO() == null) {
            throw new IllegalArgumentException("DTO or AgendaDTO cannot be null");
        }
        return AgendaItem
                .builder()
                .phase(dto.getPhase())
                .itemOrder(dto.getItemOrder())
                .content(dto.getContent())
                .agenda(Agenda
                        .builder()
                        .id(dto.getAgendaDTO().getId())
                        .name(dto.getAgendaDTO().getName())
                        .build())
                .objectives(dto.getObjectives())
                .creditable(dto.isCreditable())
                .duration(dto.getDuration())
                .build();
    }
}
