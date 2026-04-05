package com.alanzzera.agendador.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteResponse {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
}