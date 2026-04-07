package com.alanzzera.agendador.controller.dto;

import com.alanzzera.agendador.infrastructure.entity.enums.AgendamentoStatus;

public class StatusDTO {
    private AgendamentoStatus status;

    public AgendamentoStatus getStatus() {
        return status;
    }

    public void setStatus(AgendamentoStatus status) {
        this.status = status;
    }
}
