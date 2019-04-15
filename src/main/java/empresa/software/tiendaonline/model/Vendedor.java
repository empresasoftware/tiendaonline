/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.tiendaonline.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author pedro
 */
@Entity
@Table(name = "vendedores")
public class Vendedor extends User {
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "tienda_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Tienda tienda;

    public Vendedor() {
    }

    public Vendedor(Tienda tienda, String name, String username, String email, String password) {
        super(name, username, email, password);
        this.tienda = tienda;
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }
}
