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
@Table(name = "rating_tiendas")
public class RatingTienda implements Serializable {
    @EmbeddedId
    private TiendaRatingKey id; 

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private User user;
        
    @ManyToOne
    @MapsId("tienda_id")
    @JoinColumn(name = "tienda_id")
    private Tienda tienda;
    
    private short rating;

    public RatingTienda() {
    }

    public RatingTienda(TiendaRatingKey id, User user, Tienda tienda, short rating) {
        this.id = id;
        this.user = user;
        this.tienda = tienda;
        this.rating = rating;
    }

    public TiendaRatingKey getId() {
        return id;
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setId(TiendaRatingKey id) {
        this.id = id;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

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
 
}
