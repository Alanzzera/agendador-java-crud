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
import com.alanzzera.agendador.controller.dto.AgendamentoRequest;
import com.alanzzera.agendador.controller.dto.AgendamentoResponse;
import com.alanzzera.agendador.services.AgendamentoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/agendamento")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    // Criar
    @PostMapping
    public AgendamentoResponse criar(@RequestBody AgendamentoRequest request) {
        return agendamentoService.criar(request);
    }

    // Listar
    @GetMapping
    public List<AgendamentoResponse> listar() {
        return agendamentoService.listar();
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public AgendamentoResponse buscarPorId(@PathVariable Long id) {
        return agendamentoService.buscarPorId(id);
    }

    // Atualizar
    @PutMapping("/{id}")
    public AgendamentoResponse atualizar(@PathVariable Long id, @RequestBody AgendamentoRequest request) {
        return agendamentoService.atualizar(id, request);
    }

    // Deletar
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        agendamentoService.deletar(id);
    }
}
