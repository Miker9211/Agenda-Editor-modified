package com.agendaeditor.domain.kernel.utils.response;

import com.agendaeditor.domain.kernel.dto.agendaitem.AgendaItemDTO;

import java.util.List;

public class AgendaItemResponse extends MessageResponse<AgendaItemDTO> {

    public AgendaItemResponse(boolean success, AgendaItemDTO item) {
        super(success, item);
    }
}
