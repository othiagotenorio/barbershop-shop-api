package com.app.barbeariashop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @Column(name = "CLIENTE")
    private Cliente cliente;

    @ManyToOne
    @Column(name = "BARBEIRO")
    private Barbeiro barbeiro;

    @ManyToOne
    @Column(name = "SERVICO")
    private Servico servico;

    @Column(name = "DATAHORA")
    private LocalDateTime dataHora;

    @Column(name = "STATUS")
    private String status; //PENDENTE, CONCLUÍDO, CANCELADO
}
