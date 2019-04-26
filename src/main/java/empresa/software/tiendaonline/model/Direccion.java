/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.tiendaonline.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author pedro
 */
@Entity
@Table(name = "direcciones")
public class Direccion implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private float latitud;
    
    private float longitud;
        
    @NotBlank
    @Size(max = 255)
    @Column(name = "calle_principal")
    private String callePrincipal;
    
    @NotBlank
    @Size(max = 255)
    @Column(name = "calle_secundaria")
    private String calleSecundaria;
    
    @NotBlank
    @Size(max = 255)
    @Column(name = "descripcion_ubicacion")
    private String descripcionUbicacion;
    
    @NotBlank
    @Size(max = 20)
    private String pais;
    
    @NotBlank
    @Size(max = 20)
    private String provincia;
    
    @NotBlank
    @Size(max = 20)
    private String ciudad;

    @NotBlank
    @Size(max = 20)
    private String codigoZipPostal;

    @NotBlank
    @Size(max = 20)
    private String numeroTelefono;
   
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tipo_direccion_id", referencedColumnName = "id", nullable = false)
    private TipoDireccion tipoDireccion;

    public Direccion() {
    }

    public Direccion(float latitud, float longitud, String callePrincipal, String calleSecundaria, String descripcionUbicacion, String pais, String provincia, String ciudad, String codigoZipPostal, String numeroTelefono, TipoDireccion tipoDireccion) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.callePrincipal = callePrincipal;
        this.calleSecundaria = calleSecundaria;
        this.descripcionUbicacion = descripcionUbicacion;
        this.pais = pais;
        this.provincia = provincia;
        this.ciudad = ciudad;
        this.codigoZipPostal = codigoZipPostal;
        this.numeroTelefono = numeroTelefono;
        this.tipoDireccion = tipoDireccion;
    }

    public Long getId() {
        return id;
    }

    public String getCallePrincipal() {
        return callePrincipal;
    }

    public String getCalleSecundaria() {
        return calleSecundaria;
    }

    public String getDescripcionUbicacion() {
        return descripcionUbicacion;
    }

    public String getPais() {
        return pais;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getCodigoZipPostal() {
        return codigoZipPostal;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public TipoDireccion getTipoDireccion() {
        return tipoDireccion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCallePrincipal(String callePrincipal) {
        this.callePrincipal = callePrincipal;
    }

    public void setCalleSecundaria(String calleSecundaria) {
        this.calleSecundaria = calleSecundaria;
    }

    public void setDescripcionUbicacion(String descripcionUbicacion) {
        this.descripcionUbicacion = descripcionUbicacion;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setCodigoZipPostal(String codigoZipPostal) {
        this.codigoZipPostal = codigoZipPostal;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public void setTipoDireccion(TipoDireccion tipoDireccion) {
        this.tipoDireccion = tipoDireccion;
    }

    public float getLatitud() {
        return latitud;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

}
