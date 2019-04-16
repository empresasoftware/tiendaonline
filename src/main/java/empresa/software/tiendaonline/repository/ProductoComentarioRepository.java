/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.tiendaonline.repository;

import empresa.software.tiendaonline.model.Producto;
import empresa.software.tiendaonline.model.ProductoComentario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author pedro
 */
public interface ProductoComentarioRepository extends JpaRepository<ProductoComentario, Long> {
    Page<ProductoComentario> findByProducto(Producto producto, Pageable pageable);
}
