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
import com.alanzzera.agendador.controller.dto.ProfissionalRequest;
import com.alanzzera.agendador.controller.dto.ProfissionalResponse;
import com.alanzzera.agendador.services.ProfissionalService;

@RestController
@RequestMapping("/profissionais")
@RequiredArgsConstructor
public class ProfissionalController {

    private final ProfissionalService profissionalService;

    // Criar
    @PostMapping
    public ProfissionalResponse criar(@RequestBody ProfissionalRequest request) {
        return profissionalService.criar(request);
    }

    // Listar
    @GetMapping
    public List<ProfissionalResponse> listar() {
        return profissionalService.listar();
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ProfissionalResponse buscarPorId(@PathVariable Long id) {
        return profissionalService.buscarPorId(id);
    }

    // Atualizar
    @PutMapping("/{id}")
    public ProfissionalResponse atualizar(@PathVariable Long id, @RequestBody ProfissionalRequest request) {
        return profissionalService.atualizar(id, request);
    }

    // Deletar
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        profissionalService.deletar(id);
    }
}
