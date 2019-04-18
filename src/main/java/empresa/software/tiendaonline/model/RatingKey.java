/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.tiendaonline.model;

import javax.persistence.Column;

/**
 *
 * @author pedro
 */
public class RatingKey {
    @Column(name = "cliente_id")
    private Long clienteId;

    public RatingKey() {
    }

    public RatingKey(Long clienteId) {
        this.clienteId = clienteId;
    }
    

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

}
