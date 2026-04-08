package com.alanzzera.agendador.services;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.alanzzera.agendador.controller.dto.ProfissionalRequest;
import com.alanzzera.agendador.controller.dto.ProfissionalResponse;
import com.alanzzera.agendador.exceptions.BusinessException;
import com.alanzzera.agendador.exceptions.NotFoundException;
import com.alanzzera.agendador.infrastructure.entity.Profissional;
import com.alanzzera.agendador.infrastructure.entity.Servico;
import com.alanzzera.agendador.infrastructure.repository.ProfissionalRepository;
import com.alanzzera.agendador.infrastructure.repository.ServicoRepository;

@Service
@RequiredArgsConstructor
public class ProfissionalService {

    private final ProfissionalRepository profissionalRepository;
    private final ServicoRepository servicoRepository;

    // Criar
    public ProfissionalResponse criar(ProfissionalRequest request) {

        if (profissionalRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("Email já cadastrado");
        }

        Profissional profissional = new Profissional();
        profissional.setNome(request.getNome());
        profissional.setEmail(request.getEmail());
        profissional.setTelefone(request.getTelefone());

        // buscar serviços pelos IDs
        List<Servico> servicos = servicoRepository
            .findAllById(request.getServicoIds());

        if (servicos.size() != request.getServicoIds().size()) {
            throw new BusinessException("Um ou mais serviços não existem");
        }

        profissional.setServicos(servicos);

        return toResponse(profissionalRepository.save(profissional));
    }

    // Listar
    public List<ProfissionalResponse> listar() {
        return profissionalRepository.findAll()
            .stream()
            .map(this::toResponse)
            .toList();
    }

    // Buscar por ID
    public ProfissionalResponse buscarPorId(Long id) {
        Profissional profissional = profissionalRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Profissional não encontrado"));

        return toResponse(profissional);
    }

    // Atualizar
    public ProfissionalResponse atualizar(Long id, ProfissionalRequest request) {
        Profissional profissional = profissionalRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Profissional não encontrado"));

        // Verificar se email já existe em OUTRO profissional
        if (!profissional.getEmail().equals(request.getEmail()) &&
            profissionalRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("Email já cadastrado");
        }

        profissional.setNome(request.getNome());
        profissional.setEmail(request.getEmail());
        profissional.setTelefone(request.getTelefone());

        // buscar serviços pelos IDs
        List<Servico> servicos = servicoRepository
            .findAllById(request.getServicoIds());

        if (servicos.size() != request.getServicoIds().size()) {
            throw new BusinessException("Um ou mais serviços não existem");
        }

        profissional.setServicos(servicos);

        return toResponse(profissionalRepository.save(profissional));
    }

    // Deletar
    public void deletar(Long id) {

        Profissional profissional = profissionalRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Profissional não encontrado"));
        
        profissionalRepository.delete(profissional);
    }

    private ProfissionalResponse toResponse(Profissional profissional) {
        ProfissionalResponse response = new ProfissionalResponse();
        response.setId(profissional.getId());
        response.setNome(profissional.getNome());
        response.setEmail(profissional.getEmail());
        response.setTelefone(profissional.getTelefone());

        List<Long> servicoIds = profissional.getServicos()
            .stream()
            .map(Servico::getId)
            .toList();

        response.setServicoIds(servicoIds);

        return response;
    }
}
