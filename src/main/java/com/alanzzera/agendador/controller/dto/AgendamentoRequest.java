package com.alanzzera.agendador.controller.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgendamentoRequest {

    private Long clienteId;
    private Long profissionalId;
    private Long servicoId;
    private LocalDateTime dataHora;
}