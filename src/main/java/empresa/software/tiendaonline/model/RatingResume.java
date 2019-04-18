/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.tiendaonline.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author pedro
 */
@Entity
@Table(name = "rating_resume")
public class RatingResume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float estrellas;
    
    private long numeroUsuarios; 

    public Long getId() {
        return id;
    }

    public float getEstrellas() {
        return estrellas;
    }

    public long getNumeroUsuarios() {
        return numeroUsuarios;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEstrellas(float estrellas) {
        this.estrellas = estrellas;
    }

    public void setNumeroUsuarios(long numeroUsuarios) {
        this.numeroUsuarios = numeroUsuarios;
    }
}
