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
public class ProductoRatingKey extends RatingKey implements Serializable{
    @Column(name = "producto_id")
    private Long productoId;

    public ProductoRatingKey() {
    }

    public ProductoRatingKey(Long productoId, Long clienteId) {
        super(clienteId);
        this.productoId = productoId;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }
    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        ProductoRatingKey other = (ProductoRatingKey) obj;
        if(getClienteId() == null){
            if(other.getClienteId() !=null)
                return false;
        } else if (!getClienteId().equals(other.getClienteId()))
            return false;
        if (productoId == null){
            if (other.productoId != null)
                return false;
        } else if (!productoId.equals(other.productoId))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.getClienteId());
        hash = 61 * hash + Objects.hashCode(this.productoId);
        return hash;
    }
}
