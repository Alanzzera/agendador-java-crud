package com.alanzzera.agendador.infrastructure.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.alanzzera.agendador.infrastructure.entity.Agendamento;
import com.alanzzera.agendador.infrastructure.entity.enums.AgendamentoStatus;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findByProfissionalId_IdAndDataHoraAgendamentoBetween(
        Long profissionalId,
        LocalDateTime inicio,
        LocalDateTime fim
    );
    
    List<Agendamento> findByProfissionalId_IdAndDataHoraAgendamentoLessThanAndStatusIn(
        Long profissionalId,
        LocalDateTime fim,
        List<AgendamentoStatus> status
    );    

    List<Agendamento> findByProfissionalId_IdAndDataHoraAgendamentoBetweenAndStatusIn(
        Long profissionalId,
        LocalDateTime inicio,
        LocalDateTime fim,
        List<AgendamentoStatus> status
    );    
}