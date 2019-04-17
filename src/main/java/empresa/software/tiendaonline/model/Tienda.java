/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.tiendaonline.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import empresa.software.tiendaonline.model.audit.DateAudit;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author pedro
 */
@Entity
@Table(name = "tiendas")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Tienda extends DateAudit{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String name;
    
    @Size(max = 20)
    private String shopname;

    @NotBlank
    @Size(max = 500)
    private String descripcion;

    //@NotBlank
    @Size(max = 200)
    private String logo;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tipo_tienda_id", referencedColumnName = "id", nullable = false)
    private TipoTienda tipoTienda;
    
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
   @JoinColumn(name = "tienda_id")
   @JsonIgnore
    private Set<Direccion> direcciones = new HashSet<>();

   @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "tienda")
   @JsonIgnore
    private Set<Producto> productos = new HashSet<>();

    public Tienda() {
    }

    public Tienda(String name, String shopname, TipoTienda tipoTienda) {
        this.name = name;
        this.shopname = shopname;
        this.tipoTienda = tipoTienda;
    }
    
    public Tienda(String name, String shopname, String logo, TipoTienda tipoTienda) {
        this.name = name;
        this.shopname = shopname;
        this.logo = logo;
        this.tipoTienda = tipoTienda;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogo() {
        return logo;
    }

    public TipoTienda getTipoTienda() {
        return tipoTienda;
    }

    public Set<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setTipoTienda(TipoTienda tipoTienda) {
        this.tipoTienda = tipoTienda;
    }

    public void setDirecciones(Set<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
