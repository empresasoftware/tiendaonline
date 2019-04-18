/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.tiendaonline.controller;

import empresa.software.tiendaonline.model.Producto;
import empresa.software.tiendaonline.model.ShoppingCart;
import empresa.software.tiendaonline.model.ShoppingCartKey;
import empresa.software.tiendaonline.model.User;
import empresa.software.tiendaonline.payload.ApiResponse;
import empresa.software.tiendaonline.repository.ProductoRepository;
import empresa.software.tiendaonline.repository.ShoppingCartRepository;
import empresa.software.tiendaonline.repository.UserRepository;
import empresa.software.tiendaonline.security.CurrentUser;
import empresa.software.tiendaonline.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pedro
 */
@RestController
@RequestMapping("/api/users/shoppingcart")
public class ShoppingCartController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    
    
    @Secured({"ROLE_USER"})
    @PostMapping("/producto/{id}/cantidad/{cantidad}")
    public ResponseEntity<?> newProducto(@CurrentUser UserPrincipal userprincipal, @PathVariable Long id, @PathVariable int cantidad) {
        Producto producto = productoRepository.findById(id).get();
        User user = userRepository.findByUsername(userprincipal.getUsername()).get();

        ShoppingCart shoppingCart = new ShoppingCart(new ShoppingCartKey(producto.getId(), userprincipal.getId()), user, producto, cantidad);
        
        shoppingCartRepository.save(shoppingCart);

        return new ResponseEntity(new ApiResponse(true, "Ingresado Producto al carrito"),
                HttpStatus.ACCEPTED);
    }
    
}
