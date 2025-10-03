package com.app.barbeariashop.model;

import com.app.barbeariashop.enuns.MetodoPagamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

import java.math.BigDecimal;
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

    @Enumerated(EnumType.STRING)
    private MetodoPagamento metodo;

    @Column(name = "VALOR")
    private BigDecimal valor;

    @ManyToOne
    private Cliente cliente;

    @Column(name = "DATA")
    private LocalDateTime data;

}
