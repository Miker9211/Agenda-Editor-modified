package com.agendaeditor.infrastructure.config;


import com.agendaeditor.infrastructure.adapters.out.agenda.entity.Agenda;
import com.agendaeditor.infrastructure.adapters.out.agenda.repository.AgendaRepository;
import com.agendaeditor.infrastructure.adapters.out.agendaitem.entity.AgendaItem;
import com.agendaeditor.infrastructure.adapters.out.agendaitem.repository.AgendaItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseLoader implements CommandLineRunner {

    private final AgendaRepository agendaRepository;

    private final AgendaItemRepository agendaItemRepository;

    @Autowired
    public DatabaseLoader(AgendaRepository agendaRepository, AgendaItemRepository agendaItemRepository) {
        this.agendaRepository = agendaRepository;
        this.agendaItemRepository = agendaItemRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        createAgendaWithItem(1);
        createAgendaWithItem(2);
        createAgendaWithItem(3);
        createAgendaWithItem(4);
        createAgendaWithItem(5);
    }

    private void createAgendaWithItem(int count) {
        Agenda agenda = Agenda.builder().name("Agenda " + count).build();
        System.out.println(agenda);
        AgendaItem item = AgendaItem.builder().itemOrder(1).phase("Welcome").content("some content").objectives("Some objectives").duration(15l).creditable(false).agenda(agenda).build();
        agendaRepository.save(agenda);
        agendaItemRepository.save(item);
    }
}
