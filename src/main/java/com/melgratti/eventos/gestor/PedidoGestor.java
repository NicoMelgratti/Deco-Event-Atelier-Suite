package com.melgratti.eventos.gestor;

import com.melgratti.eventos.dto.DetallePedidoDTO;
import com.melgratti.eventos.dto.PedidoDTO;
import com.melgratti.eventos.entidades.Articulo;
import com.melgratti.eventos.entidades.DetallePedido;
import com.melgratti.eventos.entidades.Pedido;
import com.melgratti.eventos.repository.ArticuloRepository;
import com.melgratti.eventos.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoGestor {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ArticuloRepository articuloRepository;

    @Transactional
    public PedidoDTO procesarNuevoPedido(PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        pedido.setNombreContacto(pedidoDTO.getNombreContacto());
        pedido.setTelefonoContacto(pedidoDTO.getTelefonoContacto());
        pedido.setTipoEvento(pedidoDTO.getTipoEvento());
        pedido.setFechaEvento(pedidoDTO.getFechaEvento());
        pedido.setInvitados(pedidoDTO.getInvitados());
        pedido.setSalonM2(pedidoDTO.getSalonM2());
        pedido.setSalonAltura(pedidoDTO.getSalonAltura());
        pedido.setEstado("PENDIENTE");

        List<DetallePedido> listaDetalles = new ArrayList<>();
        double totalCalculado = 0.0;

        for (DetallePedidoDTO item : pedidoDTO.getDetalles()) {
            Articulo articuloReal = articuloRepository.findById(item.getArticuloId())
                    .orElseThrow(() -> new RuntimeException("Artículo no encontrado con ID: " + item.getArticuloId()));

            DetallePedido detalle = new DetallePedido();
            detalle.setPedido(pedido);
            detalle.setArticulo(articuloReal);
            detalle.setCantidad(item.getCantidad());
            detalle.setPrecioUnitario(articuloReal.getPrecio());

            double subtotal = articuloReal.getPrecio() * item.getCantidad();
            detalle.setSubtotal(subtotal);

            listaDetalles.add(detalle);
            totalCalculado += subtotal;
        }

        pedido.setTotalEstimado(totalCalculado);
        pedido.setDetalles(listaDetalles);

        Pedido pedidoGuardado = pedidoRepository.save(pedido);
        pedidoDTO.setTotalEstimado(pedidoGuardado.getTotalEstimado());
        return pedidoDTO;
    }

    @Transactional
    public Pedido actualizarEstado(Long id, String nuevoEstado) {
        // Buscamos el pedido por ID usando tu repository
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el pedido con ID: " + id));

        // Cambiamos el estado ("ACEPTADO" o "RECHAZADO")
        pedido.setEstado(nuevoEstado);

        // Guardamos los cambios
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> obtenerTodos() {
        return pedidoRepository.findAll();
    }
}