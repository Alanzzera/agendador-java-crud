package com.alanzzera.agendador.controller.dto;

import com.alanzzera.agendador.infrastructure.entity.enums.AgendamentoStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusDTO {
    private AgendamentoStatus status;
}
