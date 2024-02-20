package com.agendaeditor.domain.kernel.dto.agendaitem;

import com.agendaeditor.domain.kernel.dto.agenda.AgendaDTO;
import com.agendaeditor.infrastructure.adapters.out.agenda.entity.Agenda;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AgendaItemDTO {

    private Long id;

    private int itemOrder;

    private String phase;

    private AgendaDTO agendaDTO;

    private String content;

    private String objectives;

    private Long duration;

    private boolean creditable;

}
