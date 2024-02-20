package com.agendaeditor.domain.kernel.command.agenda;

import com.agendaeditor.domain.kernel.command.Command;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Builder
@AllArgsConstructor
@Getter
public class AgendaCommand implements Command {

    private Long id;
    private String name;
}
