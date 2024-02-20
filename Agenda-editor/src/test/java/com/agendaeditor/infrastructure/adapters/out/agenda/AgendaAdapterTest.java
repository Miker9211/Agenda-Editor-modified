package com.agendaeditor.infrastructure.adapters.out.agenda;

import com.agendaeditor.domain.kernel.dto.agenda.AgendaDTO;
import com.agendaeditor.infrastructure.adapters.out.agenda.entity.Agenda;
import com.agendaeditor.infrastructure.adapters.out.agenda.mapper.AgendaMapper;
import com.agendaeditor.infrastructure.adapters.out.agenda.repository.AgendaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class AgendaAdapterTest {

    @Mock
    private AgendaRepository agendaRepository;

    @InjectMocks
    private AgendaAdapter agendaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListAllAgenda() {
        // Arrange
        List<Agenda> agendaList = new ArrayList<>();
        agendaList.add(new Agenda());
        agendaList.add(new Agenda());
        when(agendaRepository.findAll()).thenReturn(agendaList);

        // Act
        Mono<List<AgendaDTO>> result = agendaAdapter.listAllAgenda();

        // Assert
        List<AgendaDTO> agendaDTOList = result.block();
        assertNotNull(agendaDTOList);
        assertEquals(2, agendaDTOList.size());
    }

    @Test
    void testGetAgendaById() {
        Long id = 1L;
        Agenda agenda = new Agenda();
        agenda.setId(id);
        when(agendaRepository.findById(id)).thenReturn(Optional.of(agenda));

        Mono<AgendaDTO> result = agendaAdapter.getAgendaById(id);

        assertNotNull(result);
        assertEquals(id, result.block().getId());
    }

    @Test
    void testCreateAgenda() {
        AgendaDTO agendaDTO = new AgendaDTO();
        agendaDTO.setName("Test agenda");
        Agenda agenda = AgendaMapper.mapToEntity(agendaDTO);
        when(agendaRepository.save(agenda)).thenReturn(agenda);

        Mono<AgendaDTO> result = agendaAdapter.create(agendaDTO);

        assertNotNull(result);
        assertEquals(agendaDTO, result.block());
    }

    @Test
    void testUpdateAgenda() {
        Long id = 1L;
        AgendaDTO agendaDTO = new AgendaDTO();
        agendaDTO.setName("New Agenda");
        Agenda agenda = new Agenda();
        agenda.setId(id);
        when(agendaRepository.findById(id)).thenReturn(Optional.of(agenda));
        when(agendaRepository.save(agenda)).thenReturn(agenda);

        Mono<AgendaDTO> result = agendaAdapter.update(id, agendaDTO);

        assertNotNull(result);
        assertEquals("New agenda", result.block().getName());
    }

    @Test
    void testDeleteAgenda() {
        Long id = 1L;
        Agenda agenda = new Agenda();
        agenda.setId(id);
        when(agendaRepository.findById(id)).thenReturn(Optional.of(agenda));
        doNothing().when(agendaRepository).delete(agenda);

        Mono<Boolean> result = agendaAdapter.delete(id);

        assertNotNull(result);
        assertTrue(result.block());
    }


}