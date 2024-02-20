package com.agendaeditor.infrastructure.adapters.out.agenda.entity;

import com.agendaeditor.infrastructure.adapters.out.agendaitem.entity.AgendaItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "agenda")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
public class Agenda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "agenda", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("agenda")
    private List<AgendaItem> agendaItemList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    //In Case agendaItemList return a null list, this line of code should return a empty a list to avoid NullPointerException
    public List<AgendaItem> getAgendaItemList() {
        return Objects.requireNonNullElseGet(this.agendaItemList, ArrayList::new);
    }
}
