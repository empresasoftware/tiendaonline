/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.tiendaonline.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author pedro
 */
@Embeddable
public class TiendaRatingKey extends RatingKey implements Serializable{
    @Column(name = "tienda_id")
    private Long tiendaId;

    public TiendaRatingKey() {
    }

    public TiendaRatingKey(Long tiendaId, Long clienteId) {
        super(clienteId);
        this.tiendaId = tiendaId;
    }

    public Long getTiendaId() {
        return tiendaId;
    }

    public void setTiendaId(Long tiendaId) {
        this.tiendaId = tiendaId;
    }
    
    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        TiendaRatingKey other = (TiendaRatingKey) obj;
        if(getClienteId() == null){
            if(other.getClienteId() !=null)
                return false;
        } else if (!getClienteId().equals(other.getClienteId()))
            return false;
        if (tiendaId == null){
            if (other.tiendaId != null)
                return false;
        } else if (!tiendaId.equals(other.tiendaId))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.getClienteId());
        hash = 61 * hash + Objects.hashCode(this.tiendaId);
        return hash;
    }
}
