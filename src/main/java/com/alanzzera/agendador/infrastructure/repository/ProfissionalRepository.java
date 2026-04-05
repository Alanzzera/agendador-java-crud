package com.alanzzera.agendador.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alanzzera.agendador.infrastructure.entity.Profissional;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long>{

}
