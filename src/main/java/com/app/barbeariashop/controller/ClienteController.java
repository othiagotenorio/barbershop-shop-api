package com.app.barbeariashop.controller;

import com.app.barbeariashop.model.Cliente;
import com.app.barbeariashop.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    @PostMapping
    public Cliente criarCliente(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @GetMapping
    public List<Cliente> listarCliente() {
        return clienteRepository.findAll();
    }

    public ResponseEntity<Cliente> buscarCliente(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteAtualizado) {
        return clienteRepository.findById(id)
                .map(cliente -> {
                    cliente.setNome(clienteAtualizado.getNome());
                    cliente.setTelefone(clienteAtualizado.getTelefone());
                    return ResponseEntity.ok(clienteRepository.save(cliente));
                }).orElse(ResponseEntity.notFound().build());
    }

}
