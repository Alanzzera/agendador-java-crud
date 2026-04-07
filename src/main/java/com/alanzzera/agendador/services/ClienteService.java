package com.alanzzera.agendador.services;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.alanzzera.agendador.controller.dto.ClienteRequest;
import com.alanzzera.agendador.controller.dto.ClienteResponse;
import com.alanzzera.agendador.exceptions.BusinessException;
import com.alanzzera.agendador.exceptions.NotFoundException;
import com.alanzzera.agendador.infrastructure.entity.Cliente;
import com.alanzzera.agendador.infrastructure.repository.ClienteRepository;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    // Criar
    public ClienteResponse criar(ClienteRequest request) {

        if (clienteRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("Email já cadastrado");
        }

        Cliente cliente = new Cliente();
        cliente.setNome(request.getNome());
        cliente.setEmail(request.getEmail());
        cliente.setTelefone(request.getTelefone());

        return toResponse(clienteRepository.save(cliente));
    }

    // Listar
    public List<ClienteResponse> listar() {
        return clienteRepository.findAll()
            .stream()
            .map(this::toResponse)
            .toList();
    }

    // Buscar por ID
    public ClienteResponse buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));

        return toResponse(cliente);
    }

    // Atualizar
    public ClienteResponse atualizar(Long id, ClienteRequest request) {
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));

        cliente.setNome(request.getNome());
        cliente.setEmail(request.getEmail());
        cliente.setTelefone(request.getTelefone());

        return toResponse(clienteRepository.save(cliente));
    }

    // Deletar
    public void deletar(Long id) {

        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));

        clienteRepository.delete(cliente);
    }

    private ClienteResponse toResponse(Cliente cliente) {
        ClienteResponse response = new ClienteResponse();
        response.setId(cliente.getId());
        response.setNome(cliente.getNome());
        response.setEmail(cliente.getEmail());
        response.setTelefone(cliente.getTelefone());
        return response;
    }
}