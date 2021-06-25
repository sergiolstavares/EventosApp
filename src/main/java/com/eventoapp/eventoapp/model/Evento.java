package com.eventoapp.eventoapp.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column
    private String nome;
    @Column
    private String local;
    @Column
    private String data;
    @Column
    private String horario;
}
