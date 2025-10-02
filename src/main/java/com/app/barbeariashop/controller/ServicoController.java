package com.app.barbeariashop.controller;

import com.app.barbeariashop.model.Servico;
import com.app.barbeariashop.repository.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/servicos")
public class ServicoController {

    private final ServicoRepository servicoRepository;

    @PostMapping
    public ResponseEntity<Servico> criarServico(@RequestBody Servico servico) {
        Servico salvo = servicoRepository.save(servico);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping
    public ResponseEntity<List<Servico>> listarServicos() {
        return ResponseEntity.ok(servicoRepository.findAll());
    }



}
