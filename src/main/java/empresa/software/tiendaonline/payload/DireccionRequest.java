/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.tiendaonline.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author pedro
 */
public class DireccionRequest {
    
    private float latitud;
    
    private float longitud;
    
    @NotBlank
    @Size(max = 255)
    private String callePrincipal;
    
    @NotBlank
    @Size(max = 255)
    private String calleSecundaria;
    
    @NotBlank
    @Size(max = 255)
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
