package com.agendaeditor.infrastructure.adapters.out.agendaitem;

import com.agendaeditor.domain.kernel.dto.agenda.AgendaDTO;
import com.agendaeditor.domain.kernel.dto.agendaitem.AgendaItemDTO;
import com.agendaeditor.infrastructure.adapters.out.agenda.entity.Agenda;
import com.agendaeditor.infrastructure.adapters.out.agendaitem.entity.AgendaItem;
import com.agendaeditor.infrastructure.adapters.out.agendaitem.mapper.AgendaItemMapper;
import com.agendaeditor.infrastructure.adapters.out.agendaitem.repository.AgendaItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class AgendaItemAdapterTest {

    @Mock
    private AgendaItemRepository repository;

    @InjectMocks
    private AgendaItemAdapter adapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAgendaItem() {

        AgendaItemDTO dto = new AgendaItemDTO();
        AgendaDTO agendaDTO = new AgendaDTO();
        agendaDTO.setId(1L);
        agendaDTO.setName("Example Agenda");
        dto.setAgendaDTO(agendaDTO);


        Agenda agenda = new Agenda();
        agenda.setId(agendaDTO.getId());
        agenda.setName(agendaDTO.getName());

        AgendaItem agendaItemEntity = new AgendaItem();
        agendaItemEntity.setAgenda(agenda);
        when(repository.save(any(AgendaItem.class))).thenReturn(agendaItemEntity);


        Mono<AgendaItemDTO> result = adapter.create(dto);

        StepVerifier.create(result)
                .expectNextMatches(createdDto -> true)
                .verifyComplete();
        verify(repository, times(1)).save(any(AgendaItem.class));
    }

    @Test
    void testUpdateAgendaItem() {

        Long id = 1L;
        AgendaItemDTO dto = new AgendaItemDTO();
        dto.setPhase("New Phase");
        dto.setItemOrder(2);

        when(repository.findById(id)).thenReturn(Optional.empty());

        Mono<AgendaItemDTO> result = adapter.update(id, dto);

        StepVerifier.create(result)
                .expectError(EntityNotFoundException.class)
                .verify();
        verify(repository, times(1)).findById(id);
        verify(repository, never()).save(any());
    }

    @Test
    void testUpdateAgendaItem_EntityNotFound() {
        Long id = 1L;
        AgendaItemDTO dto = new AgendaItemDTO();
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> adapter.update(id, dto).block());
        verify(repository, times(1)).findById(id);
        verify(repository, never()).save(any(AgendaItem.class));
    }

    @Test
    void testDeleteAgendaItem() {
        Long id = 1L;
        AgendaItem agendaItemEntity = new AgendaItem();
        when(repository.findById(id)).thenReturn(Optional.of(agendaItemEntity));

        Mono<Boolean> result = adapter.delete(id);

        assertNotNull(result);
        assertTrue(result.block());
        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).delete(agendaItemEntity);
    }

    @Test
    void testDeleteAgendaItem_EntityNotFound() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertFalse(adapter.delete(id).block());
        verify(repository, times(1)).findById(id);
        verify(repository, never()).delete(any(AgendaItem.class));
    }
}