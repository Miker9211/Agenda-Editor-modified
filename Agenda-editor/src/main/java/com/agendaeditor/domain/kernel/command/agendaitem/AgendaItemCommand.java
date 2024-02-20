package com.agendaeditor.domain.kernel.command.agendaitem;

import com.agendaeditor.domain.kernel.command.Command;
import com.agendaeditor.domain.kernel.command.agenda.AgendaCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class AgendaItemCommand implements Command {

    private Long id;

    private int itemOrder;

    private String phase;

    private String content;

    private String objectives;

    private Long duration;

    private boolean creditable;

    private AgendaCommand agenda;
}
