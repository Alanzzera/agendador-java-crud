package com.alanzzera.agendador.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alanzzera.agendador.controller.dto.AgendamentoRequest;
import com.alanzzera.agendador.controller.dto.AgendamentoResponse;
import com.alanzzera.agendador.controller.dto.StatusDTO;
import com.alanzzera.agendador.services.AgendamentoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/agendamentos")
@RequiredArgsConstructor
@Tag(name = "Agendamentos", description = "Endpoints para gerenciamento de agendamentos com validação de conflitos de horário")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @Operation(summary = "Criar agendamento", description = "Agenda um serviço verificando disponibilidade do profissional")
    @PostMapping
    public AgendamentoResponse criar(@RequestBody AgendamentoRequest request) {
        return agendamentoService.criar(request);
    }

    @Operation(summary = "Listar agendamentos", description = "Retorna todos os agendamentos cadastrados")
    @GetMapping
    public List<AgendamentoResponse> listar() {
        return agendamentoService.listar();
    }

    @Operation(summary = "Buscar agendamento por ID", description = "Retorna um agendamento específico pelo seu ID")
    @GetMapping("/{id}")
    public AgendamentoResponse buscarPorId(@PathVariable Long id) {
        return agendamentoService.buscarPorId(id);
    }

    @Operation(summary = "Atualizar agendamento", description = "Atualiza dados do agendamento verificando conflitos de horário")
    @PutMapping("/{id}")
    public AgendamentoResponse atualizar(@PathVariable Long id, @RequestBody AgendamentoRequest request) {
        return agendamentoService.atualizar(id, request);
    }

    @Operation(summary = "Deletar agendamento", description = "Remove um agendamento do sistema")
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        agendamentoService.deletar(id);
    }

    @Operation(summary = "Atualizar status", description = "Altera o status do agendamento (AGENDADO, CONFIRMADO, CANCELADO, FINALIZADO)")
    @PatchMapping("/{id}/status")
    public void atualizarStatus(@PathVariable Long id, @RequestBody StatusDTO dto) {
        agendamentoService.atualizarStatus(id, dto.getStatus());
    }
}
