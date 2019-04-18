/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.tiendaonline.model;

import java.io.Serializable;
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
@Table(name = "rating_productos")
public class RatingProducto implements Serializable {

    public User getUser() {
        return user;
    }

    public short getRating() {
        return rating;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRating(short rating) {
        this.rating = rating;
    }
    @EmbeddedId
    private ProductoRatingKey id; 
    
    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne
    @MapsId("producto_id")
    @JoinColumn(name = "producto_id")
    private Producto producto;
    
    private short rating;


    public RatingProducto() {
    }

    public RatingProducto(ProductoRatingKey id, User user, Producto producto, short rating) {
        this.id = id;
        this.user = user;
        this.producto = producto;
        this.rating = rating;
    }

    public ProductoRatingKey getId() {
        return id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setId(ProductoRatingKey id) {
        this.id = id;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
