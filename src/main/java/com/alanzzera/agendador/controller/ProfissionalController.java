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
import com.alanzzera.agendador.controller.dto.ProfissionalRequest;
import com.alanzzera.agendador.controller.dto.ProfissionalResponse;
import com.alanzzera.agendador.services.ProfissionalService;

@RestController
@RequestMapping("/profissionais")
@RequiredArgsConstructor
@Tag(name = "Profissionais", description = "Endpoints para gerenciamento de profissionais e seus serviços")
public class ProfissionalController {

    private final ProfissionalService profissionalService;

    @Operation(summary = "Criar profissional", description = "Cadastra um novo profissional com seus serviços")
    @PostMapping
    public ProfissionalResponse criar(@RequestBody ProfissionalRequest request) {
        return profissionalService.criar(request);
    }

    @Operation(summary = "Listar profissionais", description = "Retorna todos os profissionais cadastrados")
    @GetMapping
    public List<ProfissionalResponse> listar() {
        return profissionalService.listar();
    }

    @Operation(summary = "Buscar profissional por ID", description = "Retorna um profissional específico pelo seu ID")
    @GetMapping("/{id}")
    public ProfissionalResponse buscarPorId(@PathVariable Long id) {
        return profissionalService.buscarPorId(id);
    }

    @Operation(summary = "Atualizar profissional", description = "Atualiza os dados e serviços de um profissional")
    @PutMapping("/{id}")
    public ProfissionalResponse atualizar(@PathVariable Long id, @RequestBody ProfissionalRequest request) {
        return profissionalService.atualizar(id, request);
    }

    @Operation(summary = "Deletar profissional", description = "Remove um profissional do sistema")
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        profissionalService.deletar(id);
    }
}
