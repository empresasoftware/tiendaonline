/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.tiendaonline.controller;

import empresa.software.tiendaonline.model.Producto;
import empresa.software.tiendaonline.model.User;
import empresa.software.tiendaonline.payload.ApiResponse;
import empresa.software.tiendaonline.repository.ProductoRepository;
import empresa.software.tiendaonline.repository.UserRepository;
import empresa.software.tiendaonline.security.CurrentUser;
import empresa.software.tiendaonline.security.UserPrincipal;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/api/users/mywishlist")
public class WishListController {
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    ProductoRepository productoRepository;
    
    @Secured({"ROLE_USER"})
    @GetMapping
    public Set<Producto> getMyWishList(@CurrentUser UserPrincipal userprincipal) {
        User user = userRepository.findById(userprincipal.getId()).get();
        return user.getWishlist();
    }
    
    @Secured({"ROLE_USER"})
    @PostMapping("/producto/{id}")
    public ResponseEntity<?> addToMyWishList(@CurrentUser UserPrincipal userprincipal, @PathVariable Long id) {
        
        User user = userRepository.findById(userprincipal.getId()).get();
        Producto producto = productoRepository.findById(id).get();
        user.getWishlist().add(producto);
        userRepository.save(user);
        
        return ResponseEntity.accepted().body(new ApiResponse(true, "Producto agregado a la lista de deseos"));
    }
    
    @Secured({"ROLE_USER"})
    @DeleteMapping("/producto/{id}")
    public ResponseEntity<?> deleteFromMyWishList(@CurrentUser UserPrincipal userprincipal, @PathVariable Long id) {
        
        User user = userRepository.findById(userprincipal.getId()).get();
        Producto producto = productoRepository.findById(id).get();
        user.getWishlist().remove(producto);
        userRepository.save(user);
        
        return ResponseEntity.accepted().body(new ApiResponse(true, "Producto eliminado de la lista de deseos"));
    }
}
