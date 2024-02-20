package com.agendaeditor.domain.kernel.dto.agenda;

import com.agendaeditor.domain.kernel.dto.agendaitem.AgendaItemDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class AgendaDTO {
    private Long id;
    private String name;
    private List<AgendaItemDTO> listItems;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<AgendaItemDTO> getListItems() {
        return Objects.requireNonNullElseGet(this.listItems, ArrayList::new);
    }
}
