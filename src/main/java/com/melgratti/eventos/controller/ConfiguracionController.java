package com.melgratti.eventos.controller;

import com.melgratti.eventos.entidades.Configuracion;
import com.melgratti.eventos.repository.ConfiguracionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/config")
@CrossOrigin(origins = "*")
public class ConfiguracionController {

    @Autowired
    private ConfiguracionRepository repository;

    @GetMapping("/{clave}")
    public ResponseEntity<Double> getValor(@PathVariable String clave) {
        return repository.findById(clave)
                .map(c -> ResponseEntity.ok(c.getValor()))
                .orElse(ResponseEntity.ok(20000.0)); // Valor por defecto
    }

    @PutMapping("/{clave}")
    public void updateValor(@PathVariable String clave, @RequestBody Double nuevoValor) {
        Configuracion config = new Configuracion();
        config.setClave(clave);
        config.setValor(nuevoValor);
        repository.save(config);
    }
}