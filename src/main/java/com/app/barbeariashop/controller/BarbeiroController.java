package com.app.barbeariashop.controller;

import com.app.barbeariashop.model.Barbeiro;
import com.app.barbeariashop.repository.BarbeiroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/barbeiros")
public class BarbeiroController {

    private final BarbeiroRepository barbeiroRepository;

    @PostMapping
    public ResponseEntity<Barbeiro> criarBarbeiro(@RequestBody Barbeiro barbeiro) {
        Barbeiro salvo = barbeiroRepository.save(barbeiro);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping
    public ResponseEntity<List<Barbeiro>> listarBarbeiro() {
        return ResponseEntity.ok(barbeiroRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Barbeiro> buscarPorId(@PathVariable Long id) {
        return barbeiroRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Barbeiro> atualizarBarbeiro(@PathVariable Long id, @RequestBody Barbeiro barbeiro) {
        return barbeiroRepository.findById(id)
                .map(b -> {
                    b.setNome(barbeiro.getNome());
                    b.setEspecialidade(barbeiro.getEspecialidade());
                    b.setDataAdmissao(barbeiro.getDataAdmissao());
                    Barbeiro atualizado = barbeiroRepository.save(b);
                    return ResponseEntity.ok(atualizado);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarBarbeiro(@PathVariable Long id) {
        if (!barbeiroRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        barbeiroRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
