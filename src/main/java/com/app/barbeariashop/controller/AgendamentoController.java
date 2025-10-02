package com.app.barbeariashop.controller;

import com.app.barbeariashop.enuns.Status;
import com.app.barbeariashop.model.Agendamento;
import com.app.barbeariashop.repository.AgendamentoRepository;
import com.app.barbeariashop.repository.BarbeiroRepository;
import com.app.barbeariashop.repository.ClienteRepository;
import com.app.barbeariashop.repository.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final AgendamentoRepository agendamentoRepository;
    private final ClienteRepository clienteRepository;
    private final BarbeiroRepository barbeiroRepository;
    private final ServicoRepository servicoRepository;

    @PostMapping
    public ResponseEntity<Agendamento> criarAgendamento(@RequestBody Agendamento agendamento) {

        //verificar cliente , barbeiro e serviço!!
        if (!clienteRepository.existsById(agendamento.getCliente().getId())) {
            return ResponseEntity.badRequest().build();
        }

        if (!barbeiroRepository.existsById(agendamento.getBarbeiro().getId())) {
            return ResponseEntity.badRequest().build();
        }

        if (!servicoRepository.existsById(agendamento.getServico().getId())) {
            return ResponseEntity.badRequest().build();
        }

        if (agendamento.getStatus() == null) {
            agendamento.setStatus(Status.PENDENTE);
        }

        return ResponseEntity.ok(agendamentoRepository.save(agendamento));
    }


}
