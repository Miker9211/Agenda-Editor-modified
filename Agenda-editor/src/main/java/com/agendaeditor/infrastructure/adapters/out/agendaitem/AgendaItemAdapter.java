package com.agendaeditor.infrastructure.adapters.out.agendaitem;

import com.agendaeditor.domain.kernel.dto.agendaitem.AgendaItemDTO;
import com.agendaeditor.domain.ports.agendaItem.AgendaItemPort;
import com.agendaeditor.infrastructure.adapters.out.agendaitem.entity.AgendaItem;
import com.agendaeditor.infrastructure.adapters.out.agendaitem.mapper.AgendaItemMapper;
import com.agendaeditor.infrastructure.adapters.out.agendaitem.repository.AgendaItemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class AgendaItemAdapter implements AgendaItemPort {

    @Autowired
    private AgendaItemRepository repository;

    @Override
    public Mono<AgendaItemDTO> create(AgendaItemDTO dto) {
        return Mono.just(repository.save(AgendaItemMapper.dtoToEntity(dto)))
                .map(AgendaItemMapper::entityToDto)
                .onErrorResume(throwable -> {
                    log.error("Error creating agenda item : {}", throwable.getMessage());
                    return Mono.error(new Exception("Error creating agenda item", throwable));
                });
    }

    @Override
    public Mono<AgendaItemDTO> update(Long id, AgendaItemDTO dto) {
        return Mono.fromSupplier(() -> {
                    Optional<AgendaItem> optionalAgenda = repository.findById(id);
                    if (optionalAgenda.isPresent()) {
                        AgendaItem agendaItem = optionalAgenda.get();
                        agendaItem.setPhase(dto.getPhase());
                        agendaItem.setItemOrder(dto.getItemOrder());
                        agendaItem.setContent(dto.getContent());
                        agendaItem.setDuration(dto.getDuration());
                        agendaItem.setObjectives(dto.getObjectives());
                        agendaItem.setCreditable(dto.isCreditable());

                        return repository.save(agendaItem);
                    } else {
                        throw new EntityNotFoundException("Agenda item not found with ID: " + id);
                    }
                })
                .map(AgendaItemMapper::entityToDto);

    }

    @Override
    public Mono<Boolean> delete(Long id) {
        return Mono.justOrEmpty(repository.findById(id))
                .flatMap(agenda -> {
                    repository.delete(agenda);
                    return Mono.just(true);
                })
                .switchIfEmpty(Mono.error(new EntityNotFoundException("Agenda not found")))
                .onErrorResume(throwable -> {
                    log.error("Error deleting agenda item with id : {}", id);
                    return Mono.just(false);
                });
    }
}
