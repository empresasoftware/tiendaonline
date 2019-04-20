/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.tiendaonline.repository;

import empresa.software.tiendaonline.model.EstadoProducto;
import empresa.software.tiendaonline.model.EstadoProductoName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author pedro
 */
public interface EstadoProductoRepository extends JpaRepository<EstadoProducto, Long>{
    Optional<EstadoProducto> findByName(EstadoProductoName estadoProductoName);
}
