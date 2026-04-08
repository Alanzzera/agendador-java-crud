package com.alanzzera.agendador.services;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.alanzzera.agendador.controller.dto.ServicoRequest;
import com.alanzzera.agendador.controller.dto.ServicoResponse;
import com.alanzzera.agendador.exceptions.business.BusinessException;
import com.alanzzera.agendador.exceptions.business.NotFoundException;
import com.alanzzera.agendador.infrastructure.entity.Servico;
import com.alanzzera.agendador.infrastructure.repository.ServicoRepository;

@Service
@RequiredArgsConstructor
public class ServicoService {

    private final ServicoRepository servicoRepository;

    // Criar
    public ServicoResponse criar(ServicoRequest request) {
        
        if(servicoRepository.existsByNome(request.getNome())) {
            throw new BusinessException("Serviço já cadastrado");
        }
        Servico servico = new Servico();
        servico.setNome(request.getNome());
        servico.setValor(request.getValor());
        servico.setTempoMinutos(request.getTempoMinutos());
        servico.setDescricao(request.getDescricao());

        return toResponse(servicoRepository.save(servico));
    }


    // Listar
    public List<ServicoResponse> listar() {
        return servicoRepository.findAll()
            .stream()
            .map(this::toResponse)
            .toList();
    }

    // Buscar por ID
    public ServicoResponse buscarPorId(Long id) {
        Servico servico = servicoRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Serviço não encontrado"));
        
            return toResponse(servico);
    }

    // Atualizar
    public ServicoResponse atualizar(Long id, ServicoRequest request) {
        Servico servico = servicoRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Serviço não encontrado"));

        // Verificar se nome já existe em OUTRO serviço
        if (!servico.getNome().equals(request.getNome()) &&
            servicoRepository.existsByNome(request.getNome())) {
            throw new BusinessException("Serviço já cadastrado");
        }

        servico.setNome(request.getNome());
        servico.setValor(request.getValor());
        servico.setTempoMinutos(request.getTempoMinutos());
        servico.setDescricao(request.getDescricao());

        return toResponse(servicoRepository.save(servico));
    }

    // Deletar
    public void deletar(Long id) {

        Servico servico = servicoRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Serviço não encontrado"));
        
        servicoRepository.delete(servico);
    }

    // Mapper (Entity -> DTO)
    private ServicoResponse toResponse(Servico servico) {
        ServicoResponse response = new ServicoResponse();
        response.setId(servico.getId());
        response.setNome(servico.getNome());
        response.setValor(servico.getValor());
        response.setTempoMinutos(servico.getTempoMinutos());
        response.setDescricao(servico.getDescricao());
        return response;
    }
}
