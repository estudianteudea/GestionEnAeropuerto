/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Usuariox
 */
@Embeddable
public class EmbarquePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "puertaEmbarque")
    private int puertaEmbarque;
    @Basic(optional = false)
    @NotNull
    @Column(name = "viaje")
    private int viaje;

    public EmbarquePK() {
    }

    public EmbarquePK(int puertaEmbarque, int viaje) {
        this.puertaEmbarque = puertaEmbarque;
        this.viaje = viaje;
    }

    public int getPuertaEmbarque() {
        return puertaEmbarque;
    }

    public void setPuertaEmbarque(int puertaEmbarque) {
        this.puertaEmbarque = puertaEmbarque;
    }

    public int getViaje() {
        return viaje;
    }

    public void setViaje(int viaje) {
        this.viaje = viaje;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) puertaEmbarque;
        hash += (int) viaje;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmbarquePK)) {
            return false;
        }
        EmbarquePK other = (EmbarquePK) object;
        if (this.puertaEmbarque != other.puertaEmbarque) {
            return false;
        }
        if (this.viaje != other.viaje) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.EmbarquePK[ puertaEmbarque=" + puertaEmbarque + ", viaje=" + viaje + " ]";
    }
    
}
