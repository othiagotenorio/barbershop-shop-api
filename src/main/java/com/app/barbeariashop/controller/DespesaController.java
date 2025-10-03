package com.app.barbeariashop.controller;

import com.app.barbeariashop.model.Despesa;
import com.app.barbeariashop.repository.DespesaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/despesas")
public class DespesaController {

    private final DespesaRepository despesaRepository;

    @PostMapping("/criar")
    public ResponseEntity<Despesa> criarDespesa(@RequestBody Despesa despesa) {
        Despesa salva = despesaRepository.save(despesa);
        return ResponseEntity.ok(salva);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Despesa>> listarDespesas() {
        return ResponseEntity.ok(despesaRepository.findAll());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Despesa> buscarPorId(@PathVariable Long id) {
        return despesaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Despesa> atualizarDespesa(@PathVariable Long id, @RequestBody Despesa despesa) {
        return despesaRepository.findById(id)
                .map(despesaAtualizada -> {
                    despesaAtualizada.setTipo(despesa.getTipo());
                    despesaAtualizada.setValor(despesa.getValor());
                    despesaAtualizada.setData(despesa.getData());
                    Despesa despesaAtual = despesaRepository.save(despesaAtualizada);
                    return ResponseEntity.ok(despesaAtual);
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Void> deletarDespesa(@PathVariable Long id) {
        if (!despesaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        despesaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
