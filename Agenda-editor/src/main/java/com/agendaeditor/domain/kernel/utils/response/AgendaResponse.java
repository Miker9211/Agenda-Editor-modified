package com.agendaeditor.domain.kernel.utils.response;

import com.agendaeditor.domain.kernel.dto.agenda.AgendaDTO;

public class AgendaResponse extends MessageResponse<AgendaDTO>{

    public AgendaResponse(boolean success, AgendaDTO agendaDTO){
        super(success, agendaDTO);
    }
}
