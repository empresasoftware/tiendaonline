/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.tiendaonline.repository;

import empresa.software.tiendaonline.model.TipoTienda;
import empresa.software.tiendaonline.model.TipoTiendaName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author pedro
 */
public interface TipoTiendaRepository extends JpaRepository<TipoTienda, Long> {
    Optional<TipoTienda> findByName(TipoTiendaName tipoTiendaName);
}
