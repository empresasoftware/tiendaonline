/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.tiendaonline.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import empresa.software.tiendaonline.model.audit.DateAudit;
import empresa.software.tiendaonline.model.RatingResume;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author pedro
 */
@Entity
@Table(name = "productos")
public class Producto extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Size(max = 100)
    private String nombre;

    @NotBlank
    @Size(max = 100)
    private String descripcion;
    
//    @Digits(integer=6, fraction=2)
//    @Min(0)
//    private BigDecimal precio;
//    
//    private int cantidad;
    
    @NotBlank
    @Size(max = 100)
    private String imagenPrincipal;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoria_producto_id", referencedColumnName = "id", nullable = false)
    private Categoria categoriaProducto;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estado_producto_id", referencedColumnName = "id", nullable = false)
    private EstadoProducto estadoProducto;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rating_resume_producto_id", referencedColumnName = "id")
    private RatingResume ratingResume;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tienda_id", nullable = false)
    @JsonIgnore
    private Tienda tienda;
    
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;
    
   @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "producto")
    @JsonIgnore
    private Set<ImagenProducto> imagenes = new HashSet<>();
   
    @OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<ShoppingCart> shoppingCart = new HashSet<>();
    
    @OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<RatingProducto> ratings;
    
    @OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<ProductoComentario> comentarios;

    public Producto() {
    }

    public Producto(String nombre, String descripcion, String imagenPrincipal, Categoria categoriaProducto, Tienda tienda) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagenPrincipal = imagenPrincipal;
        this.categoriaProducto = categoriaProducto;
        this.tienda = tienda;
    }   
   
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Tienda getTienda() {
        return tienda;
    }

    public Set<ImagenProducto> getImagenes() {
        return imagenes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

    public void setImagenes(Set<ImagenProducto> imagenes) {
        this.imagenes = imagenes;
    }

    public Set<ShoppingCart> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(Set<ShoppingCart> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Set<ProductoComentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(Set<ProductoComentario> comentarios) {
        this.comentarios = comentarios;
    }

    public String getImagenPrincipal() {
        return imagenPrincipal;
    }

    public void setImagenPrincipal(String imagenPrincipal) {
        this.imagenPrincipal = imagenPrincipal;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public EstadoProducto getEstadoProducto() {
        return estadoProducto;
    }

    public RatingResume getRatingResume() {
        return ratingResume;
    }

    public void setEstadoProducto(EstadoProducto estadoProducto) {
        this.estadoProducto = estadoProducto;
    }

    public void setRatingResume(RatingResume ratingResume) {
        this.ratingResume = ratingResume;
    }

    public Set<RatingProducto> getRatings() {
        return ratings;
    }

    public void setRatings(Set<RatingProducto> ratings) {
        this.ratings = ratings;
    }

    public Categoria getCategoriaProducto() {
        return categoriaProducto;
    }

    public void setCategoriaProducto(Categoria categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
    }
   
   
}
