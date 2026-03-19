package com.melgratti.eventos.entidades;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "articulos")
public class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String categoria;
    private Double precio;

    @Column(name = "requiere_altura_minima")
    private Double requiereAlturaMinima;

    @Column(name = "ocupa_m2")
    private Double ocupaM2;

    private Boolean activo;
}



