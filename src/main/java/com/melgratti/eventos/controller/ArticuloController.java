package com.melgratti.eventos.controller;

import com.melgratti.eventos.dto.ArticuloDTO;
import com.melgratti.eventos.gestor.ArticuloGestor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articulos")
@CrossOrigin(origins = "*")
public class ArticuloController {

    @Autowired
    private ArticuloGestor articuloGestor;

    @GetMapping
    public ResponseEntity<List<ArticuloDTO>> listarArticulos() {
        return new ResponseEntity<>(articuloGestor.obtenerTodosActivos(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ArticuloDTO> crearArticulo(@RequestBody ArticuloDTO articuloDTO) {
        return new ResponseEntity<>(articuloGestor.crearArticulo(articuloDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarArticulo(@PathVariable Long id) {
        articuloGestor.eliminar(id); // Asegúrate de tener este método en tu Gestor
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}