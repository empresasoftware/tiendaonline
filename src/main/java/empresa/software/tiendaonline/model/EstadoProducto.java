/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.tiendaonline.model;

import javax.persistence.*;
import org.hibernate.annotations.NaturalId;

/**
 *
 * @author pedro
 */
@Entity
@Table(name = "estado_producto")
public class EstadoProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private EstadoProductoName name;

    public Long getId() {
        return id;
    }

    public EstadoProductoName getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(EstadoProductoName name) {
        this.name = name;
    }
}
