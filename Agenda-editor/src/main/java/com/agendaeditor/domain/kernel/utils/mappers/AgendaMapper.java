package com.agendaeditor.domain.kernel.utils.mappers;

import com.agendaeditor.domain.kernel.command.agenda.AgendaCommand;
import com.agendaeditor.domain.kernel.dto.agenda.AgendaDTO;
import com.agendaeditor.domain.kernel.dto.agendaitem.AgendaItemDTO;
import com.agendaeditor.domain.kernel.utils.response.AgendaListResponse;
import com.agendaeditor.domain.kernel.utils.response.AgendaResponse;

import java.util.List;
import java.util.stream.Collectors;

public class AgendaMapper {

    public static AgendaListResponse createResponseList(List<AgendaDTO> dto){
        return new AgendaListResponse(
                true,
                dto.stream()
                        .map(agendaDTO -> AgendaDTO
                                .builder()
                                .id(agendaDTO.getId())
                                .name(agendaDTO.getName())
                                .listItems(agendaDTO.getListItems().stream()
                                        .map(agendaItemDTO -> AgendaItemDTO
                                                .builder()
                                                .id(agendaItemDTO.getId())
                                                .phase(agendaItemDTO.getPhase())
                                                .itemOrder(agendaItemDTO.getItemOrder())
                                                .objectives(agendaItemDTO.getObjectives())
                                                .content(agendaItemDTO.getContent())
                                                .creditable(agendaItemDTO.isCreditable())
                                                .duration(agendaItemDTO.getDuration())
                                                .build())
                                        .collect(Collectors.toList()))
                                .build())
                        .collect(Collectors.toList()));

    }

    public static AgendaResponse buildResponse(boolean success, AgendaDTO dto){
        return new AgendaResponse(
                success,
                dto
        );
    }

    public static AgendaDTO commandToDto(AgendaCommand command){
        return AgendaDTO
                .builder()
                .name(command.getName())
                .build();
    }
}
