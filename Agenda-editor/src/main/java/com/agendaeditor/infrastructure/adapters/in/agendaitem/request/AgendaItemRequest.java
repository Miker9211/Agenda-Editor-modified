package com.agendaeditor.infrastructure.adapters.in.agendaitem.request;

import com.agendaeditor.infrastructure.adapters.in.agenda.request.AgendaRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class AgendaItemRequest {

    @NotNull
    private int itemOrder;

    @NotBlank
    @Size(min = 1, max = 30, message = "Phase length must be between 1 and 30 characters")
    private String phase;

    private String content;

    private String objectives;

    @NotNull
    private Long duration;

    private boolean creditable;

    private AgendaRequest agenda;
}
