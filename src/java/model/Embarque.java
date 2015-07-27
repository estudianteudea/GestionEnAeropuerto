/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuariox
 */
@Entity
@Table(name = "embarque")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Embarque.findAll", query = "SELECT e FROM Embarque e"),
    @NamedQuery(name = "Embarque.findByHoraCierre", query = "SELECT e FROM Embarque e WHERE e.horaCierre = :horaCierre"),
    @NamedQuery(name = "Embarque.findByPuertaEmbarque", query = "SELECT e FROM Embarque e WHERE e.embarquePK.puertaEmbarque = :puertaEmbarque"),
    @NamedQuery(name = "Embarque.findByViaje", query = "SELECT e FROM Embarque e WHERE e.embarquePK.viaje = :viaje")})
public class Embarque implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EmbarquePK embarquePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horaCierre")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaCierre;
    @JoinColumn(name = "puertaEmbarque", referencedColumnName = "idPuertaEmbarque", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Puertaembarque puertaembarque;
    @JoinColumn(name = "viaje", referencedColumnName = "idViaje", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Viaje viaje1;

    public Embarque() {
    }

    public Embarque(EmbarquePK embarquePK) {
        this.embarquePK = embarquePK;
    }

    public Embarque(EmbarquePK embarquePK, Date horaCierre) {
        this.embarquePK = embarquePK;
        this.horaCierre = horaCierre;
    }

    public Embarque(int puertaEmbarque, int viaje) {
        this.embarquePK = new EmbarquePK(puertaEmbarque, viaje);
    }

    public EmbarquePK getEmbarquePK() {
        return embarquePK;
    }

    public void setEmbarquePK(EmbarquePK embarquePK) {
        this.embarquePK = embarquePK;
    }

    public Date getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(Date horaCierre) {
        this.horaCierre = horaCierre;
    }

    public Puertaembarque getPuertaembarque() {
        return puertaembarque;
    }

    public void setPuertaembarque(Puertaembarque puertaembarque) {
        this.puertaembarque = puertaembarque;
    }

    public Viaje getViaje1() {
        return viaje1;
    }

    public void setViaje1(Viaje viaje1) {
        this.viaje1 = viaje1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (embarquePK != null ? embarquePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Embarque)) {
            return false;
        }
        Embarque other = (Embarque) object;
        if ((this.embarquePK == null && other.embarquePK != null) || (this.embarquePK != null && !this.embarquePK.equals(other.embarquePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Embarque[ embarquePK=" + embarquePK + " ]";
    }
    
}
