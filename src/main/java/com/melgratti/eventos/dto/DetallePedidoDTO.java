package com.melgratti.eventos.dto;

import lombok.Data;

@Data
public class DetallePedidoDTO {
    private Long articuloId;
    private Integer cantidad;
    private Double precioUnitario;
}