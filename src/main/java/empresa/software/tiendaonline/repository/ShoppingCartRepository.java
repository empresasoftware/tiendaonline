/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.tiendaonline.repository;

import empresa.software.tiendaonline.model.ShoppingCart;
import empresa.software.tiendaonline.model.ShoppingCartKey;
import empresa.software.tiendaonline.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author pedro
 */
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, ShoppingCartKey> {
    List<ShoppingCart> findByUser(User user);
}
