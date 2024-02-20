package com.agendaeditor.infrastructure.config;

import com.agendaeditor.domain.ports.agenda.AgendaPort;
import com.agendaeditor.domain.ports.agendaItem.AgendaItemPort;
import com.agendaeditor.domain.services.agenda.AgendaService;
import com.agendaeditor.domain.services.agendaitem.AgendaItemService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public AgendaService agendaService(final AgendaPort agendaPort){
        return new AgendaService(agendaPort);
    }

    @Bean
    public AgendaItemService agendaItemService(final AgendaItemPort agendaItemPort){
        return new AgendaItemService(agendaItemPort);
    }
}
