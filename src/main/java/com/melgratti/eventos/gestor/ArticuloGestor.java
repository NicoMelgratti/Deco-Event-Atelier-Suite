package com.melgratti.eventos.gestor;

import com.melgratti.eventos.dto.ArticuloDTO;
import com.melgratti.eventos.entidades.Articulo;
import com.melgratti.eventos.repository.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticuloGestor {

    @Autowired
    private ArticuloRepository articuloRepository;

    public List<ArticuloDTO> obtenerTodosActivos() {
        return articuloRepository.findByActivoTrue().stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    public ArticuloDTO crearArticulo(ArticuloDTO dto) {
        Articulo entidad = convertirAEntidad(dto);
        entidad.setActivo(true);
        Articulo guardado = articuloRepository.save(entidad);
        return convertirADto(guardado);
    }

    private ArticuloDTO convertirADto(Articulo entidad) {
        ArticuloDTO dto = new ArticuloDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setCategoria(entidad.getCategoria());
        dto.setPrecio(entidad.getPrecio());
        dto.setRequiereAlturaMinima(entidad.getRequiereAlturaMinima());
        dto.setOcupaM2(entidad.getOcupaM2());
        return dto;
    }

    private Articulo convertirAEntidad(ArticuloDTO dto) {
        Articulo entidad = new Articulo();
        entidad.setNombre(dto.getNombre());
        entidad.setCategoria(dto.getCategoria());
        entidad.setPrecio(dto.getPrecio());
        entidad.setRequiereAlturaMinima(dto.getRequiereAlturaMinima());
        entidad.setOcupaM2(dto.getOcupaM2());
        return entidad;
    }

    public void eliminar(Long id) {
        articuloRepository.deleteById(id);
    }
}