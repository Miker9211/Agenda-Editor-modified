package com.agendaeditor.infrastructure.adapters.out.agenda;

import com.agendaeditor.domain.kernel.dto.agenda.AgendaDTO;
import com.agendaeditor.domain.ports.agenda.AgendaPort;
import com.agendaeditor.infrastructure.adapters.out.agenda.entity.Agenda;
import com.agendaeditor.infrastructure.adapters.out.agenda.mapper.AgendaMapper;
import com.agendaeditor.infrastructure.adapters.out.agenda.repository.AgendaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@RequiredArgsConstructor
@Slf4j
public class AgendaAdapter implements AgendaPort {

    @Autowired
    private AgendaRepository agendaRepository;


    @Override
    public Mono<List<AgendaDTO>> listAllAgenda() {
        return Mono.just(StreamSupport.stream(agendaRepository.findAll().spliterator(), false)
                .map(AgendaMapper::mapToAgendaDto)
                .collect(Collectors.toList()))
                .onErrorResume(throwable -> {
                    log.error("Error getting agendaList");
                    return Mono.just(Collections.emptyList());
                });

    }

    @Override
    public Mono<AgendaDTO> getAgendaById(Long id) {
        return Mono.justOrEmpty(agendaRepository.findById(id))
                .map(AgendaMapper::mapToAgendaDto)
                .switchIfEmpty(Mono.error(new ChangeSetPersister.NotFoundException()));
    }


    @Transactional
    @Override
    public Mono<AgendaDTO> create(AgendaDTO agendaDTO) {
        return Mono.just(agendaRepository.save(AgendaMapper.mapToEntity(agendaDTO)))
                .map(AgendaMapper::mapToAgendaDto)
                .onErrorResume(throwable -> {
                    log.error("Error creating agenda : {}", throwable.getMessage());
                    return Mono.error(new Exception("Error creating agenda", throwable));
                });
    }

    @Transactional
    @Override
    public Mono<AgendaDTO> update(Long id,AgendaDTO agendaDTO) {
        return Mono.fromSupplier(() -> {
                    Optional<Agenda> optionalAgenda = agendaRepository.findById(id);
                    if (optionalAgenda.isPresent()) {
                        Agenda agenda = optionalAgenda.get();
                        agenda.setName(agendaDTO.getName());

                        return agendaRepository.save(agenda);
                    } else {
                        throw new EntityNotFoundException("Agenda not found with ID: " + id);
                    }
                })
                .map(AgendaMapper::mapToAgendaDto)
                .onErrorResume(throwable -> {
                    log.error("Error updating  agenda: {}", throwable.getMessage());
                    return Mono.error(new Exception("Error updating  agenda", throwable));
                });
    }


    @Transactional
    @Override
    public Mono<Boolean> delete(Long id) {
        return Mono.justOrEmpty(agendaRepository.findById(id))
                .flatMap(agenda -> {
                    agendaRepository.delete(agenda);
                    return Mono.just(true);
                })
                .switchIfEmpty(Mono.error(new EntityNotFoundException("Agenda not found")))
                .onErrorResume(throwable -> {
                    log.error("Error deleting agenda with id : {}", id);
                    return Mono.just(false);
                });
    }
}
