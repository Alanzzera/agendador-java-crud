package com.alanzzera.agendador.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alanzzera.agendador.infrastructure.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
