/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.tiendaonline.repository;

import empresa.software.tiendaonline.model.TiendaRatingKey;
import empresa.software.tiendaonline.model.RatingTienda;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author pedro
 */
public interface RatingTiendaRepository extends JpaRepository<RatingTienda, TiendaRatingKey> {
    
}
