package com.alanzzera.agendador.infrastructure.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.alanzzera.agendador.infrastructure.entity.Agendamento;
import com.alanzzera.agendador.infrastructure.entity.enums.AgendamentoStatus;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findByProfissional_IdAndDataHoraAgendamentoBetween(
        Long profissionalId,
        LocalDateTime inicio,
        LocalDateTime fim
    );
    
    List<Agendamento> findByProfissional_IdAndDataHoraAgendamentoLessThanAndStatusIn(
        Long profissionalId,
        LocalDateTime fim,
        List<AgendamentoStatus> status
    );    

    List<Agendamento> findByProfissional_IdAndDataHoraAgendamentoBetweenAndStatusIn(
        Long profissionalId,
        LocalDateTime inicio,
        LocalDateTime fim,
        List<AgendamentoStatus> status
    );    
}