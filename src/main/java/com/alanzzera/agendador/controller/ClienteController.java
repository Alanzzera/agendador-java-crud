package com.alanzzera.agendador.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Clientes", description = "Endpoints para gerenciamento de clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Operation(summary = "Criar cliente", description = "Cadastra um novo cliente no sistema")
    @PostMapping
    public ClienteResponse criar(@RequestBody ClienteRequest request) {
        return clienteService.criar(request);
    }

    @Operation(summary = "Listar clientes", description = "Retorna todos os clientes cadastrados")
    @GetMapping
    public List<ClienteResponse> listar() {
        return clienteService.listar();
    }

    @Operation(summary = "Buscar cliente por ID", description = "Retorna um cliente específico pelo seu ID")
    @GetMapping("/{id}")
    public ClienteResponse buscarPorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id);
    }

    @Operation(summary = "Atualizar cliente", description = "Atualiza os dados de um cliente existente")
    @PutMapping("/{id}")
    public ClienteResponse atualizar(@PathVariable Long id, @RequestBody ClienteRequest request) {
        return clienteService.atualizar(id, request);
    }

    @Operation(summary = "Deletar cliente", description = "Remove um cliente do sistema")
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        clienteService.deletar(id);
    }
}