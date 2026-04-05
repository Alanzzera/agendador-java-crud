package com.alanzzera.agendador.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alanzzera.agendador.infrastructure.entity.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long> {

}
