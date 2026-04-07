package com.alanzzera.agendador.controller.dto;

import java.time.LocalDateTime;
import com.alanzzera.agendador.infrastructure.entity.enums.AgendamentoStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgendamentoResponse {

    private Long id;
    private Long clienteId;
    private Long profissionalId;
    private Long servicoId;
    private LocalDateTime dataHoraAgendamento;
    private LocalDateTime dataCadastro;
    private AgendamentoStatus status;
}