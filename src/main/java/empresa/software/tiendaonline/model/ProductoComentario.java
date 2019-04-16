/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.tiendaonline.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import empresa.software.tiendaonline.model.audit.DateAudit;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author pedro
 */
@Entity
@Table(name = "producto_comentario")
public class ProductoComentario extends DateAudit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
 
    @ManyToOne
    @JoinColumn(name = "producto_id")
    @JsonIgnore
    private Producto producto;
 
    private String comentario;

    public ProductoComentario() {
    }

    public ProductoComentario(User user, Producto producto, String comentario) {
        this.user = user;
        this.producto = producto;
        this.comentario = comentario;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Producto getProducto() {
        return producto;
    }

    public String getComentario() {
        return comentario;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
