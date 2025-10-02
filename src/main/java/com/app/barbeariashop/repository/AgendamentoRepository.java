package com.app.barbeariashop.repository;

import com.app.barbeariashop.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findByClienteId(Long clienteId);

    List<Agendamento> findByBarbeiroId(Long barbeiroId);
}
