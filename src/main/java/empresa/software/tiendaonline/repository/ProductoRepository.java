/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.tiendaonline.repository;

import empresa.software.tiendaonline.model.Tienda;
import empresa.software.tiendaonline.model.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author pedro
 */
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByTienda(Tienda tienda);
}
