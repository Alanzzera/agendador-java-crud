package com.alanzzera.agendador.controller.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServicoRequest {

    private String nome;
    private BigDecimal valor;
    private Integer tempoMinutos;
    private String descricao;
}
