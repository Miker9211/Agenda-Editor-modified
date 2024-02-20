package com.agendaeditor.domain.kernel.utils.response;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponseList<T> {

    private boolean success;
    private List<T> item;

    public boolean isSuccess() {
        return success;
    }

    public List<T> getItem() {
        return Objects.requireNonNullElseGet(this.item, ArrayList::new);
    }
}
