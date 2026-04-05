package com.alanzzera.agendador.controller;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import com.alanzzera.agendador.controller.dto.ClienteRequest;
import com.alanzzera.agendador.controller.dto.ClienteResponse;
import com.alanzzera.agendador.services.ClienteService;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    // Criar
    @PostMapping
    public ClienteResponse criar(@RequestBody ClienteRequest request) {
        return clienteService.criar(request);
    }

    // Listar
    @GetMapping
    public List<ClienteResponse> listar() {
        return clienteService.listar();
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ClienteResponse buscarPorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id);
    }

    // Atualizar
    @PutMapping("/{id}")
    public ClienteResponse atualizar(@PathVariable Long id, @RequestBody ClienteRequest request) {
        return clienteService.atualizar(id, request);
    }

    // Deletar
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        clienteService.deletar(id);
    }
}