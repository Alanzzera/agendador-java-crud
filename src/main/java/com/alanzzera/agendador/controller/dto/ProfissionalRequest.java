package com.alanzzera.agendador.controller.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfissionalRequest {

    private String nome;
    private String email;
    private String telefone;

    private List<Long> servicoIds;
}
