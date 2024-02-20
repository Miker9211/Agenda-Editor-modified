package com.agendaeditor.infrastructure.adapters.in.agenda.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
@Getter
@AllArgsConstructor
public class AgendaRequest {

    private Long id;

    @NotBlank
    private String name;
}
