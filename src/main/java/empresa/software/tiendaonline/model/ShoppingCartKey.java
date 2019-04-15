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
public class ShoppingCartKey implements Serializable {
   
    @Column(name = "user_id")
    private Long userId;
 
    @Column(name = "producto_id")
    private Long productoId;

    public ShoppingCartKey() {
    }

    public ShoppingCartKey(Long userId, Long productoId) {
        this.userId = userId;
        this.productoId = productoId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
        ShoppingCartKey other = (ShoppingCartKey) obj;
        if(userId == null){
            if(other.userId !=null)
                return false;
        } else if (!userId.equals(other.userId))
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
        hash = 61 * hash + Objects.hashCode(this.userId);
        hash = 61 * hash + Objects.hashCode(this.productoId);
        return hash;
    }
}
