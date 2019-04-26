/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.tiendaonline.repository;

import empresa.software.tiendaonline.model.TipoDireccion;
import empresa.software.tiendaonline.model.TipoDireccionName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author pedro
 */
public interface TipoDireccionRepository extends JpaRepository<TipoDireccion, Long> {
    Optional<TipoDireccion> findByName(TipoDireccionName tipoDireccionName);

}
