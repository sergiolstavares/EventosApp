package com.eventoapp.eventoapp.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Data
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @NotEmpty
    @Column
    private String nome;
    @NotEmpty
    @Column
    private String local;
    @NotEmpty
    @Column
    private String data;

    @NotEmpty
    @Column
    private String horario;

    @OneToMany
    private List<Convidado> convidados;
}
