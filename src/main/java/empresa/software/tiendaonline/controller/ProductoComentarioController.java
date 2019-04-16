/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.tiendaonline.controller;

import empresa.software.tiendaonline.model.Producto;
import empresa.software.tiendaonline.model.ProductoComentario;
import empresa.software.tiendaonline.model.User;
import empresa.software.tiendaonline.payload.ApiResponse;
import empresa.software.tiendaonline.payload.ComentarioRequest;
import empresa.software.tiendaonline.repository.ProductoComentarioRepository;
import empresa.software.tiendaonline.repository.ProductoRepository;
import empresa.software.tiendaonline.repository.UserRepository;
import empresa.software.tiendaonline.security.CurrentUser;
import empresa.software.tiendaonline.security.UserPrincipal;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pedro
 */
@RestController
@RequestMapping("/api/comentarios")
public class ProductoComentarioController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    ProductoComentarioRepository productoComentarioRepository;
    
    @Secured({"ROLE_USER"})
    @PostMapping("/producto/{id}")
    public ResponseEntity<?> newComentario(@CurrentUser UserPrincipal userprincipal, @PathVariable Long id, @Valid @RequestBody ComentarioRequest comentarioRequest) {
        Producto producto = productoRepository.findById(id).get();
        User user = userRepository.findByUsername(userprincipal.getUsername()).get();

        ProductoComentario productoComentario = new ProductoComentario(user, producto, comentarioRequest.getComentario());

        productoComentarioRepository.save(productoComentario);

        return new ResponseEntity(new ApiResponse(true, "Ingresado Comentario"),
                HttpStatus.ACCEPTED);
    }

    @Secured({"ROLE_USER"})
    @PutMapping("/{id}")
    public ResponseEntity<?> updateComentario(@CurrentUser UserPrincipal userprincipal, @PathVariable Long id, @Valid @RequestBody ComentarioRequest comentarioRequest) {
        
        ProductoComentario productoComentario = productoComentarioRepository.findById(id).get();
        productoComentario.setComentario(comentarioRequest.getComentario());
        productoComentarioRepository.save(productoComentario);

        return new ResponseEntity(new ApiResponse(true, "Comentario actualizado"),
                HttpStatus.ACCEPTED);
    }
    
    @GetMapping("/producto/{id}/{page}/{pageSize}")
    public Page<ProductoComentario> getComentarioCliente(@CurrentUser UserPrincipal userprincipal, @PathVariable Long id, @PathVariable int page, @PathVariable int pageSize) {
        Producto producto = productoRepository.findById(id).get();
        return productoComentarioRepository.findByProducto(producto, PageRequest.of(page, pageSize));
    }
    
    @Secured({"ROLE_USER"})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComentario(@CurrentUser UserPrincipal userprincipal, @PathVariable Long id) {
        ProductoComentario productoComentario = productoComentarioRepository.findById(id).get();
        if (productoComentario.getUser().getUsername().equals(userprincipal.getUsername())) {
            productoComentarioRepository.deleteById(id);
            return new ResponseEntity(new ApiResponse(true, "Comentario eliminado!"),
                    HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(new ApiResponse(true, "No puede realizar esta transaccion"),
                HttpStatus.UNAUTHORIZED);
    }
}
