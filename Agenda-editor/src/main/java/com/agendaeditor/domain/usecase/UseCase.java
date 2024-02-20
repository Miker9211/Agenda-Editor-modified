package com.agendaeditor.domain.usecase;

import com.agendaeditor.domain.kernel.command.Command;
import com.fasterxml.jackson.core.JsonProcessingException;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface UseCase<T extends Command, R> {
    R execute(T command) throws JsonProcessingException;
}
