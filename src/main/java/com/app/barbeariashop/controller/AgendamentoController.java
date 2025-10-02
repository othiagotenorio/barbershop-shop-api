package com.app.barbeariashop.controller;

import com.app.barbeariashop.enuns.Status;
import com.app.barbeariashop.model.Agendamento;
import com.app.barbeariashop.repository.AgendamentoRepository;
import com.app.barbeariashop.repository.BarbeiroRepository;
import com.app.barbeariashop.repository.ClienteRepository;
import com.app.barbeariashop.repository.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<Agendamento> listarAgendamento() {
        return agendamentoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> buscarAgendamento(@PathVariable Long id) {
        return agendamentoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agendamento> atualizarAgendamento(@PathVariable Long id, @RequestBody Agendamento agendamentoAtualizado) {

        return agendamentoRepository.findById(id)
                .map(agendamento -> {
                    agendamento.setCliente(agendamentoAtualizado.getCliente());
                    agendamento.setBarbeiro(agendamentoAtualizado.getBarbeiro());
                    agendamento.setServico(agendamentoAtualizado.getServico());
                    agendamento.setDataHora(agendamentoAtualizado.getDataHora());
                    return ResponseEntity.ok(agendamentoRepository.save(agendamento));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAgendamento(@PathVariable Long id) {

        return agendamentoRepository.findById(id)
                .map(agendamento -> {
                    agendamentoRepository.delete(agendamento);
                    return ResponseEntity.noContent().<Void>build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{clienteId}")
    public List<Agendamento> listarPorCliente(@PathVariable Long clienteId) {
        return agendamentoRepository.findByClienteId(clienteId);
    }

    @GetMapping("/barbeiro/{barbeiroId}")
    public List<Agendamento> listarPorBarbeiro(@PathVariable Long barbeiroId) {
        return agendamentoRepository.findByBarbeiroId(barbeiroId);
    }

}
