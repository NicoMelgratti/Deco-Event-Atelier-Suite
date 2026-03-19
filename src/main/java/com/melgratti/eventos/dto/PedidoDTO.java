package com.melgratti.eventos.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class PedidoDTO {
    private String nombreContacto;
    private String telefonoContacto;
    private String tipoEvento;
    private LocalDate fechaEvento;
    private Integer invitados;
    private Double salonM2;
    private Double salonAltura;
    private List<DetallePedidoDTO> detalles;
    private Double totalEstimado;
}