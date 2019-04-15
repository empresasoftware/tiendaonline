/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.tiendaonline.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 *
 * @author pedro
 */
@Entity
@Table(name = "shopping_cart")
public class ShoppingCart implements Serializable {
    
    @EmbeddedId
    private ShoppingCartKey id;
 
    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private User user;
 
    @ManyToOne
    @MapsId("producto_id")
    @JoinColumn(name = "producto_id")
    private Producto producto;
 
    private int cantidad;

    public ShoppingCart() {
    }

    public ShoppingCart(ShoppingCartKey id, User user, Producto producto, int cantidad) {
        this.id = id;
        this.user = user;
        this.producto = producto;
        this.cantidad = cantidad;
    }
    
    

    public ShoppingCartKey getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setId(ShoppingCartKey id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
@Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        ShoppingCart other = (ShoppingCart) obj;
        if(id == null){
            if(other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }
}
