package com.alanzzera.agendador.services;

import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.alanzzera.agendador.controller.dto.AgendamentoRequest;
import com.alanzzera.agendador.controller.dto.AgendamentoResponse;
import com.alanzzera.agendador.exceptions.BusinessException;
import com.alanzzera.agendador.exceptions.NotFoundException;
import com.alanzzera.agendador.infrastructure.entity.Agendamento;
import com.alanzzera.agendador.infrastructure.entity.Cliente;
import com.alanzzera.agendador.infrastructure.entity.Profissional;
import com.alanzzera.agendador.infrastructure.entity.Servico;
import com.alanzzera.agendador.infrastructure.entity.enums.AgendamentoStatus;
import com.alanzzera.agendador.infrastructure.repository.AgendamentoRepository;
import com.alanzzera.agendador.infrastructure.repository.ClienteRepository;
import com.alanzzera.agendador.infrastructure.repository.ProfissionalRepository;
import com.alanzzera.agendador.infrastructure.repository.ServicoRepository;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final ClienteRepository clienteRepository;
    private final ProfissionalRepository profissionalRepository;
    private final ServicoRepository servicoRepository;

    // Criar
    public AgendamentoResponse criar(AgendamentoRequest request) {

        // Buscar entidades
        Cliente cliente = clienteRepository.findById(request.getClienteId())
            .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));

        Profissional profissional = profissionalRepository.findById(request.getProfissionalId())
            .orElseThrow(() -> new NotFoundException("Profissional não encontrado"));

        Servico servico = servicoRepository.findById(request.getServicoId())
            .orElseThrow(() -> new NotFoundException("Serviço não encontrado"));

        // Validar se profissional faz o serviço
        validarServicoDoProfissional(profissional, servico);

        // Calcular intervalo
        LocalDateTime inicio = request.getDataHora();
        LocalDateTime fim = inicio.plusMinutes(servico.getTempoMinutos());

        // Validar conflito
        validarConflito(profissional.getId(), inicio, fim, null);

        // Criar agendamento
        Agendamento agendamento = new Agendamento();
        agendamento.setCliente(cliente);
        agendamento.setProfissional(profissional);
        agendamento.setServico(servico);
        agendamento.setDataHoraAgendamento(inicio);
        agendamento.setStatus(AgendamentoStatus.AGENDADO);

        // Salvar
        return toResponse(agendamentoRepository.save(agendamento));
    }

    // Listar
    public List<AgendamentoResponse> listar() {
        return agendamentoRepository.findAll()
            .stream()
            .map(this::toResponse)
            .toList();
    }

    // Buscar por ID
    public AgendamentoResponse buscarPorId(Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Agendamento não encontrado"));

        return toResponse(agendamento);
    }

    // Atualizar
    public AgendamentoResponse atualizar(Long id, AgendamentoRequest request) {
        // Buscar entidades
        Agendamento agendamento = agendamentoRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Agendamento não encontrado"));

        Cliente cliente = clienteRepository.findById(request.getClienteId())
            .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));

        Profissional profissional = profissionalRepository.findById(request.getProfissionalId())
            .orElseThrow(() -> new NotFoundException("Profissional não encontrado"));

        Servico servico = servicoRepository.findById(request.getServicoId())
            .orElseThrow(() -> new NotFoundException("Serviço não encontrado"));

        // Validar se profissional faz o serviço
        validarServicoDoProfissional(profissional, servico);

        // Calcular intervalo
        LocalDateTime inicio = request.getDataHora();
        LocalDateTime fim = inicio.plusMinutes(servico.getTempoMinutos());

        // Validar conflito
        validarConflito(profissional.getId(), inicio, fim, agendamento.getId());

        // Validar status
        agendamento.getStatus().validarTransicao(request.getStatus());

        // Criar agendamento
        agendamento.setCliente(cliente);
        agendamento.setProfissional(profissional);
        agendamento.setServico(servico);
        agendamento.setDataHoraAgendamento(inicio);
        agendamento.setStatus(request.getStatus());

        // Salvar
        return toResponse(agendamentoRepository.save(agendamento));
    }

    // Deletar
    public void deletar(Long id) {

        Agendamento agendamento = agendamentoRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Agendamento não encontrado"));

        agendamentoRepository.delete(agendamento);
    }

    // Atualiza status
    public void atualizarStatus(Long id, AgendamentoStatus novoStatus) {
        Agendamento agendamento = agendamentoRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Agendamento não encontrado"));

        agendamento.getStatus().validarTransicao(novoStatus);

        agendamento.setStatus(novoStatus);
        agendamentoRepository.save(agendamento);
    }

    private void validarServicoDoProfissional(Profissional profissional, Servico servico) {
        if (!profissional.getServicos().contains(servico)) {
            throw new BusinessException("Profissional não realiza esse serviço");
        }
    }

    private void validarConflito(Long profissionalId, LocalDateTime inicio, LocalDateTime fim, Long agendamentoId) {

        List<AgendamentoStatus> statusValidos = Arrays.stream(AgendamentoStatus.values())
            .filter(AgendamentoStatus::bloqueiaHorario)
            .toList();

        List<Agendamento> agendamentos = agendamentoRepository
            .findByProfissionalId_IdAndDataHoraAgendamentoLessThanAndStatusIn(
                profissionalId,
                fim,
                statusValidos
            );

        for (Agendamento ag : agendamentos) {

            if (agendamentoId != null && ag.getId().equals(agendamentoId)) {
                continue;
            }

            LocalDateTime inicioExistente = ag.getDataHoraAgendamento();
            LocalDateTime fimExistente = inicioExistente
                .plusMinutes(ag.getServico().getTempoMinutos());

            if (inicio.isBefore(fimExistente) && fim.isAfter(inicioExistente)) {
                throw new BusinessException("Horário já ocupado");
            }
        }
    }

    private AgendamentoResponse toResponse(Agendamento agendamento) {
        AgendamentoResponse response = new AgendamentoResponse();
        response.setId(agendamento.getId());
        response.setClienteId(agendamento.getCliente().getId());
        response.setProfissionalId(agendamento.getProfissional().getId());
        response.setServicoId(agendamento.getServico().getId());
        response.setDataHoraAgendamento(agendamento.getDataHoraAgendamento());
        response.setDataCadastro(agendamento.getDataCadastro());
        response.setStatus(agendamento.getStatus());
        return response;
    }
}