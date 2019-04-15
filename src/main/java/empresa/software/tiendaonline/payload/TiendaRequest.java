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
public class TiendaRequest {
    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 15)
    private String shopname;

    @NotBlank
    @Size(max = 60)
    private String tipoTienda;

    public String getName() {
        return name;
    }

    public String getShopname() {
        return shopname;
    }

    public String getTipoTienda() {
        return tipoTienda;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public void setTipoTienda(String tipoTienda) {
        this.tipoTienda = tipoTienda;
    }
    
}
