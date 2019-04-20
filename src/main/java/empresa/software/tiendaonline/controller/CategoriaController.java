/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.tiendaonline.controller;

import empresa.software.tiendaonline.model.Categoria;
import empresa.software.tiendaonline.payload.CategoriaRequest;
import empresa.software.tiendaonline.repository.CategoriaRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pedro
 */
@RestController
@RequestMapping("/api/productos/categorias")
public class CategoriaController {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Secured({"ROLE_SHOP"})
    @PostMapping("/nuevo")
    public Categoria newCategoria(@Valid @RequestBody CategoriaRequest categoriaRequest) {
        Categoria categoria;
        Categoria parent;
        if (categoriaRequest.getParent() == null) {
            categoria = new Categoria(categoriaRequest.getNombre(), null);
            categoriaRepository.save(categoria);
        } else {
            parent = categoriaRepository.getOne(categoriaRequest.getParent());
            categoria = new Categoria(categoriaRequest.getNombre(), parent);
            parent.getChildren().add(categoria);
            categoriaRepository.save(categoria);
            categoriaRepository.save(parent);
        }
        return categoria;
    }

    @GetMapping
    public List<Categoria> getCategoriaAll() {
        return categoriaRepository.findByParent(null);
    }
}
