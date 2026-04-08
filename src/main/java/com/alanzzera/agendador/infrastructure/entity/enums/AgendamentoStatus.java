package com.alanzzera.agendador.infrastructure.entity.enums;

import com.alanzzera.agendador.exceptions.business.BusinessException;

public enum AgendamentoStatus {

    AGENDADO,
    CONFIRMADO,
    CANCELADO,
    FINALIZADO;

    public void validarTransicao(AgendamentoStatus novo) {

        // estados finais
        if (this == CANCELADO || this == FINALIZADO) {
            throw new BusinessException(
                "Não é possível alterar um agendamento " + this
            );
        }

        // regras de fluxo
        if (!podeIrPara(novo)) {
            throw new BusinessException(
                "Transição inválida de " + this + " para " + novo
            );
        }
    }

    private boolean podeIrPara(AgendamentoStatus novo) {
        return switch (this) {
            case AGENDADO -> novo == CONFIRMADO || novo == FINALIZADO || novo == CANCELADO;
            case CONFIRMADO -> novo == FINALIZADO || novo == CANCELADO;
            default -> false;
        };
    }

    public boolean bloqueiaHorario() {
        return this == AGENDADO || this == CONFIRMADO;
    }
}