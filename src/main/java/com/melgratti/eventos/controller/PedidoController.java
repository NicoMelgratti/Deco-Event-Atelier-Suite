package com.melgratti.eventos.controller;

import com.melgratti.eventos.dto.PedidoDTO;
import com.melgratti.eventos.entidades.Pedido;
import com.melgratti.eventos.gestor.PedidoGestor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "http://localhost:3000")
public class PedidoController {

    @Autowired
    private PedidoGestor pedidoGestor;

    @PostMapping
    public ResponseEntity<PedidoDTO> crearPedidoCarrito(@RequestBody PedidoDTO pedidoDTO) {
        try {
            PedidoDTO pedidoProcesado = pedidoGestor.procesarNuevoPedido(pedidoDTO);
            return new ResponseEntity<>(pedidoProcesado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.BAD_REQUEST);
        }
    }
    @PatchMapping("/{id}/estado")
    public ResponseEntity<Pedido> actualizarEstado(@PathVariable Long id, @RequestParam String estado) {
        Pedido actualizado = pedidoGestor.actualizarEstado(id, estado);
        return ResponseEntity.ok(actualizado);
    }

    // AGREGÁ ESTO PARA QUE EL ADMIN PUEDA VER LA LISTA
    @GetMapping
    public ResponseEntity<List<Pedido>> obtenerTodosLosPedidos() {
        try {
            List<Pedido> pedidos = pedidoGestor.obtenerTodos(); // Asegurate de tener este método en el Gestor
            return new ResponseEntity<>(pedidos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}