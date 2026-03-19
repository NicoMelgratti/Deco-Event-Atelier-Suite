package com.melgratti.eventos.repository;

import com.melgratti.eventos.entidades.Configuracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfiguracionRepository extends JpaRepository<Configuracion, String> {
    // No necesitás escribir nada acá, JpaRepository ya trae todo.
}