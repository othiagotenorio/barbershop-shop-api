package com.app.barbeariashop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @OneToOne
    @Column(name = "AGENDAMENTO")
    private Agendamento agendamento;

    @Column(name = "FORMAPAGAMENTO")
    private String formaPagamento;

    @Column(name = "VALOR")
    private Double valor;

    @Column(name = "DATA")
    private LocalDateTime data;

}
