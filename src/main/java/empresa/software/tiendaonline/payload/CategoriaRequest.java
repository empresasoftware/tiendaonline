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
public class CategoriaRequest {
    
    private Long parent;
    
    @NotBlank
    @Size(max = 50)
    private String nombre;

    public Long getParent() {
        return parent;
    }

    public String getNombre() {
        return nombre;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
