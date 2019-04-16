/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.tiendaonline.controller;

import empresa.software.tiendaonline.model.TipoDireccion;
import empresa.software.tiendaonline.repository.TipoDireccionRepository;
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
@RequestMapping("/api/tiendas/tipodireccion")
public class TipoDireccionController {
    
    @Autowired
    TipoDireccionRepository tipoDireccionRepository;
    
    @GetMapping
    public List<TipoDireccion> getTipoDireccion() {          
        return tipoDireccionRepository.findAll();
    }
}
