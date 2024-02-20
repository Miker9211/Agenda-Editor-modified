package com.agendaeditor.infrastructure.adapters.in.agenda;

import com.agendaeditor.domain.kernel.utils.response.AgendaListResponse;
import com.agendaeditor.domain.kernel.utils.response.AgendaResponse;
import com.agendaeditor.domain.usecase.agenda.create.AgendaCreateUseCase;
import com.agendaeditor.domain.usecase.agenda.delete.AgendaDeleteUseCase;
import com.agendaeditor.domain.usecase.agenda.getById.AgendaIdUseCase;
import com.agendaeditor.domain.usecase.agenda.listAll.AgendaListUseCase;
import com.agendaeditor.domain.usecase.agenda.update.AgendaUpdateUseCase;
import com.agendaeditor.infrastructure.adapters.in.agenda.request.AgendaRequest;
import com.agendaeditor.infrastructure.adapters.in.mapper.AgendaMappers;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/")
@RequiredArgsConstructor
@Validated
public class AgendaController {

    private final AgendaListUseCase agendaListUseCase;
    private final AgendaIdUseCase agendaIdUseCase;
    private final AgendaCreateUseCase agendaCreateUseCase;
    private final AgendaUpdateUseCase agendaUpdateUseCase;
    private final AgendaDeleteUseCase agendaDeleteUseCase;

    @GetMapping("/list-all-agendas")
    public ResponseEntity<AgendaListResponse> listAll() {
        try {
            AgendaListResponse agendaListResponse = agendaListUseCase.execute(AgendaMappers.buildCommand(null, null));
            return ResponseEntity.ok().body(agendaListResponse);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/get-agenda-by-id")
    public ResponseEntity<AgendaResponse> getById(@RequestParam(name = "id") Long id){
        try {
            AgendaResponse agendaResponse = agendaIdUseCase.execute(AgendaMappers.buildCommand(id, null));
            return ResponseEntity.ok().body(agendaResponse);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/create-agenda")
    public ResponseEntity<AgendaResponse> create(@RequestBody @Valid AgendaRequest request){
        try{
            AgendaResponse agendaResponse = agendaCreateUseCase.execute(AgendaMappers.buildCommand(null, request.getName()));
            return ResponseEntity.ok().body(agendaResponse);
        }catch (JsonProcessingException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/update-agenda")
    public ResponseEntity<AgendaResponse> update(@RequestParam(name = "id") Long id, @RequestBody @Valid AgendaRequest request){
        try{
            AgendaResponse response = agendaUpdateUseCase.execute(AgendaMappers.buildCommand(id, request.getName()));
            return ResponseEntity.ok().body(response);
        }catch (JsonProcessingException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete-agenda/{id}")
    public ResponseEntity<AgendaResponse> delete(@PathVariable Long id){
        try{
            AgendaResponse response = agendaDeleteUseCase.execute(AgendaMappers.buildCommand(id, null));
            return ResponseEntity.ok().body(response);
        }catch (JsonProcessingException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
