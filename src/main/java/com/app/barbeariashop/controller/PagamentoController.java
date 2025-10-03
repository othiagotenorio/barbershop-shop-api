package com.app.barbeariashop.controller;

import com.app.barbeariashop.model.Pagamento;
import com.app.barbeariashop.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    private final PagamentoRepository pagamentoRepository;

    @GetMapping("/listar")
    public ResponseEntity<List<Pagamento>> listarPagamentos() {
        return ResponseEntity.ok(pagamentoRepository.findAll());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Pagamento> cadastrarPagamento(@RequestBody Pagamento pagamento) {
        return ResponseEntity.ok(pagamentoRepository.save(pagamento));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Pagamento> buscarPagamento(@PathVariable Long id) {
        return pagamentoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletarPagamento(@PathVariable Long id) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));

        pagamentoRepository.delete(pagamento);
        return ResponseEntity.noContent().build();
    }





}
