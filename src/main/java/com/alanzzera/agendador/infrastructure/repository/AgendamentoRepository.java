package com.alanzzera.agendador.infrastructure.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.alanzzera.agendador.infrastructure.entity.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findByProfissionalId_IdAndDataHoraAgendamentoBetween(
        Long profissionalId,
        LocalDateTime inicio,
        LocalDateTime fim
    );
}