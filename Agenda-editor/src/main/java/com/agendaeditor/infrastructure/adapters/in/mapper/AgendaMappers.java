package com.agendaeditor.infrastructure.adapters.in.mapper;

import com.agendaeditor.domain.kernel.command.agenda.AgendaCommand;

public class AgendaMappers {

    public static AgendaCommand buildCommand(Long id, String name){
        return AgendaCommand
                .builder()
                .id(id)
                .name(name)
                .build();
    }
}
