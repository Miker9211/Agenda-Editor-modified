package com.agendaeditor.domain.kernel.utils.response;

import com.agendaeditor.domain.kernel.dto.agenda.AgendaDTO;

import java.util.List;

public class AgendaListResponse extends MessageResponseList<AgendaDTO> {

    public AgendaListResponse(boolean success, List<AgendaDTO> agendaList){
        super(success, agendaList);
    }

}
