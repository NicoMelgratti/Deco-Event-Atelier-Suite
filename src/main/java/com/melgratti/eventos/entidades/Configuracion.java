package com.melgratti.eventos.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "configuraciones")
public class Configuracion {
    @Id
    private String clave; // Ejemplo: "PRECIO_M2_ENTELADO"
    private Double valor;
}
