package com.agendaeditor.infrastructure.adapters.in.agendaitem;

import com.agendaeditor.domain.kernel.utils.response.AgendaItemResponse;
import com.agendaeditor.domain.usecase.agendaitem.create.AgendaItemCreateUseCase;
import com.agendaeditor.domain.usecase.agendaitem.deleteById.AgendaItemDeleteUseCase;
import com.agendaeditor.domain.usecase.agendaitem.update.AgendaItemUpdateUseCase;
import com.agendaeditor.infrastructure.adapters.in.agendaitem.request.AgendaItemRequest;
import com.agendaeditor.infrastructure.adapters.in.mapper.AgendaItemMappers;
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
public class AgendaItemController {

    private final AgendaItemCreateUseCase agendaItemCreateUseCase;
    private final AgendaItemUpdateUseCase agendaItemUpdateUseCase;
    private final AgendaItemDeleteUseCase agendaItemDeleteUseCase;

    @PostMapping("/create-agenda-item")
    public ResponseEntity<AgendaItemResponse> create(@RequestBody @Valid AgendaItemRequest request){
        try{
            AgendaItemResponse response = agendaItemCreateUseCase.execute(AgendaItemMappers.requestToCommand(request));
            return ResponseEntity.ok().body(response);
        }catch (JsonProcessingException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/update-agenda-item")
    public ResponseEntity<AgendaItemResponse> update(@RequestParam(name = "id") Long id, @RequestBody @Valid AgendaItemRequest request){
        try{
            AgendaItemResponse response = agendaItemUpdateUseCase.execute(AgendaItemMappers.requestToCommand(id, request));
            return ResponseEntity.ok().body(response);
        }catch (JsonProcessingException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete-agenda-item/{id}")
    public ResponseEntity<AgendaItemResponse> delete(@PathVariable Long id){
        try{
            AgendaItemResponse response = agendaItemDeleteUseCase.execute(AgendaItemMappers.requestToCommand(id));
            return ResponseEntity.ok().body(response);
        }catch (JsonProcessingException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
