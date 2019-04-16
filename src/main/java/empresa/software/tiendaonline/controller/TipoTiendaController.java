/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.tiendaonline.controller;

import empresa.software.tiendaonline.model.TipoTienda;
import empresa.software.tiendaonline.repository.TipoTiendaRepository;
import empresa.software.tiendaonline.security.CurrentUser;
import empresa.software.tiendaonline.security.UserPrincipal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pedro
 */
@RestController
@RequestMapping("/api/tiendas/tipotienda")
public class TipoTiendaController {
    @Autowired
    TipoTiendaRepository tipoTiendaRepository;
    
    @GetMapping
    public List<TipoTienda> getTipoTiendas() {          
        return tipoTiendaRepository.findAll();
    }
}
