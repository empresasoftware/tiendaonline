/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.tiendaonline.controller;

import empresa.software.tiendaonline.model.EstadoProducto;
import empresa.software.tiendaonline.repository.EstadoProductoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pedro
 */
@RestController
@RequestMapping("/api/productos/estados")
public class EstadoProductoController {
    
    @Autowired
    private EstadoProductoRepository estadoProductoRepository;
    
    @GetMapping
    public List<EstadoProducto> getCategoriaAll() {
        return estadoProductoRepository.findAll();
    }
}
