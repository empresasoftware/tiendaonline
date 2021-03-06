/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.tiendaonline.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.NaturalId;

/**
 *
 * @author pedro
 */
@Entity
@Table(name = "tipo_tienda")
public class TipoTienda implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private TipoTiendaName name;

    public Long getId() {
        return id;
    }

    public TipoTiendaName getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(TipoTiendaName name) {
        this.name = name;
    }
}
