package com.melgratti.eventos.entidades;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_contacto")
    private String nombreContacto;

    @Column(name = "telefono_contacto")
    private String telefonoContacto;

    @Column(name = "tipo_evento")
    private String tipoEvento;

    @Column(name = "fecha_evento")
    private LocalDate fechaEvento;

    private Integer invitados;

    @Column(name = "salon_m2")
    private Double salonM2;

    @Column(name = "salon_altura")
    private Double salonAltura;

    @Column(name = "total_estimado")
    private Double totalEstimado;

    private String estado;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<DetallePedido> detalles = new ArrayList<>();

    // Método helper para vincular detalles
    public void agregarDetalle(DetallePedido detalle) {
        detalles.add(detalle);
        detalle.setPedido(this);
    }

}