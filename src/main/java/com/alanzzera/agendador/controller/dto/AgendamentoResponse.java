package com.alanzzera.agendador.controller.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class AgendamentoResponse {

    private Long id;
    private Long clienteId;
    private Long profissionalId;
    private Long servicoId;
    private LocalDateTime dataHoraAgendamento;
    private LocalDateTime dataCadastro;
}