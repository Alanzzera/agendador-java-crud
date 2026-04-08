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
import com.alanzzera.agendador.controller.dto.ServicoRequest;
import com.alanzzera.agendador.controller.dto.ServicoResponse;
import com.alanzzera.agendador.services.ServicoService;

@RestController
@RequestMapping("/servicos")
@RequiredArgsConstructor
@Tag(name = "Serviços", description = "Endpoints para gerenciamento de serviços")
public class ServicoController {

    private final ServicoService servicoService;

    @Operation(summary = "Criar serviço", description = "Cadastra um novo serviço com valor e duração")
    @PostMapping
    public ServicoResponse criar(@RequestBody ServicoRequest request) {
        return servicoService.criar(request);
    }

    @Operation(summary = "Listar serviços", description = "Retorna todos os serviços disponíveis")
    @GetMapping
    public List<ServicoResponse> listar() {
        return servicoService.listar();
    }

    @Operation(summary = "Buscar serviço por ID", description = "Retorna um serviço específico pelo seu ID")
    @GetMapping("/{id}")
    public ServicoResponse buscarPorId(@PathVariable Long id) {
        return servicoService.buscarPorId(id);
    }

    @Operation(summary = "Atualizar serviço", description = "Atualiza os dados de um serviço existente")
    @PutMapping("/{id}")
    public ServicoResponse atualizar(@PathVariable Long id, @RequestBody ServicoRequest request) {
        return servicoService.atualizar(id, request);
    }

    @Operation(summary = "Deletar serviço", description = "Remove um serviço do sistema")
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        servicoService.deletar(id);
    }
}
