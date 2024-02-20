package com.agendaeditor.infrastructure.config;

import com.agendaeditor.domain.services.agenda.AgendaService;
import com.agendaeditor.domain.services.agendaitem.AgendaItemService;
import com.agendaeditor.domain.usecase.agenda.create.AgendaCreateUseCase;
import com.agendaeditor.domain.usecase.agenda.delete.AgendaDeleteUseCase;
import com.agendaeditor.domain.usecase.agenda.getById.AgendaIdUseCase;
import com.agendaeditor.domain.usecase.agenda.listAll.AgendaListUseCase;
import com.agendaeditor.domain.usecase.agenda.update.AgendaUpdateUseCase;
import com.agendaeditor.domain.usecase.agendaitem.create.AgendaItemCreateUseCase;
import com.agendaeditor.domain.usecase.agendaitem.deleteById.AgendaItemDeleteUseCase;
import com.agendaeditor.domain.usecase.agendaitem.update.AgendaItemUpdateUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public AgendaListUseCase agendaUseCase(AgendaService service){
        return new AgendaListUseCase(service);
    }

    @Bean
    public AgendaIdUseCase agendaIdUseCase(AgendaService service){
        return new AgendaIdUseCase(service);
    }

    @Bean
    public AgendaCreateUseCase agendaCreate(AgendaService service){
        return new AgendaCreateUseCase(service);
    }

    @Bean
    public AgendaUpdateUseCase agendaUpdate(AgendaService service){
        return new AgendaUpdateUseCase(service);
    }

    @Bean
    public AgendaDeleteUseCase agendaDelete(AgendaService service){
        return new AgendaDeleteUseCase(service);
    }

    @Bean
    public AgendaItemCreateUseCase agendaItemCreateUseCase(AgendaItemService service){return  new AgendaItemCreateUseCase(service);}

    @Bean
    public AgendaItemUpdateUseCase agendaItemUpdateUseCase(AgendaItemService service){
        return new AgendaItemUpdateUseCase(service);
    }

    @Bean
    public AgendaItemDeleteUseCase agendaDeleteUseCase(AgendaItemService service){
        return new AgendaItemDeleteUseCase(service);
    }

}
