package com.melgratti.eventos.dto;

import lombok.Data;

@Data
public class ArticuloDTO {
    private Long id;
    private String nombre;
    private String categoria;
    private Double precio;
    private Double requiereAlturaMinima;
    private Double ocupaM2;
}