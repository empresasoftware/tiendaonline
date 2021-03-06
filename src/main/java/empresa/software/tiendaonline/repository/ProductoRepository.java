/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.tiendaonline.repository;

import empresa.software.tiendaonline.model.Categoria;
import empresa.software.tiendaonline.model.Tienda;
import empresa.software.tiendaonline.model.Producto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author pedro
 */
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Page<Producto> findByTienda(Tienda tienda, Pageable pageable);
    Page<Producto> findByCategoria(Categoria categoria, Pageable pageable);
}
