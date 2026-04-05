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
import com.alanzzera.agendador.controller.dto.ServicoRequest;
import com.alanzzera.agendador.controller.dto.ServicoResponse;
import com.alanzzera.agendador.services.ServicoService;


@RestController
@RequestMapping("/servico")
@RequiredArgsConstructor
public class ServicoController {

    private final ServicoService servicoService;

    // Criar
    @PostMapping
    public ServicoResponse criar(@RequestBody ServicoRequest request) {
        return servicoService.criar(request);
    }

    // Listar
    @GetMapping()
    public List<ServicoResponse> listar() {
        return servicoService.listar();
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ServicoResponse buscarPorId(@PathVariable Long id) {
        return servicoService.buscarPorId(id);
    }

    // Atualizar
    @PutMapping("/{id}")
    public ServicoResponse atualizar(@PathVariable Long id, @RequestBody ServicoRequest request) {
        return servicoService.atualizar(id, request);
    }

    // Deletar
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        servicoService.deletar(id);
    }
}
