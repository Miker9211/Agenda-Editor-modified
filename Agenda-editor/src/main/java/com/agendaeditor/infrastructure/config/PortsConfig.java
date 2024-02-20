package com.agendaeditor.infrastructure.config;

import com.agendaeditor.domain.ports.agenda.AgendaPort;
import com.agendaeditor.domain.ports.agendaItem.AgendaItemPort;
import com.agendaeditor.infrastructure.adapters.out.agenda.AgendaAdapter;
import com.agendaeditor.infrastructure.adapters.out.agendaitem.AgendaItemAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PortsConfig {

    @Bean
    public AgendaPort agendaPort(){
        return new AgendaAdapter();
    }

    @Bean

    public AgendaItemPort agendaItemPort(){
        return new AgendaItemAdapter();
    }
}
